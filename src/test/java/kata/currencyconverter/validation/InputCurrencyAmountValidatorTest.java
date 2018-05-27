package kata.currencyconverter.validation;

import kata.currencyconverter.model.CurrencyAmount;
import kata.currencyconverter.model.InputParameter;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class InputCurrencyAmountValidatorTest {

    private InputCurrencyAmountValidator validator;
    private InputParameter givenInputParameter;
    private ValidationResult actualResult;

    @Before
    public void setUp() {
        validator = new InputCurrencyAmountValidator();
    }

    @Test
    public void givenAValidInputNumberThenShouldPassValidation() {
        givenAnInputParamterWithAmount("1.00");

        whenValidatingAmount();

        thenHasNoError();
    }

    @Test
    public void givenAnInvalidInputThenShouldPassValidation() {
        givenAnInputParamterWithAmount("a");

        whenValidatingAmount();

        thenHasError();
        thenHasErrorMessage("Value 'a' is not a valid 2 decimal number.");
    }

    @Test
    public void givenAnInvalidInputNumberThenShouldPassValidation() {
        givenAnInputParamterWithAmount("1");

        whenValidatingAmount();

        thenHasError();
        thenHasErrorMessage("Value '1' is not a valid 2 decimal number.");
    }

    @Test
    public void givenAnOnceDecimalInputNumberThenShouldPassValidation() {
        givenAnInputParamterWithAmount("1.0");

        whenValidatingAmount();

        thenHasError();
        thenHasErrorMessage("Value '1.0' is not a valid 2 decimal number.");
    }

    private void thenHasError() {
        assertThat(actualResult.hasError(), is(true));
    }

    private void thenHasErrorMessage(String expectedValue) {
        assertThat(actualResult.getErrorMessage(), is(expectedValue));
    }

    private void thenHasNoError() {
        assertThat(actualResult.hasError(), is(false));
    }

    private void whenValidatingAmount() {
        actualResult = validator.validate(givenInputParameter);
    }

    private void givenAnInputParamterWithAmount(String amount) {
        givenInputParameter = new InputParameter(new CurrencyAmount(amount), null, null);
    }
}