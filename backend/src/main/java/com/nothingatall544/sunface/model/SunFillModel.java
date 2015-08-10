package com.nothingatall544.sunface.model;

import android.util.Log;

import java.util.Calendar;

/**
 * Created by derp on 7/21/2015.
 */
public class SunFillModel implements iSunFillModel {
    private Calendar mCalendar;

    private int mHour;
    private int mMinute;

    @Override
    public void syncTime() {
        mCalendar = Calendar.getInstance();
        mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        mMinute = mCalendar.get(Calendar.MINUTE);
        Log.d("SunFace", String.format("Time %d:%d", mHour, mMinute));
    }

    @Override
    public SunState getSunState() {
        return SunState.getSunState(mHour);
    }

    @Override
    public float getFillPercent() {
        return calcPercent(mHour, mMinute);
    }

    private float calcPercent(int hour, int minute){
        final int scaledHour = hour % 3;
        final int time = ((scaledHour * 60) + minute);
        final float percent = (time / 180.0f);
        Log.d("SunFace", String.format("minute %d, time %d, percent %f", minute, time, percent));
        if ((hour % 6) < 3){
            return 1 - percent;
        }
        return percent;
    }
}
