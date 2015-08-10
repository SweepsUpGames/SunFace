package com.nothingatall544.sunface.model;

/**
 * Created by derp on 7/21/2015.
 */
public interface iSunFillModel {
    void syncTime();
    SunState getSunState();
    float getFillPercent();
}
