package com.nothingatall544.sunface.view;

import android.graphics.Paint;
import android.support.annotation.NonNull;

import com.nothingatall544.sunface.model.ColorPallet;
import com.nothingatall544.sunface.model.SunState;

import java.net.PasswordAuthentication;

/**
 * Created by derp on 7/21/2015.
 */
public interface iSunFace {
    void setState(@NonNull SunState sunState, float percent);
    void setSunColors(ColorPallet colorPallet);
}