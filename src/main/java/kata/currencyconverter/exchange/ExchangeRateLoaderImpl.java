package kata.currencyconverter.exchange;

import kata.currencyconverter.model.CountryName;
import kata.currencyconverter.model.CurrencyCode;
import kata.currencyconverter.model.CurrencyName;
import kata.currencyconverter.model.CurrencyRate;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ExchangeRateLoaderImpl implements ExchangeRateLoader {

    public ExchangeRates load(String filename) {
        ExchangeRates rates = new ExchangeRates();

        String line;
        try (BufferedReader br = readResourceFile(filename)) {
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                rates.add(buildExchangeRate(columns));
            }
        } catch (Exception e) {
            throw new ExchangeRateLoaderException(e);
        }

        return rates;
    }

    private ExchangeRate buildExchangeRate(String[] columns) {
        CountryName countryName = new CountryName(columns[0]);
        CurrencyName currencyName = new CurrencyName(columns[1]);
        CurrencyCode currencyCode = new CurrencyCode(columns[2]);
        CurrencyRate currencyRate = new CurrencyRate(columns[3]);
        return new ExchangeRate(countryName, currencyName, currencyCode, currencyRate);
    }

    private BufferedReader readResourceFile(String filename) {
        InputStream fileInputStream = getClass().getClassLoader().getResourceAsStream(filename);
        return new BufferedReader(new InputStreamReader(fileInputStream));
    }
}
