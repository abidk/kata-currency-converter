package kata.currencyconverter.currency;

import kata.currencyconverter.exchange.ExchangeRate;
import kata.currencyconverter.exchange.ExchangeRates;
import kata.currencyconverter.model.CountryName;
import kata.currencyconverter.model.CurrencyAmount;
import kata.currencyconverter.model.CurrencyName;
import kata.currencyconverter.model.InputParameter;

public class CurrencyConverterImpl implements CurrencyConverter {

    private final CurrencyAmountNormaliser normaliser;

    public CurrencyConverterImpl(CurrencyAmountNormaliser normaliser) {
        this.normaliser = normaliser;
    }

    public ConvertedResult convert(InputParameter input, ExchangeRates rates) {
        ExchangeRate targetExchange = getTargetCurrencyExchangeRate(input, rates);

        CurrencyAmount normalisedAmount = exchangeCurrencyAndNormaliseAmount(input, targetExchange);
        CountryName countryName = targetExchange.getCountryName();
        CurrencyName currencyName = targetExchange.getCurrencyName();
        return new ConvertedResult(countryName, currencyName, normalisedAmount);
    }

    private ExchangeRate getTargetCurrencyExchangeRate(InputParameter input, ExchangeRates rates) {
        return rates.getRate(input.getTarget());
    }

    private CurrencyAmount exchangeCurrencyAndNormaliseAmount(InputParameter input, ExchangeRate targetExchange) {
        CurrencyAmount exchangedAmount = targetExchange.exchange(input.getAmount());
        return normaliser.normalise(exchangedAmount);
    }
}
