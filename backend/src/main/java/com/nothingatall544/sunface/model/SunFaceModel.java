package com.nothingatall544.sunface.model;

import android.text.format.Time;

/**
 * Created by derp on 7/21/2015.
 */
public class SunFaceModel implements iSunFaceModel {
    private final Time mTime;

    public SunFaceModel(){
        mTime = new Time();
    }

    @Override
    public void syncTime() {
        mTime.setToNow();
    }

    @Override
    public SunState getSunState() {
        return SunState.getSunState(mTime.hour);
    }

    @Override
    public double getFillPercent() {
        return calcPercent(mTime.minute);
    }

    private double calcPercent(int minute){
        return ((minute % 30) / 30.0);
    }
}
