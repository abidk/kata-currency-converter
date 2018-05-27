package kata.currencyconverter.model;

public final class InputParameter {

    private final CurrencyAmount amount;
    private final CurrencyCode source;
    private final CurrencyCode target;

    public InputParameter(CurrencyAmount amount, CurrencyCode source, CurrencyCode target) {
        this.amount = amount;
        this.source = source;
        this.target = target;
    }

    public CurrencyAmount getAmount() {
        return amount;
    }

    public CurrencyCode getSource() {
        return source;
    }

    public CurrencyCode getTarget() {
        return target;
    }
}
