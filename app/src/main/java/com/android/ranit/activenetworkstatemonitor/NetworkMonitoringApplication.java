package com.android.ranit.activenetworkstatemonitor;

import android.app.Application;
import android.util.Log;

public class NetworkMonitoringApplication extends Application {
    public static final String TAG = NetworkMonitoringApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() called");
    }
}
