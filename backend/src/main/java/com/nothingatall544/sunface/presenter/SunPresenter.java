package com.nothingatall544.sunface.presenter;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.nothingatall544.sunface.model.SunFaceModel;
import com.nothingatall544.sunface.model.SunState;
import com.nothingatall544.sunface.model.iSunFaceModel;
import com.nothingatall544.sunface.view.iSunFace;

/**
 * Created by derp on 7/21/2015.
 */
public class SunPresenter {
    private final iSunFaceModel mSunFaceModel;
    private iSunFace mSunFace;

    public SunPresenter() {
        mSunFaceModel = new SunFaceModel();
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
        final double fillPercent = mSunFaceModel.getFillPercent();
        if (mSunFace != null) {
            mSunFace.setState(sunState, fillPercent);
        }
    }
}
