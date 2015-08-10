package com.nothingatall544.sunface.model;

import com.google.common.collect.ImmutableMap;
import com.nothingatall544.backend.R;

import java.util.Map;

/**
 * Created by derp on 8/9/2015.
 */
public class SunColorModel {
    private final static Map<SunState, ColorPallet> COLOR_PALLET_MAP =
            ImmutableMap.<SunState, ColorPallet>builder()
                    .put(SunState.MIDNIGHT,      new ColorPallet(R.color.night_dark,  R.color.moon,        R.color.moon))
                    .put(SunState.EARLY_MORNING, new ColorPallet(R.color.night_dark,  R.color.moon,        R.color.night_dark))
                    .put(SunState.DAWN,          new ColorPallet(R.color.sun,         R.color.night_dark,  R.color.night_dark))
                    .put(SunState.MORNING,       new ColorPallet(R.color.sun,         R.color.night_dark,  R.color.sun))
                    .put(SunState.NOON,          new ColorPallet(R.color.night_light, R.color.sun,         R.color.sun))
                    .put(SunState.AFTER_NOON,    new ColorPallet(R.color.night_light, R.color.sun,         R.color.night_light))
                    .put(SunState.DUSK,          new ColorPallet(R.color.moon,        R.color.night_light, R.color.night_light))
                    .put(SunState.EVENING,       new ColorPallet(R.color.moon,        R.color.night_light, R.color.moon))
                    .build();

    public ColorPallet getColorPallet(SunState sunState) {
        return COLOR_PALLET_MAP.get(sunState);
    }
}
