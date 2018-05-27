package kata.currencyconverter.validation;

import kata.currencyconverter.model.InputParameter;

public interface InputValidator {

    ValidationResult validate(InputParameter input);
}
