package kata.currencyconverter.model;

public final class CurrencyName {

    private final String name;

    public CurrencyName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
