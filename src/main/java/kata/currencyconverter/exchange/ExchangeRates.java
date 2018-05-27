package kata.currencyconverter.exchange;

import kata.currencyconverter.model.CurrencyCode;

import java.util.HashMap;
import java.util.Map;

public class ExchangeRates {

    private final Map<CurrencyCode, ExchangeRate> rates = new HashMap<>();

    public void add(ExchangeRate rate) {
        rates.put(rate.getCurrencyCode(), rate);
    }

    public ExchangeRate getRate(CurrencyCode code) {
        return rates.get(code);
    }

    public Map<CurrencyCode, ExchangeRate> getRates() {
        return rates;
    }
}
