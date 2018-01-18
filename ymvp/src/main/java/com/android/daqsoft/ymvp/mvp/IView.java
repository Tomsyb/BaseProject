package com.android.daqsoft.ymvp.mvp;

import android.content.Intent;

/**
 * Created by yanbo on 2018/1/18.
 * 每个view都要实现此类
 */

public interface IView {
    void showLoading();
    void hideLoading();
    void showMsg(String msg);
    void launchActivity(Intent intent);
    void killMyself();
}
