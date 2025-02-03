package com.join.core.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtil {
    public static double round(int newScale, double value) {
        return BigDecimal.valueOf(value).setScale(newScale, RoundingMode.HALF_UP).doubleValue();
    }
}
