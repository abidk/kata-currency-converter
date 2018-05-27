package kata.currencyconverter;

import kata.currencyconverter.currency.*;
import kata.currencyconverter.exchange.ExchangeRateLoader;
import kata.currencyconverter.exchange.ExchangeRateLoaderImpl;
import kata.currencyconverter.exchange.ExchangeRates;
import kata.currencyconverter.model.CurrencyAmount;
import kata.currencyconverter.model.CurrencyCode;
import kata.currencyconverter.model.InputParameter;
import kata.currencyconverter.validation.*;

import java.util.Scanner;

public class Start {

    private static final String EXCHANGE_RATES_FILENAME = "exchange-rates.csv";

    public static void main(String[] args) {
        CurrencyConverterApplication application = initApplication();

        Scanner consoleReader = new Scanner(System.in);
        boolean keepRunning = true;
        do {
            System.out.println("Enter a decimal number to convert (e.g. 1.22):");
            String amount = consoleReader.nextLine();
            System.out.println("Enter the source currency (e.g. GBP):");
            String source = consoleReader.nextLine();
            System.out.println("Enter the target currency (e.g. AUD):");
            String target = consoleReader.nextLine();

            try {
                InputParameter input = new InputParameter(new CurrencyAmount(amount), new CurrencyCode(source), new CurrencyCode(target));
                ConvertedResult result = application.convert(input);

                System.out.printf("Result:%n%s", result);
                keepRunning = false;
            } catch (InputValidationException e) {
                System.err.println(e.getMessage());
            }
        } while (keepRunning);

    }

    private static CurrencyConverterApplication initApplication() {
        ExchangeRateLoader rateLoader = new ExchangeRateLoaderImpl();
        ExchangeRates exchangeRates = rateLoader.load(EXCHANGE_RATES_FILENAME);

        InputParameterValidator inputValidator = new InputParameterValidator();
        inputValidator.addValidator(new InputCurrencyAmountValidator());
        inputValidator.addValidator(new InputCurrencySourceValidator());
        inputValidator.addValidator(new InputCurrencyTargetValidator());

        CurrencyAmountNormaliser amountNormaliser = new CurrencyAmountTwoDecimalNormaliser();

        CurrencyConverter currencyConverter = new CurrencyConverterImpl(amountNormaliser);
        return new CurrencyConverterApplication(exchangeRates, inputValidator, currencyConverter);
    }
}
