package kata.currencyconverter.model;

import java.math.BigDecimal;

public final class CurrencyRate {

    private final BigDecimal rate;

    public CurrencyRate(String rate) {
        this.rate = new BigDecimal(rate);
    }

    public BigDecimal getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return rate.toPlainString();
    }
}
