package kata.currencyconverter.currency;

import kata.currencyconverter.exchange.ExchangeRate;
import kata.currencyconverter.exchange.ExchangeRates;
import kata.currencyconverter.model.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CurrencyConverterImplTest {

    private CurrencyConverterImpl converter;
    private ExchangeRates givenExchangeRates;
    private ConvertedResult actualConvertedValue;
    private CurrencyCode givenCurrencyTarget;
    private CurrencyCode givenCurrencySource;
    private CurrencyAmount givenCurrencyAmount;

    @Before
    public void setUp() {
        converter = new CurrencyConverterImpl(amount -> amount);
    }

    @Test
    public void givenConvertingInputToAnExchangeThatExistsShouldReturnCorrectResult() {
        givenInputParameterAmount("1.00");
        givenInputParameterSource("GBP");
        givenInputParameterTarget("USD");
        givenExchangeRates();

        whenConvertingInputParameter();

        thenShouldReturnCorrectAmount("2.00");
        thenShouldReturnCorrectCurrencyName("Dollars");
        thenShouldReturnCorrectCountryName("United States");
    }

    @Test(expected = NullPointerException.class)
    public void givenConvertingInputToAnExchangeThatDoesNotExistShouldThrowError() {
        givenInputParameterAmount("1.00");
        givenInputParameterSource("GBP");
        givenInputParameterTarget("AAA");
        givenExchangeRates();

        whenConvertingInputParameter();
    }

    private void thenShouldReturnCorrectCountryName(String expectedValue) {
        assertThat(actualConvertedValue.getCountryName().getName(), is(expectedValue));
    }

    private void thenShouldReturnCorrectCurrencyName(String expectedValue) {
        assertThat(actualConvertedValue.getCurrencyName().getName(), is(expectedValue));
    }

    private void thenShouldReturnCorrectAmount(String expectedValue) {
        assertThat(actualConvertedValue.getCurrencyAmount().getValue(), is(expectedValue));
    }

    private void whenConvertingInputParameter() {
        InputParameter inputParam = new InputParameter(givenCurrencyAmount, givenCurrencySource, givenCurrencyTarget);
        actualConvertedValue = converter.convert(inputParam, givenExchangeRates);
    }

    private void givenInputParameterTarget(String code) {
        givenCurrencyTarget = new CurrencyCode(code);
    }

    private void givenInputParameterSource(String code) {
        givenCurrencySource = new CurrencyCode(code);
    }

    private void givenInputParameterAmount(String amount) {
        givenCurrencyAmount = new CurrencyAmount(amount);
    }


    private void givenExchangeRates() {
        CountryName countryName = new CountryName("United States");
        CurrencyName currencyName = new CurrencyName("Dollars");
        CurrencyRate currencyRate = new CurrencyRate("2");
        CurrencyCode currencyCode = new CurrencyCode("USD");

        givenExchangeRates = new ExchangeRates();
        givenExchangeRates.add(new ExchangeRate(countryName, currencyName, currencyCode, currencyRate));
    }
}