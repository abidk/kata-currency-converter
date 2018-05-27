package kata.currencyconverter.exchange;

import kata.currencyconverter.model.CountryName;
import kata.currencyconverter.model.CurrencyCode;
import kata.currencyconverter.model.CurrencyName;
import kata.currencyconverter.model.CurrencyRate;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class ExchangeRatesTest {

    private ExchangeRates rates;
    private CurrencyCode givenCurrencyCode;
    private ExchangeRate actualReturnedRate;

    @Before
    public void setUp() {
        rates = new ExchangeRates();
        rates.add(buildExchangeRate());
    }

    @Test
    public void givenAnExchangeRateThatExistsThenExchangeRateIsReturned() {
        givenExchangeRateWithCurrencyCode("USD");

        whenCallingGetRate();

        thenRateIsReturned();
    }

    @Test
    public void givenAnExchangeRateThatDoesNotExistsThenNullIsReturned() {
        givenExchangeRateWithCurrencyCode("GBP");

        whenCallingGetRate();

        thenRateIsNotRetuned();
    }

    private void thenRateIsNotRetuned() {
        assertNull(actualReturnedRate);
    }

    private void thenRateIsReturned() {
        assertThat(actualReturnedRate.getCurrencyCode(), is(givenCurrencyCode));
    }

    private void whenCallingGetRate() {
        actualReturnedRate = rates.getRate(givenCurrencyCode);
    }


    private void givenExchangeRateWithCurrencyCode(String code) {
        givenCurrencyCode = new CurrencyCode(code);
    }

    private ExchangeRate buildExchangeRate() {
        CountryName countryName = new CountryName("United States");
        CurrencyName currencyName = new CurrencyName("Dollars");
        CurrencyRate currencyRate = new CurrencyRate("2");
        CurrencyCode currencyCode = new CurrencyCode("USD");

        return new ExchangeRate(countryName, currencyName, currencyCode, currencyRate);
    }
}