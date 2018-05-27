package kata.currencyconverter.currency;

import kata.currencyconverter.model.CurrencyAmount;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyAmountTwoDecimalNormaliser implements CurrencyAmountNormaliser {

    @Override
    public CurrencyAmount normalise(CurrencyAmount amount) {
        BigDecimal newValue = roundCurrencyAmount(amount);

        return new CurrencyAmount(newValue.toPlainString());
    }

    private BigDecimal roundCurrencyAmount(CurrencyAmount currentAmount) {
        BigDecimal numberValue = currentAmount.getValueAsNumber();
        return numberValue.setScale(2, RoundingMode.HALF_EVEN);
    }
}
