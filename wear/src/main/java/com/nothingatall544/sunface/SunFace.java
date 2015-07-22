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

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.wearable.watchface.CanvasWatchFaceService;
import android.view.SurfaceHolder;

import com.nothingatall544.sunface.model.SunState;
import com.nothingatall544.sunface.presenter.SunPresenter;
import com.nothingatall544.sunface.view.iSunFace;

/**
 * Analog watch face with a ticking second hand. In ambient mode, the second hand isn't shown. On
 * devices with low-bit ambient mode, the hands are drawn without anti-aliasing in ambient mode.
 */
public class SunFace extends CanvasWatchFaceService {
    @Override
    public Engine onCreateEngine() {
        return new Engine();
    }

    private class Engine extends CanvasWatchFaceService.Engine implements iSunFace {
        private SunPresenter mPresenter;
        private SunState mSunState;
        private double mPercent;

        @Override
        public void onCreate(SurfaceHolder holder) {
            super.onCreate(holder);
            mPresenter = new SunPresenter();
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
            // background
            // left half
            // right half
            // top oval
        }

        @Override
        public void setState(@NonNull SunState sunState, double percent) {
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
