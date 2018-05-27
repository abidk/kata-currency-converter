package kata.currencyconverter.validation;

import kata.currencyconverter.model.InputParameter;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class InputParameterValidatorTest {

    private InputParameterValidator validator;
    private ValidationResult actualResult;

    @Before
    public void setUp() {
        validator = new InputParameterValidator();
    }

    @Test
    public void givenValidationHasNoErrorsThenResultHasNoError() {
        givenValidationThatHasNoErrors();

        whenCallingValidator();

        thenResultHasNoError();
    }

    @Test
    public void givenValidationThatHasErrorThenResultHasError() {
        givenValidationThatHasErrors("error");

        whenCallingValidator();

        thenResultHasError();
        thenResultHasErrorMessage("error\n");
    }

    @Test
    public void givenValidationThatACoupleErrorsThenResultHasError() {
        givenValidationThatHasErrors("error1");
        givenValidationThatHasErrors("error2");

        whenCallingValidator();

        thenResultHasError();
        thenResultHasErrorMessage("error1\nerror2\n");
    }

    @Test
    public void givenValidationThatAPassingValidationsAndAFailingOneThenResultHasError() {
        givenValidationThatHasNoErrors();
        givenValidationThatHasNoErrors();
        givenValidationThatHasErrors("error2");

        whenCallingValidator();

        thenResultHasError();
        thenResultHasErrorMessage("error2\n");
    }


    private void thenResultHasError() {
        assertThat(actualResult.hasError(), is(true));
    }

    private void thenResultHasErrorMessage(String expectedError) {
        assertThat(actualResult.getErrorMessage(), is(expectedError));
    }


    private void thenResultHasNoError() {
        assertThat(actualResult.hasError(), is(false));
    }

    private void whenCallingValidator() {
        actualResult = validator.validate(new InputParameter(null, null, null));
    }

    private void givenValidationThatHasErrors(String error) {
        validator.addValidator(input -> {
            ValidationResult result = new ValidationResult();
            result.setErrorMessage(error);
            return result;
        });
    }

    private void givenValidationThatHasNoErrors() {
        validator.addValidator(input -> new ValidationResult());
    }
}