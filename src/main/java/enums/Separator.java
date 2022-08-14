package enums;

public enum Separator {
    SYMBOL_X("x");

    private final String element;

    Separator(String element) {
        this.element = element;
    }

    public String getSymbol() {
        return element;
    }
}
