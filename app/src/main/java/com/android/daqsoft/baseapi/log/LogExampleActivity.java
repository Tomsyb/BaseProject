package com.android.daqsoft.baseapi.log;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.daqsoft.baseproject.R;
import com.android.daqsoft.log.YLog;

/**
 * 日志打印的工具页面（全部用法）
 */
public class LogExampleActivity extends AppCompatActivity {
    private static final String LOG_MSG = "KLog is a so cool Log Tool!";
    private static String JSON;
    private static String STRING;
    private static final String TAG = "YLog";
    private static String XML = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><!--  Copyright w3school.com.cn --><note><to>George</to><from>John</from><heading>Reminder</heading><body>Don't forget the meeting!</body></note>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_example);
        initData();
    }

    private void initData() {
        JSON = getResources().getString(R.string.json);
        STRING = getString(R.string.string_long);
    }

    public void logTraceStack(View view) {
        TestTraceUtil.testTrace();
    }

    public void logDebug(View view) {
        YLog.debug();
        YLog.debug("This is a debug message");
        YLog.debug("DEBUG", "params1", "params2", this);
    }

    public void log(View view) {
        YLog.v();
        YLog.d();
        YLog.i();
        YLog.w();
        YLog.e();
        YLog.a();
    }

    public void logWithNull(View view) {
        YLog.v(null);
        YLog.d(null);
        YLog.i(null);
        YLog.w(null);
        YLog.e(null);
        YLog.a(null);
    }

    public void logWithMsg(View view) {
        YLog.v(LOG_MSG);
        YLog.d(LOG_MSG);
        YLog.i(LOG_MSG);
        YLog.w(LOG_MSG);
        YLog.e(LOG_MSG);
        YLog.a(LOG_MSG);
    }

    public void logWithTag(View view) {
        YLog.v(TAG, LOG_MSG);
        YLog.d(TAG, LOG_MSG);
        YLog.i(TAG, LOG_MSG);
        YLog.w(TAG, LOG_MSG);
        YLog.e(TAG, LOG_MSG);
        YLog.a(TAG, LOG_MSG);
    }

    public void logWithLong(View view) {
        YLog.d(TAG, STRING);
    }

    public void logWithParams(View view) {
        YLog.v(TAG, LOG_MSG, "params1", "params2", this);
        YLog.d(TAG, LOG_MSG, "params1", "params2", this);
        YLog.i(TAG, LOG_MSG, "params1", "params2", this);
        YLog.w(TAG, LOG_MSG, "params1", "params2", this);
        YLog.e(TAG, LOG_MSG, "params1", "params2", this);
        YLog.a(TAG, LOG_MSG, "params1", "params2", this);
    }


    public void logWithJson(View view) {
        YLog.json("12345");
        YLog.json(null);
        YLog.json(JSON);
    }

    public void logWithLongJson(View view) {
        YLog.json(JSON);
    }

    public void logWithJsonTag(View view) {
        YLog.json(TAG, JSON);
    }

    public void logWithFile(View view) {
        YLog.file(Environment.getExternalStorageDirectory(), JSON);
        YLog.file(TAG, Environment.getExternalStorageDirectory(), JSON);
        YLog.file(TAG, Environment.getExternalStorageDirectory(), "test.txt", JSON);
    }

    public void logWithXml(View view) {
        YLog.xml("12345");
        YLog.xml(null);
        YLog.xml(XML);
    }
}
