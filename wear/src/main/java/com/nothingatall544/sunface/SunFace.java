/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nothingatall544.sunface;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.wearable.watchface.CanvasWatchFaceService;
import android.view.SurfaceHolder;

import com.google.common.collect.ImmutableMap;
import com.nothingatall544.sunface.model.ColorPallet;
import com.nothingatall544.sunface.model.SunState;
import com.nothingatall544.sunface.presenter.SunPresenter;
import com.nothingatall544.sunface.view.iSunFace;

import java.util.Map;

public class SunFace extends CanvasWatchFaceService {
    @Override
    public Engine onCreateEngine() {
        return new Engine();
    }

    private class Engine extends CanvasWatchFaceService.Engine implements iSunFace {
        private final static boolean USE_CENTER = true;
        private Resources mResources;
        private SunPresenter mPresenter;

        private RectF mFullBounds;

        private double mPercent = -1.0;

        private Paint mLeftPaint;
        private Paint mRightPaint;
        private Paint mMiddlePaint;
        private Paint mBackground;

        @Override
        public void onCreate(SurfaceHolder holder) {
            super.onCreate(holder);
            mPresenter = new SunPresenter();
            mResources = getResources();

            mLeftPaint = new Paint();
            mRightPaint = new Paint();
            mMiddlePaint = new Paint();
            mBackground = new Paint();
            mBackground.setColor(mResources.getColor(R.color.background));
        }

        @Override
        public void onSurfaceCreated(SurfaceHolder holder) {
            super.onSurfaceCreated(holder);
            mPresenter.setSunFace(this);
        }

        @Override
        public void onTimeTick() {
            super.onTimeTick();
            mPresenter.updateSunFace();
        }

        @Override
        public void onDraw(Canvas canvas, Rect bounds) {
            if (initialize(bounds)) {
                return;
            }
            final RectF middleBounds = getMiddleBounds(bounds);

            // draw background
            canvas.drawRect(mFullBounds, mBackground);

            // left half
            canvas.drawArc(mFullBounds, 90, 180, USE_CENTER, mLeftPaint);

            // right half
            canvas.drawArc(mFullBounds, 270, 180, USE_CENTER, mRightPaint);

            // top oval
            canvas.drawOval(middleBounds, mMiddlePaint);
        }

        @Override
        public void setState(@NonNull SunState sunState, float percent) {
            mPercent = percent;
            invalidate();
        }

        @Override
        public void setSunColors(ColorPallet colorPallet) {
            mLeftPaint.setColor(mResources.getColor(colorPallet.getLeftColor()));
            mRightPaint.setColor(mResources.getColor(colorPallet.getRightColor()));
            mMiddlePaint.setColor(mResources.getColor(colorPallet.getMiddleColor()));
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            mPresenter.removeSunFace();
        }

        private boolean initialize(Rect bounds){
            if (mPercent != -1.0){
                return false;
            }

            mFullBounds = new RectF(bounds.left, bounds.top, bounds.right, bounds.bottom);
            mPresenter.updateSunFace();
            return true;
        }

        private RectF getMiddleBounds(Rect bounds){
            final float midpoint = bounds.width() / 2;
            final float drawDistance = (float) (midpoint * mPercent);
            return new RectF(
                    (midpoint - drawDistance), // left
                    bounds.top,                // top
                    (midpoint + drawDistance), // right
                    bounds.bottom);            // bottom
        }
    }
}
