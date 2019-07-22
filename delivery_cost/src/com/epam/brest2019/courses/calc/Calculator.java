package com.epam.brest2019.courses.calc;

import java.math.BigDecimal;

/**
 * The interface to calculate the cost of shipping
 */
public interface Calculator {

    BigDecimal calc(BigDecimal weight, BigDecimal distance, BigDecimal pricePerKg, BigDecimal pricePerKm);

}
