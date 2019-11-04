package com.kks.trainingapp;

import android.app.Application;

import com.kks.trainingapp.custom_control.AndroidCommonSetup;

/**
 * Created by kaungkhantsoe on 2019-10-18.
 **/
public class RootApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidCommonSetup.getInstance().init(getApplicationContext());

    }
}
