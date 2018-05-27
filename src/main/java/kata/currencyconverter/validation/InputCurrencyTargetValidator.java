package kata.currencyconverter.validation;

import kata.currencyconverter.model.CurrencyCode;
import kata.currencyconverter.model.InputParameter;

public class InputCurrencyTargetValidator implements InputValidator {

    public ValidationResult validate(InputParameter input) {
        ValidationResult result = new ValidationResult();

        CurrencyCode target = input.getTarget();
        if (!target.isValidISO4217Code()) {
            result.setErrorMessage("Target value '" + target + "' is not a valid ISO 4217 code.");
        }

        return result;
    }

}
