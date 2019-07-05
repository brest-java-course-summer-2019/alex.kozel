package Action;

import java.math.BigDecimal;

public class CorrectValue implements EnteredValue {

    private BigDecimal value;

    public CorrectValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public Types getType() {
        return Types.VALUE;
    }

}