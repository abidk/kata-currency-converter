package kata.currencyconverter;

import kata.currencyconverter.currency.*;
import kata.currencyconverter.exchange.ExchangeRate;
import kata.currencyconverter.exchange.ExchangeRates;
import kata.currencyconverter.model.*;
import kata.currencyconverter.validation.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CurrencyConverterApplicationTest {

    private CurrencyConverterApplication application;
    private InputParameter givenInput;
    private ConvertedResult actualResult;

    @Before
    public void setUp() {
        // just doing integration testing rather than mocks etc.
        ExchangeRates exchangeRates = buildExchangeRates();

        InputParameterValidator inputValidator = new InputParameterValidator();
        inputValidator.addValidator(new InputCurrencyAmountValidator());
        inputValidator.addValidator(new InputCurrencySourceValidator());
        inputValidator.addValidator(new InputCurrencyTargetValidator());

        CurrencyAmountNormaliser amountNormaliser = new CurrencyAmountTwoDecimalNormaliser();

        CurrencyConverter currencyConverter = new CurrencyConverterImpl(amountNormaliser);

        application = new CurrencyConverterApplication(exchangeRates, inputValidator, currencyConverter);
    }

    @Test
    public void givenValidInputParametersShouldReturnExpectedResult() {
        givenValidInput();

        whenCallingConverter();

        thenResultIsCorrect();
    }

    @Test(expected = InputValidationException.class)
    public void givenInvalidInputParametersShouldThrowValidationException() {
        givenInvalidInput();

        whenCallingConverter();
    }

    private void thenResultIsCorrect() {
        assertThat(actualResult.getCurrencyAmount().getValue(), is("2.00"));
        assertThat(actualResult.getCurrencyName().getName(), is("Dollars"));
        assertThat(actualResult.getCountryName().getName(), is("United States"));
    }

    private void whenCallingConverter() {
        actualResult = application.convert(givenInput);
    }

    private void givenValidInput() {
        givenInput = new InputParameter(new CurrencyAmount("1.00"), new CurrencyCode("GBP"), new CurrencyCode("USD"));
    }

    private void givenInvalidInput() {
        givenInput = new InputParameter(new CurrencyAmount("a"), new CurrencyCode("aa"), new CurrencyCode("bb"));
    }

    private ExchangeRates buildExchangeRates() {
        CountryName countryName = new CountryName("United States");
        CurrencyName currencyName = new CurrencyName("Dollars");
        CurrencyCode currencyCode = new CurrencyCode("USD");
        CurrencyRate currencyRate = new CurrencyRate("2");
        ExchangeRate exchangeRate = new ExchangeRate(countryName, currencyName, currencyCode, currencyRate);

        ExchangeRates exchangeRates = new ExchangeRates();
        exchangeRates.add(exchangeRate);
        return exchangeRates;
    }
}