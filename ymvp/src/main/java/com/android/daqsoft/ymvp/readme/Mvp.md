## MVP包封装API

### IView(View层接口)

界面显示相关

- showLoading();显示加载框
- hideLoading();隐藏加载框
- showMesg(String msg);显示信息
- launchActivity(Intent intent);跳转
- killMyself();杀死自己

### IPresenter（P层）

- onStart();初始化操作
- onDestroy（);在Activity的onDestroy()默认调用这方法

### IModel(M层)

- onDestroy();在BasePresenter中onDestroy();默认调用

###　BasePresenter(基类Presenter)
