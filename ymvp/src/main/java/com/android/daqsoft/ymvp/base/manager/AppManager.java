package com.android.daqsoft.ymvp.base.manager;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.view.View;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import timber.log.Timber;

/**
 * Created by yanbo on 2018/1/10.
 * 用于管理所有Activity类
 * 也可通过post(Message) 远程遥控执行对应的方法，用法和EventBus类似
 */
@Singleton
public class AppManager {
    private final String TAG = this.getClass().getSimpleName();
    private Application mApplication;
    /**
     * Evenbus TAG
     */
    public static final String APPMANAGER_MESSAGE = "appmanager_message";
    /**
     * 处理页面不同的消息标示
     */
    public static final int START_ACTIVITY = 5000;
    public static final int KILLALL_ACTIVITY = 5001;
    public static final int EXIT_APP = 5002;
    public static final int SHOW_SNACKBAR = 5003;

    /**
     * 管理所有的Activity,容器中的Activity是Activity的创建顺序，不能保证和Activity的任务栈一致
     */
    public List<Activity> mActivityList;
    //当前在前台的 Activity
    private Activity mCurrentActivity;
    //提供给外部扩展 AppManager 的 onReceive 方法
    private HandleListener mHandleListener;
    //true 为不需要加入到 Activity 容器进行统一管理,默认为 false
    public static final String IS_NOT_ADD_ACTIVITY_LIST = "is_not_add_activity_list";


    //---------------------------------------------------------------------------------构造方法--------------------------------------

    /**
     * application由dagger2提供依赖
     * @param application 获取application来操作页面的添加与跳转
     */
    @Inject
    public AppManager(Application application) {
        this.mApplication = application;
        EventBus.getDefault().register(this);//注册
    }

    //---------------------------------------------------------------------------------外部遥控操作本类--------------------------------------

    /**
     *
     * @param msg 来操作不同的页面处理方法
     */
    @Subscriber(tag = APPMANAGER_MESSAGE,mode = ThreadMode.MAIN)
    public void onReceive(Message msg){
        switch (msg.what){
            case START_ACTIVITY://启动
                if (msg.obj == null)
                    break;
                dispathStart(msg);
                break;
            case KILLALL_ACTIVITY:
                killAll();
                break;
            case EXIT_APP:
                appExit();
                break;
            case SHOW_SNACKBAR:
                if (msg.obj == null)
                    break;
                showSnackBar((String) msg.obj, msg.arg1 == 0 ? false : true);
                break;
            default:
                break;
        }
        /**
         * 外部扩展调用本类
         */
        if (mHandleListener !=null){
            mHandleListener.handleMessage(this,msg);
        }

    }
    public HandleListener getHandleListener(){
        return mHandleListener;
    }
    /**
     * 提供给外部扩展 {@link AppManager} 的 {@link #onReceive} 方法(远程遥控 {@link AppManager} 的功能)
     * 建议在 {@link ConfigModule#injectAppLifecycle(Context, List)} 中
     * 通过 {@link AppLifecycles#onCreate(Application)} 在 App 初始化时,使用此方法传入自定义的 {@link HandleListener}
     *
     * @param handleListener
     */
    public void setHandleListener(HandleListener handleListener) {
        this.mHandleListener = handleListener;
    }
    /**
     * 通过此方法远程遥控 {@link AppManager} ,使 {@link #onReceive(Message)} 执行对应方法
     *
     * @param msg
     */
    public static void post(Message msg) {
        EventBus.getDefault().post(msg, APPMANAGER_MESSAGE);
    }


