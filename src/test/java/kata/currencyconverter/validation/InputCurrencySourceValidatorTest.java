package kata.currencyconverter.validation;

import kata.currencyconverter.model.CurrencyCode;
import kata.currencyconverter.model.InputParameter;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class InputCurrencySourceValidatorTest {

    private InputCurrencySourceValidator validator;
    private InputParameter givenInputParameter;
    private ValidationResult actualResult;

    @Before
    public void setUp() {
        validator = new InputCurrencySourceValidator();
    }


    @Test
    public void givenAValidIsoCodeShouldPassValidation() {
        givenAnInputParamterWithCode("GBP");

        whenValidatingCode();

        thenHasNoError();
    }

    @Test
    public void givenAnOneLetterIsoCodeShouldFailValidation() {
        givenAnInputParamterWithCode("A");

        whenValidatingCode();

        thenHasError();
        thenHasErrorMessage("Source value 'A' is not a valid ISO 4217 code.");
    }

    @Test
    public void given4LetterCodeInvalidIsoCodeShouldFailValidation() {
        givenAnInputParamterWithCode("AAAA");

        whenValidatingCode();

        thenHasError();
        thenHasErrorMessage("Source value 'AAAA' is not a valid ISO 4217 code.");
    }

    @Test
    public void given3NumberCodeInvalidIsoCodeShouldFailValidation() {
        givenAnInputParamterWithCode("123");

        whenValidatingCode();

        thenHasError();
        thenHasErrorMessage("Source value '123' is not a valid ISO 4217 code.");
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

    private void whenValidatingCode() {
        actualResult = validator.validate(givenInputParameter);
    }

    private void givenAnInputParamterWithCode(String code) {
        givenInputParameter = new InputParameter(null, new CurrencyCode(code), null);
    }
}