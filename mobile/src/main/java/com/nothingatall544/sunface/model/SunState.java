package com.nothingatall544.sunface.model;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * todo add colors to states
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

    public static final Map<Integer, SunState> TIME_STATE_LOOKUP =
            ImmutableMap.<Integer, SunState>builder()
                    .put(0, MIDNIGHT).put(1, MIDNIGHT).put(2, MIDNIGHT)
                    .put(3, EARLY_MORNING).put(4, EARLY_MORNING).put(5, EARLY_MORNING)
                    .put(6, DAWN).put(7, DAWN).put(8, DAWN)
                    .put(9, MORNING).put(10, MORNING).put(11, MORNING)
                    .put(12, NOON).put(13, NOON).put(14, NOON)
                    .put(15, AFTER_NOON).put(16, AFTER_NOON).put(17, AFTER_NOON)
                    .put(18, DUSK).put(19, DUSK).put(20, DUSK)
                    .put(21, EVENING).put(22, EVENING).put(23, EVENING)
                    .build();
}
