package com.epam.brest2019.courses.selector;

import java.math.BigDecimal;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class SelectorFromMap implements ValueSelector {

    @Override
    public BigDecimal selectValue(Map<Integer, BigDecimal> valuesMap, BigDecimal targetValue) {
        SortedSet<Integer> sortedKeys = new TreeSet<>(valuesMap.keySet());
        Integer foundedKey = sortedKeys.first();
        for(Integer key : sortedKeys) {
            if (foundedKey >= targetValue.doubleValue()) {
                break;
            }
            foundedKey = key;
        }

        BigDecimal foundedValue = valuesMap.get(foundedKey);
        System.out.format("Selected interval for value %.2f is %d -> %.2f$%n", targetValue, foundedKey, foundedValue);
        return foundedValue;
    }
}
