package kata.currencyconverter.model;

import java.math.BigDecimal;

public final class CurrencyAmount {

    private static final String TWO_DECIMAL_NUMBER_REGEX = "^\\d+\\.\\d{2}$";

    private final String value;

    public CurrencyAmount(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public BigDecimal getValueAsNumber() {
        return new BigDecimal(value);
    }

    public boolean isTwoDecimalNumber() {
        return value.matches(TWO_DECIMAL_NUMBER_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }
}
