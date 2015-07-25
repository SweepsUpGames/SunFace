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
import android.support.annotation.NonNull;
import android.support.wearable.watchface.CanvasWatchFaceService;
import android.util.Log;
import android.view.SurfaceHolder;

import com.google.common.collect.ImmutableMap;
import com.nothingatall544.sunface.model.SunState;
import com.nothingatall544.sunface.presenter.SunPresenter;
import com.nothingatall544.sunface.view.iSunFace;

import java.util.Map;

public class SunFace extends CanvasWatchFaceService {
    private static final Map<SunState, Integer> LEFT_COLOR_MAP =
            ImmutableMap.<SunState, Integer>builder()
                    .put(SunState.MIDNIGHT, R.color.night_dark)
                    .put(SunState.EARLY_MORNING, R.color.night_dark)
                    .put(SunState.DAWN, R.color.sun)
                    .put(SunState.MORNING, R.color.sun)
                    .put(SunState.NOON, R.color.night_light)
                    .put(SunState.AFTER_NOON, R.color.night_light)
                    .put(SunState.DUSK, R.color.moon)
                    .put(SunState.EVENING, R.color.moon)
                    .build();

    private static final Map<SunState, Integer> RIGHT_COLOR_MAP =
            ImmutableMap.<SunState, Integer>builder()
                    .put(SunState.MIDNIGHT, R.color.moon)
                    .put(SunState.EARLY_MORNING, R.color.moon)
                    .put(SunState.DAWN, R.color.night_dark)
                    .put(SunState.MORNING, R.color.night_dark)
                    .put(SunState.NOON, R.color.sun)
                    .put(SunState.AFTER_NOON, R.color.sun)
                    .put(SunState.DUSK, R.color.night_light)
                    .put(SunState.EVENING, R.color.night_light)
                    .build();

    private static final Map<SunState, Integer> CENTER_COLOR_MAP =
            ImmutableMap.<SunState, Integer>builder()
                    .put(SunState.MIDNIGHT, R.color.moon)
                    .put(SunState.EARLY_MORNING, R.color.night_dark)
                    .put(SunState.DAWN, R.color.night_dark)
                    .put(SunState.MORNING, R.color.sun)
                    .put(SunState.NOON, R.color.sun)
                    .put(SunState.AFTER_NOON, R.color.night_light)
                    .put(SunState.DUSK, R.color.night_light)
                    .put(SunState.EVENING, R.color.moon)
                    .build();

    @Override
    public Engine onCreateEngine() {
        return new Engine();
    }

    private class Engine extends CanvasWatchFaceService.Engine implements iSunFace {
        private Resources mResources;
        private SunPresenter mPresenter;
        private SunState mSunState;
        private double mPercent;

        private Paint mBackground;

        @Override
        public void onCreate(SurfaceHolder holder) {
            super.onCreate(holder);
            mPresenter = new SunPresenter();
            mResources = getResources();
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
            if(mSunState == null){
                mPresenter.updateSunFace();
                return;
            }
            final float midpoint = bounds.width() / 2;
            final float drawDistance = (float) (midpoint * mPercent);

            canvas.drawRect(bounds, mBackground);
            // left half
            final Paint leftPaint = new Paint();
            leftPaint.setColor(mResources.getColor(LEFT_COLOR_MAP.get(mSunState)));
            canvas.drawArc(0, 0, bounds.right, bounds.bottom, 90, 180, true, leftPaint);
            // right half
            final Paint rightPaint = new Paint();
            rightPaint.setColor(mResources.getColor(RIGHT_COLOR_MAP.get(mSunState)));
            canvas.drawArc(0, 0, bounds.right, bounds.bottom, 270, 180, true, rightPaint);
            // top oval
            final Paint centerPaint = new Paint();
            centerPaint.setColor(mResources.getColor(CENTER_COLOR_MAP.get(mSunState)));
            canvas.drawOval((midpoint - drawDistance), 0, (midpoint + drawDistance), bounds.bottom, centerPaint);
        }

        @Override
        public void setState(@NonNull SunState sunState, float percent) {
            mSunState = sunState;
            mPercent = percent;
            invalidate();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            mPresenter.removeSunFace();
        }
    }
}
