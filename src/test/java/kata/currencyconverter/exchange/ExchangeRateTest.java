package kata.currencyconverter.exchange;

import kata.currencyconverter.model.*;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ExchangeRateTest {

    private ExchangeRate givenExchangeRate;
    private CurrencyAmount actualExchangeAmount;

    @Test
    public void whenExchangingCurrencyShouldReturnCorrectAmount() {
        givenExchangeRate();

        whenExchangingCurrency(new CurrencyAmount("1.00"));

        thenExchangedAmountIs("2.00");
    }

    @Test
    public void whenExchangingCurrencyShouldReturnAllDecimals() {
        givenExchangeRate();

        whenExchangingCurrency(new CurrencyAmount("1.2345"));

        thenExchangedAmountIs("2.4690");
    }

    private void thenExchangedAmountIs(String expectedAmout) {
        assertThat(actualExchangeAmount.getValue(), is(expectedAmout));
    }

    private void whenExchangingCurrency(CurrencyAmount currencyAmount) {
        actualExchangeAmount = givenExchangeRate.exchange(currencyAmount);
    }

    private void givenExchangeRate() {
        CountryName countryName = new CountryName("United States");
        CurrencyName currencyName = new CurrencyName("Dollars");
        CurrencyCode currencyCode = new CurrencyCode("USD");
        CurrencyRate currencyRate = new CurrencyRate("2");
        givenExchangeRate = new ExchangeRate(countryName, currencyName, currencyCode, currencyRate);
    }
}