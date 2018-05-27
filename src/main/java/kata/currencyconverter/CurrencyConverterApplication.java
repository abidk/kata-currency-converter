package kata.currencyconverter;

import kata.currencyconverter.currency.ConvertedResult;
import kata.currencyconverter.currency.CurrencyConverter;
import kata.currencyconverter.exchange.ExchangeRates;
import kata.currencyconverter.model.InputParameter;
import kata.currencyconverter.validation.InputValidationException;
import kata.currencyconverter.validation.InputValidator;
import kata.currencyconverter.validation.ValidationResult;

public class CurrencyConverterApplication {

    private final ExchangeRates exchangeRates;
    private final InputValidator inputValidator;
    private final CurrencyConverter currencyConverter;

    public CurrencyConverterApplication(ExchangeRates exchangeRates, InputValidator inputValidator, CurrencyConverter currencyConverter) {
        this.exchangeRates = exchangeRates;
        this.inputValidator = inputValidator;
        this.currencyConverter = currencyConverter;
    }

    public ConvertedResult convert(InputParameter input) {
        validateInput(input);

        return exchangeInput(input);
    }

    private void validateInput(InputParameter input) {
        ValidationResult result = inputValidator.validate(input);
        if (result.hasError()) {
            throw new InputValidationException(result.getErrorMessage());
        }
    }

    private ConvertedResult exchangeInput(InputParameter input) {
        return currencyConverter.convert(input, exchangeRates);
    }

}
