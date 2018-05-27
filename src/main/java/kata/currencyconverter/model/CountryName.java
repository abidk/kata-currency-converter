package kata.currencyconverter.model;

public final class CountryName {

    private final String name;

    public CountryName(String name) {
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
