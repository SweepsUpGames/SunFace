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
import android.view.SurfaceHolder;

import com.nothingatall544.sunface.model.SunState;
import com.nothingatall544.sunface.presenter.SunPresenter;
import com.nothingatall544.sunface.view.iSunFace;

public class SunFace extends CanvasWatchFaceService {
    @Override
    public Engine onCreateEngine() {
        return new Engine();
    }

    private class Engine extends CanvasWatchFaceService.Engine implements iSunFace {
        private SunPresenter mPresenter;
        private SunState mSunState;
        private double mPercent;

        private Paint mBackground;
        private Paint mSun;
        private Paint mDusk;

        @Override
        public void onCreate(SurfaceHolder holder) {
            super.onCreate(holder);
            mPresenter = new SunPresenter();
            Resources r = getResources();
            mBackground = new Paint();
            mBackground.setColor(r.getColor(R.color.background));
            mSun = new Paint();
            mSun.setColor(r.getColor(R.color.sun));
            mDusk = new Paint();
            mDusk.setColor(r.getColor(R.color.moon));

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
            //todo draw the watch face
            final float midpoint = bounds.width()/2;
            final float drawDistance = (float)(midpoint * mPercent);

            canvas.drawRect(bounds, mBackground);
            // left half
            canvas.drawArc(0, 0, bounds.right, bounds.bottom, 270, 180, true, mDusk);
            canvas.drawArc(0, 0, bounds.right, bounds.bottom, 90, 180, true, mSun);
            // right half
            // top oval

            canvas.drawOval((midpoint - drawDistance), 0, (midpoint + drawDistance), bounds.bottom, mSun);
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