    //---------------------------------------------------------------------------------前台Activity的设置与获取--------------------------------------
    /**
     * 将在前台的 {@link Activity} 赋值给 {@code currentActivity}, 注意此方法是在 {@link Activity#onResume} 方法执行时将栈顶的 {@link Activity} 赋值给 {@code currentActivity}
     * 所以在栈顶的 {@link Activity} 执行 {@link Activity#onCreate} 方法时使用 {@link #getCurrentActivity()} 获取的就不是当前栈顶的 {@link Activity}, 可能是上一个 {@link Activity}
     * 如果在 App 启动第一个 {@link Activity} 执行 {@link Activity#onCreate} 方法时使用 {@link #getCurrentActivity()} 则会出现返回为 {@code null} 的情况
     * 想避免这种情况请使用 {@link #getTopActivity()}
     *
     * @param currentActivity
     */
    public void setCurrentActivity(Activity currentActivity){
        this.mCurrentActivity = currentActivity;
    }
    /**
     * 获取在前台的 {@link Activity} (保证获取到的 {@link Activity} 正处于可见状态, 即未调用 {@link Activity#onStop()}), 获取的 {@link Activity} 存续时间
     * 是在 {@link Activity#onStop()} 之前, 所以如果当此 {@link Activity} 调用 {@link Activity#onStop()} 方法之后, 没有其他的 {@link Activity} 回到前台(用户返回桌面或者打开了其他 App 会出现此状况)
     * 这时调用 {@link #getCurrentActivity()} 有可能返回 {@code null}, 所以请注意使用场景和 {@link #getTopActivity()} 不一样
     * <p>
     * Example usage:
     * 使用场景比较适合, 只需要在可见状态的 {@link Activity} 上执行的操作
     * 如当后台 {@link Service} 执行某个任务时, 需要让前台 {@link Activity} ,做出某种响应操作或其他操作,如弹出 {@link Dialog}, 这时在 {@link Service} 中就可以使用 {@link #getCurrentActivity()}
     * 如果返回为 {@code null}, 说明没有前台 {@link Activity} (用户返回桌面或者打开了其他 App 会出现此状况), 则不做任何操作, 不为 {@code null}, 则弹出 {@link Dialog}
     *
     * @return
     */
    public Activity getCurrentActivity(){
        return mCurrentActivity != null ? mCurrentActivity : null;
    }
    //---------------------------------------------------------------------------------启动Activity--------------------------------------
    /**
     * @param msg 根据类型判断跳转(Intent Or Class)
     */
    private void dispathStart(Message msg) {
        if (msg.obj instanceof Intent)
            startActivity((Intent) msg.obj);
        else if (msg.obj instanceof Class)
            startActivity((Class) msg.obj);
    }

    /**
     * 让栈顶的Activity打开指定的Activity(Intent方式)
     * @param intent
     */
    public void startActivity(Intent intent){
        if (getTopActivity() == null){
            Timber.tag(TAG).w("mCurrentActivity == null when startActivity(Intent)");
            //前台为空用new_task模式启动activity
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mApplication.startActivity(intent);
            return;
        }
        getTopActivity().startActivity(intent);
    }

    /**
     * 让栈顶的Activity打开指定的Activity(Intent方式)
     * @param activityClass
     */
    public void startActivity(Class activityClass){
        startActivity(new Intent(mApplication,activityClass));
    }

    //---------------------------------------------------------------------------------Activity的获取与添加--------------------------------------
    /**
     * @param activityClass 找到指定Activity实例 同一Activity可能有多个实例，返回最早创建的实例
     * @return 没有返回null
     */
    public Activity findActivity(Class<?> activityClass) {
        if (mActivityList == null) {
            Timber.tag(TAG).w("mActivityList == null when findActivity(Class)");
            return null;
        }
        for (Activity activity : mActivityList) {
            if (activity.getClass().equals(activityClass)) {
                return activity;
            }
        }
        return null;
    }
    /**
     * @param activityClass 指定Activiity是否存活，class可能多个
     * @return
     */
    public boolean activityClassIsLive(Class<?> activityClass) {
        if (mActivityList == null) {
            Timber.tag(TAG).w("mActivityList == null when activityClassIsLive(Class)");
            return false;
        }
        for (Activity activity : mActivityList) {
            if (activity.getClass().equals(activityClass)) {
                return true;
            }
        }
        return false;
    }
    /**
     * @param {@link Activity} 指定的Activity实例是否存活
     * @return
     */
    public boolean activityInstanceIsLive(Activity activity) {
        if (mActivityList == null) {
            Timber.tag(TAG).w("mActivityList == null when activityInstanceIsLive(Activity)");
            return false;
        }
        return mActivityList.contains(activity);
    }


    /**
     * 删除集合里的指定位置的 {@link Activity}
     *
     * @param location
     */
    public Activity removeActivity(int location) {
        if (mActivityList == null) {
            Timber.tag(TAG).w("mActivityList == null when removeActivity(int)");
            return null;
        }
        synchronized (AppManager.class) {
            if (location > 0 && location < mActivityList.size()) {
                return mActivityList.remove(location);
            }
        }
        return null;
    }

    /**
     * @param activity 删除集合里的指定的Activity 实例
     */
    public void removeActivity(Activity activity) {
        if (mActivityList == null) {
            Timber.tag(TAG).w("mActivityList == null when removeActivity(Activity)");
            return;
        }
        synchronized (AppManager.class) {
            if (mActivityList.contains(activity)) {
                mActivityList.remove(activity);
            }
        }
    }
    /**
     * 添加 {@link Activity} 到集合
     */
    public void addActivity(Activity activity) {
        synchronized (AppManager.class) {
            List<Activity> activities = getActivityList();
            if (!activities.contains(activity)) {
                activities.add(activity);
            }
        }
    }

