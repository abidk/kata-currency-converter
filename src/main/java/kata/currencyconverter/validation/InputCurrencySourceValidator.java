package kata.currencyconverter.validation;

import kata.currencyconverter.model.CurrencyCode;
import kata.currencyconverter.model.InputParameter;

public class InputCurrencySourceValidator implements InputValidator {

    public ValidationResult validate(InputParameter input) {
        ValidationResult result = new ValidationResult();

        CurrencyCode source = input.getSource();
        if (!source.isValidISO4217Code()) {
            result.setErrorMessage("Source value '" + source + "' is not a valid ISO 4217 code.");
        }

        return result;
    }

}
