package kata.currencyconverter.model;

public final class CurrencyCode {

    private static final String ISO_4217_CODE_REGEX = "^[A-z]{3}$";

    private final String value;

    public CurrencyCode(String value) {
        this.value = value;
    }

    public boolean isValidISO4217Code() {
        return value.matches(ISO_4217_CODE_REGEX);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return value.equals(((CurrencyCode) other).value);
    }

    @Override
    public String toString() {
        return value;
    }
}
