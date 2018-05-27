package kata.currencyconverter.currency;

import kata.currencyconverter.model.CountryName;
import kata.currencyconverter.model.CurrencyAmount;
import kata.currencyconverter.model.CurrencyName;

public class ConvertedResult {

    private final CountryName countryName;
    private final CurrencyName currencyName;
    private final CurrencyAmount currencyAmount;

    public ConvertedResult(CountryName countryName, CurrencyName currencyName, CurrencyAmount currencyAmount) {
        this.countryName = countryName;
        this.currencyName = currencyName;
        this.currencyAmount = currencyAmount;
    }

    public CountryName getCountryName() {
        return countryName;
    }

    public CurrencyName getCurrencyName() {
        return currencyName;
    }

    public CurrencyAmount getCurrencyAmount() {
        return currencyAmount;
    }

    @Override
    public String toString() {
        return "Country=" + countryName + " Currency=" + currencyName + " Amount=" + currencyAmount;
    }
}
