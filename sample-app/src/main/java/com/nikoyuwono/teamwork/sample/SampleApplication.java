package com.nikoyuwono.teamwork.sample;

import android.app.Application;

import com.nikoyuwono.teamwork.Teamwork;

/**
 * Created by niko-yuwono on 17/01/09.
 */

public class SampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Teamwork.initialize(this);
    }
}
