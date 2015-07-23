package com.nothingatall544.sunface.model;

import android.text.format.Time;
import android.util.Log;

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
    public float getFillPercent() {
        return calcPercent(mTime.minute);
    }

    private float calcPercent(int minute){
        //todo 30 minutes is not the max time, 3 hours is.  Scale based on that
        final int time = (minute % 30);
        final float percent = ((minute % 30) / 30.0f);
        Log.d("SunFace", String.format("minute %d, time %d, percent %f", minute, time, percent));
        return ((minute % 30) / 30.0f);
    }
}
