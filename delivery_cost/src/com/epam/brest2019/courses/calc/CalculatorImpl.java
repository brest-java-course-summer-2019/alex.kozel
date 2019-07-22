package com.epam.brest2019.courses.calc;

import java.math.BigDecimal;

public class CalculatorImpl implements Calculator {

    public BigDecimal calc(BigDecimal weight, BigDecimal distance, BigDecimal pricePerKg, BigDecimal pricePerKm) {
        return weight.multiply(pricePerKg).add(distance.multiply(pricePerKm));
    }

}
