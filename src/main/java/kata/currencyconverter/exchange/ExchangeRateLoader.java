package kata.currencyconverter.exchange;

public interface ExchangeRateLoader {

    ExchangeRates load(String filename);

}
