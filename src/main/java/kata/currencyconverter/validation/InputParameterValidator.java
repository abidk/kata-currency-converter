package kata.currencyconverter.validation;

import kata.currencyconverter.model.InputParameter;

import java.util.ArrayList;
import java.util.List;

public class InputParameterValidator implements InputValidator {

    private final List<InputValidator> validators = new ArrayList<>();

    public void addValidator(InputValidator validator) {
        validators.add(validator);
    }

    public ValidationResult validate(InputParameter input) {
        ValidationResults validationResults = validateInputData(input);

        return aggregateResults(validationResults);
    }

    private ValidationResult aggregateResults(ValidationResults validationResults) {
        ValidationResult result = new ValidationResult();
        if (validationResults.hasError()) {
            result.setErrorMessage(validationResults.getErrorMessage());
        }
        return result;
    }

    private ValidationResults validateInputData(InputParameter input) {
        ValidationResults results = new ValidationResults();
        for (InputValidator validator : validators) {
            ValidationResult validationResult = validator.validate(input);
            results.add(validationResult);
        }
        return results;
    }
}
