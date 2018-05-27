package kata.currencyconverter.currency;

import kata.currencyconverter.model.CurrencyAmount;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CurrencyAmountTwoDecimalNormaliserTest {

    private CurrencyAmount givenCurrencyAmount;
    private CurrencyAmount actualNormaliseAmount;

    @Test
    public void givenATwoDecimalValueShouldReturnResultWithoutRounding() {
        givenCurrencyValue("1.99");

        whenNormalisingValue();

        thenResultValueIs("1.99");
    }

    @Test
    public void givenATwoDecimalValueAtHalEvenShouldReturnResultWithoutRounding() {
        givenCurrencyValue("1.55");

        whenNormalisingValue();

        thenResultValueIs("1.55");
    }


    @Test
    public void givenAThreeDecimalValueThenResultIsRoundedUpToTwoDecimalPlaces() {
        givenCurrencyValue("1.019");

        whenNormalisingValue();

        thenResultValueIs("1.02");
    }

    @Test
    public void givenAThreeDecimalValueThatIsHalfEvenThenResultIsRoundedCorrectly() {
        givenCurrencyValue("1.015");

        whenNormalisingValue();

        thenResultValueIs("1.02");
    }

    @Test
    public void givenAThreeDecimalValueThatIsBelowHalfEvenThenResultIsNotRoundedUp() {
        givenCurrencyValue("1.014");

        whenNormalisingValue();

        thenResultValueIs("1.01");
    }

    @Test
    public void givenAThreeDecimalValueThenResultIsRoundedToAWholeNumber() {
        givenCurrencyValue("1.999");

        whenNormalisingValue();

        thenResultValueIs("2.00");
    }


    @Test
    public void givenAFourDecimalValueThenResultIsRoundedToAWholeNumber() {
        givenCurrencyValue("1.9999");

        whenNormalisingValue();

        thenResultValueIs("2.00");
    }

    private void thenResultValueIs(String expectedValue) {
        assertThat(actualNormaliseAmount.getValue(), is(expectedValue));
    }

    private void whenNormalisingValue() {
        CurrencyAmountTwoDecimalNormaliser normaliser = new CurrencyAmountTwoDecimalNormaliser();
        actualNormaliseAmount = normaliser.normalise(givenCurrencyAmount);
    }

    private void givenCurrencyValue(String value) {
        givenCurrencyAmount = new CurrencyAmount(value);
    }
}