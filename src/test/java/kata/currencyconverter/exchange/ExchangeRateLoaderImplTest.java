package kata.currencyconverter.exchange;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ExchangeRateLoaderImplTest {

    private ExchangeRateLoaderImpl loader;
    private String givenFilename;
    private ExchangeRates actualResults;

    @Before
    public void setUp() {
        loader = new ExchangeRateLoaderImpl();
    }

    @Test
    public void givenAValidExchangeRateFileThenFileIsLoadedCorrectly() {
        givenAValidExchangeFile("test-exchange-rates.csv");

        whenCallingLoader();

        thenExchangeRateIsLoaded();
    }

    @Test(expected = ExchangeRateLoaderException.class)
    public void givenAnInvalidExchangeRateFileThenExceptionIsThrown() {
        givenAValidExchangeFile("invalid-file.csv");

        whenCallingLoader();
    }

    private void thenExchangeRateIsLoaded() {
        assertThat(actualResults.getRates().size(), is(2));
    }

    private void whenCallingLoader() {
        actualResults = loader.load(givenFilename);
    }

    private void givenAValidExchangeFile(String filename) {
        givenFilename = filename;
    }
}