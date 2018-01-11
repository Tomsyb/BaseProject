## AppManager的API

## 类简介

1. 单列类(@Singleton注解)
2. 管理Activity生命周期
3. Eventbus和其对象操控对应方法

## Eventbus管理
Appmanager构造方法注册eventbus
远程操控
- onReceive(Message msg),msg操控Activity（让外部可扩展新的事件）
1. START_ACTIVITY:启动
2. KILLALL_ACTIVITY：杀死全部
3. EXIT_APP        :退出APP
4. SHOW_SNACKBAR   :显示SNACKBAR文本

## 本类方法

方法 | 定义
---|---
dispatchStart(Message msg) | 判断跳转类型(Intent Or Class)
startActivity(Intent intent) | intent跳转
startActivity(Class classActivity) | class跳转
getTopActivity() | row
killAll() | 杀死所有Activity（使用synchronized和iterator循环杀死）
killAllStillOne(Class/NameStr) | 杀死所有Activity排除指定的Activity(Class或路径)
appExit() | 退出程序
showSnackbar() | 让前台Activity显示文本
getCurrentActivity() | 获取前台Activity
release() | 释放资源
addActivity(Activity activity) | 添加Activity
removeActivity(Activity activity) | 删除Activity
removeActivity(int location) | 删除指定位置Activity
killActivity(Class<?> activityClass) | 关闭指定Activity所有实例
activityInstanceIsLive(Activity activity) | 指定Activity是否存活
activityClassIsLive(Class<?> activityClass) | 指定Activity是否存活，同一Activity可能有多个Class
findActivity(Class<?> activityClass) | 获取指定Activity class 的实例