    /**
     * 获取所有存活的activity集合
     */
    public List<Activity> getActivityList(){
        if (mActivityList == null){
            mActivityList = new LinkedList<>();
        }
        return mActivityList;
    }

    /**
     * 获取最近启动的一个 {@link Activity}, 此方法不保证获取到的 {@link Activity} 正处于前台可见状态
     * 即使 App 进入后台或在这个 {@link Activity} 中打开一个之前已经存在的 {@link Activity}, 这时调用此方法
     * 还是会返回这个最近启动的 {@link Activity}, 因此基本不会出现 {@code null} 的情况
     * 比较适合大部分的使用场景, 如 startActivity
     * <p>
     * Tips: mActivityList 容器中的顺序仅仅是 Activity 的创建顺序, 并不能保证和 Activity 任务栈顺序一致
     *
     * @return
     */
    public Activity getTopActivity(){
        if (mActivityList == null){
            Timber.tag(TAG).w("mActivityList == null when getTopActivity");
            return null;
        }
        return mActivityList.size()>0 ? mActivityList.get(mActivityList.size() - 1): null;
    }
    //---------------------------------------------------------------------------------杀死Activity--------------------------------------

    /**
     * @param activityClass 关闭指定的Activity 的class 的所有的实例
     */
    public void killActivity(Class<?> activityClass) {
        if (mActivityList == null) {
            Timber.tag(TAG).w("mActivityList == null when killActivity(Class)");
            return;
        }
        synchronized (AppManager.class) {
            Iterator<Activity> iterator = getActivityList().iterator();
            while (iterator.hasNext()) {
                Activity next = iterator.next();

                if (next.getClass().equals(activityClass)) {
                    iterator.remove();
                    next.finish();
                }
            }
        }
    }

    /**
     * 杀掉所有Activity
     */
    public void killAll(){
//        while (getActivityList().size() != 0) { //此方法只能兼容LinkedList
//            getActivityList().remove(0).finish();
//        }
        synchronized (AppManager.class){
            Iterator<Activity> iterator = getActivityList().iterator();
            while (iterator.hasNext()){
                Activity next = iterator.next();
                iterator.remove();
                next.finish();
            }
        }
    }
    /**
     * 杀死所有Activity保留一个(Class方式)
     */
    public void killAllStillOne(Class<?>... stillOneActivity){
        List<Class<?>> stillList = Arrays.asList(stillOneActivity);//将其转换为list
        synchronized (AppManager.class){
            Iterator<Activity> iterator = getActivityList().iterator();
            while (iterator.hasNext()){
                Activity next = iterator.next();
                if (stillList.contains(next.getClass()))
                    continue;
                iterator.remove();
                next.finish();
            }
        }
    }

    /**
     * 杀死所有Activity保留一个(Activity完整路径名方式)
     */
    public void killAllStillOne(String... stillOneName){
        List<String> nameList = Arrays.asList(stillOneName);//路径的Activity集合
        synchronized (AppManager.class){
            Iterator<Activity> iterator = getActivityList().iterator();
            while (iterator.hasNext()){
                Activity next = iterator.next();
                if (nameList.contains(next.getClass().getName()))
                    continue;
                iterator.remove();
                next.finish();
            }
        }
    }

    /**
     * 退出程序
     */
    public void appExit(){
        try {
            killAll();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放资源
     */
    public void release(){
        EventBus.getDefault().unregister(this);
        mActivityList.clear();
        mHandleListener = null;
        mActivityList = null;
        mCurrentActivity = null;
        mApplication = null;
    }
    //---------------------------------------------------------------------------------显示文本--------------------------------------

    /**
     * 前台Activity显示文本
     * @param msg 消息
     * @param isLong 是否是常显示
     */
    public void showSnackBar(String msg,boolean isLong){
        if (getCurrentActivity() == null){
            Timber.tag(TAG).w("文本显示，mCurrentActivity == null");
            return;
        }
        View view = getCurrentActivity().getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar.make(view, msg, isLong ? Snackbar.LENGTH_LONG : Snackbar.LENGTH_SHORT).show();
    }
    /**
     * 接口
     */
    public interface HandleListener{
        void handleMessage(AppManager appManager,Message message);
    }

}
