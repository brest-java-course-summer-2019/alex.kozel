package com.epam.brest2019.courses.selector;

import java.math.BigDecimal;
import java.util.Map;

public interface ValueSelector {

    BigDecimal selectValue(Map<Integer, BigDecimal> valuesMap, BigDecimal targetValue);

}
