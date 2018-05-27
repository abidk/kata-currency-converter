package kata.currencyconverter.validation;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ValidationResultTest {

    private ValidationResult validationResult;
    private boolean actualHasError;
    private String actualErrorMessage;

    @Test
    public void givenValidationThatHasErrorThenResultHasError() {
        givenValidationResultWithAnError("error");

        whenCheckingHasError();

        thenResultHasError();
        thenResultHasErrorMessage("error");
    }

    @Test
    public void givenValidationWithNoErrorThenResultHasNoError() {
        givenValidationResultWithNoError();

        whenCheckingHasError();

        thenResultHasNoError();
        thenResultHasErrorMessage(null);
    }

    private void thenResultHasErrorMessage(String error) {
        assertThat(actualErrorMessage, is(error));
    }

    private void thenResultHasNoError() {
        assertThat(actualHasError, is(false));
    }

    private void thenResultHasError() {
        assertThat(actualHasError, is(true));
    }

    private void whenCheckingHasError() {
        actualHasError = validationResult.hasError();
        actualErrorMessage = validationResult.getErrorMessage();
    }

    private void givenValidationResultWithAnError(String error) {
        validationResult = new ValidationResult();
        validationResult.setErrorMessage(error);
    }

    private void givenValidationResultWithNoError() {
        validationResult = new ValidationResult();
    }
}
