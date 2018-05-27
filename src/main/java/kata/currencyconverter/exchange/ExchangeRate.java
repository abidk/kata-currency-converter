package kata.currencyconverter.exchange;

import kata.currencyconverter.model.*;

import java.math.BigDecimal;

public class ExchangeRate {

    public final CountryName countryName;
    public final CurrencyCode currencyCode;
    public final CurrencyName currencyName;
    public final CurrencyRate currencyRate;

    public ExchangeRate(CountryName countryName, CurrencyName currencyName, CurrencyCode currencyCode, CurrencyRate currencyRate) {
        this.countryName = countryName;
        this.currencyCode = currencyCode;
        this.currencyName = currencyName;
        this.currencyRate = currencyRate;
    }

    public CountryName getCountryName() {
        return countryName;
    }

    public CurrencyCode getCurrencyCode() {
        return currencyCode;
    }

    public CurrencyName getCurrencyName() {
        return currencyName;
    }

    public CurrencyAmount exchange(CurrencyAmount amount) {
        BigDecimal convertedAmount = exchangeInputAmount(amount);

        return new CurrencyAmount(convertedAmount.toPlainString());
    }

    private BigDecimal exchangeInputAmount(CurrencyAmount amount) {
        return currencyRate.getRate().multiply(amount.getValueAsNumber());
    }
}
