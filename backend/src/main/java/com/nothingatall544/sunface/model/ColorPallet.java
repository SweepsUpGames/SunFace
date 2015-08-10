package com.nothingatall544.sunface.model;

/**
 * Created by derp on 8/9/2015.
 */
public class ColorPallet {
    private final int mLeftColor;
    private final int mRightColor;
    private final int mMiddleColor;

    public ColorPallet (int leftColor,
                        int rightColor,
                        int middleColor){
        mLeftColor = leftColor;
        mRightColor = rightColor;
        mMiddleColor = middleColor;
    }

    public int getLeftColor() {
        return mLeftColor;
    }

    public int getRightColor() {
        return mRightColor;
    }

    public int getMiddleColor() {
        return mMiddleColor;
    }
}
