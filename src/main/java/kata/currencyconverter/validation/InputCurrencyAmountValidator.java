package kata.currencyconverter.validation;

import kata.currencyconverter.model.CurrencyAmount;
import kata.currencyconverter.model.InputParameter;

public class InputCurrencyAmountValidator implements InputValidator {

    public ValidationResult validate(InputParameter input) {
        ValidationResult result = new ValidationResult();

        CurrencyAmount amount = input.getAmount();
        if (!amount.isTwoDecimalNumber()) {
            result.setErrorMessage("Value '" + amount + "' is not a valid 2 decimal number.");
        }

        return result;
    }

}
