package com.nothingatall544.sunface.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.common.base.Preconditions;
import com.nothingatall544.sunface.model.ColorPallet;
import com.nothingatall544.sunface.model.SunColorModel;
import com.nothingatall544.sunface.model.SunFillModel;
import com.nothingatall544.sunface.model.SunState;
import com.nothingatall544.sunface.model.iSunFillModel;
import com.nothingatall544.sunface.view.iSunFace;

/**
 * Created by derp on 7/21/2015.
 */
public class SunPresenter {
    private final iSunFillModel mSunFaceModel;
    private final SunColorModel mSunColorModel;
    private iSunFace mSunFace;

    public SunPresenter() {
        mSunFaceModel = new SunFillModel();
        mSunColorModel = new SunColorModel();
    }

    public void setSunFace(@NonNull iSunFace sunFace){
        mSunFace = Preconditions.checkNotNull(sunFace, "sunFace");
    }

    public void removeSunFace(){
        mSunFace = null;
    }

    public void updateSunFace(){
        mSunFaceModel.syncTime();
        final SunState sunState = mSunFaceModel.getSunState();
        final float fillPercent = mSunFaceModel.getFillPercent();
        final ColorPallet colorPallet = mSunColorModel.getColorPallet(sunState);
        Log.d("SunFace", String.format("Updated to %s with %f", sunState.name(), fillPercent));
        if (mSunFace != null) {
            mSunFace.setSunColors(colorPallet);
            mSunFace.setState(sunState, fillPercent);
        }
    }
}
