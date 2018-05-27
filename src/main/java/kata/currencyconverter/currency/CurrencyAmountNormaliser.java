package kata.currencyconverter.currency;

import kata.currencyconverter.model.CurrencyAmount;

public interface CurrencyAmountNormaliser {

    CurrencyAmount normalise(CurrencyAmount amount);
}
