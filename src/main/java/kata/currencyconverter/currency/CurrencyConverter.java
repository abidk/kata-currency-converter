package kata.currencyconverter.currency;

import kata.currencyconverter.exchange.ExchangeRates;
import kata.currencyconverter.model.InputParameter;

public interface CurrencyConverter {

    ConvertedResult convert(InputParameter value, ExchangeRates rates);

}
