package kata.currencyconverter.validation;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ValidationResultsTest {

    private ValidationResults results;

    @Before
    public void setUp() {
        results = new ValidationResults();
    }

    @Test
    public void givenValidationThatHasNoErrorShouldReturnNoErrorResult() {
        givenAnNoErrorResult();

        thenHasNoError();
        thenHasNoErrorMessage();
    }

    @Test
    public void givenValidationThatHasErrorShouldReturnCorrectResult() {
        givenAnErrorResult("some error");

        thenHasError();
        thenHasErrorMessage("some error\n");
    }

    @Test
    public void givenValidationThatHasMultipleErrorsShouldReturnCorrectErrorMessage() {
        givenAnErrorResult("some error1");
        givenAnErrorResult("some error2");

        thenHasError();
        thenHasErrorMessage("some error1\nsome error2\n");
    }

    @Test
    public void givenValidationThatHasACombinationOfErrorAndNoErrorShouldReturnCorrectErrorMessage() {
        givenAnNoErrorResult();
        givenAnErrorResult("some error1");
        givenAnNoErrorResult();
        givenAnErrorResult("some error2");

        thenHasError();
        thenHasErrorMessage("some error1\nsome error2\n");
    }

    private void thenHasNoErrorMessage() {
        assertThat(results.getErrorMessage(), is(""));
    }

    private void thenHasErrorMessage(String expectedValue) {
        assertThat(results.getErrorMessage(), is(expectedValue));
    }

    private void thenHasError() {
        assertThat(results.hasError(), is(true));
    }

    private void thenHasNoError() {
        assertThat(results.hasError(), is(false));
    }

    private void givenAnErrorResult(String errorMessage) {
        ValidationResult errorResult = new ValidationResult();
        errorResult.setErrorMessage(errorMessage);
        results.add(errorResult);
    }

    private void givenAnNoErrorResult() {
        results.add(new ValidationResult());
    }

}