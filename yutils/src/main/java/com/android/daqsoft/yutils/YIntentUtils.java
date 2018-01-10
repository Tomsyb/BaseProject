package com.android.daqsoft.yutils;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by yanbo on 2018/1/10.
 */

public class YIntentUtils {
    /**
     * 获取其他应用的Intent
     *
     * @param packageName 包名
     * @param className   全类名
     * @return 意图
     */
    public static Intent getComponentNameIntent(String packageName, String className) {
        return getComponentNameIntent(packageName, className, null);
    }

    /**
     * 获取其他应用的Intent
     *
     * @param packageName 包名
     * @param className   全类名
     * @return 意图
     */
    public static Intent getComponentNameIntent(String packageName, String className, Bundle bundle) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (bundle != null) intent.putExtras(bundle);
        ComponentName cn = new ComponentName(packageName, className);
        intent.setComponent(cn);
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }
}
