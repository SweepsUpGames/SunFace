package com.nothingatall544.sunface.model;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * Created by derp on 7/21/2015.
 */
public enum SunState {
    MIDNIGHT,
    EARLY_MORNING,
    DAWN,
    MORNING,
    NOON,
    AFTER_NOON,
    DUSK,
    EVENING;

    private static final Map<Integer, SunState> TIME_STATE_LOOKUP =
            ImmutableMap.<Integer, SunState>builder()
                    .put(0, MIDNIGHT)
                    .put(1, EARLY_MORNING)
                    .put(2, DAWN)
                    .put(3, MORNING)
                    .put(4, NOON)
                    .put(5, AFTER_NOON)
                    .put(6, DUSK)
                    .put(7, EVENING)
                    .build();

    public static SunState getSunState(int hours){
        return TIME_STATE_LOOKUP.get(hours/3);
    }
}
