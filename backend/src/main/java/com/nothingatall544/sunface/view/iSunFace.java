package com.nothingatall544.sunface.view;

import android.support.annotation.NonNull;

import com.nothingatall544.sunface.model.SunState;

/**
 * Created by derp on 7/21/2015.
 */
public interface iSunFace {
    public void setState(@NonNull SunState sunState, double percent);
}