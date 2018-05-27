package kata.currencyconverter.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationResults {

    private final List<ValidationResult> results = new ArrayList<>();

    public String getErrorMessage() {
        StringBuilder errorMessage = new StringBuilder();
        for (ValidationResult result : results) {
            appendErrorMessage(errorMessage, result);
        }

        return errorMessage.toString();
    }

    public void add(ValidationResult result) {
        results.add(result);
    }

    public boolean hasError() {
        for (ValidationResult result : results) {
            if (result.hasError()) {
                return true;
            }
        }
        return false;
    }

    private void appendErrorMessage(StringBuilder errorMessage, ValidationResult result) {
        if (result.hasError()) {
            errorMessage.append(result.getErrorMessage());
            errorMessage.append("\n");
        }
    }
}