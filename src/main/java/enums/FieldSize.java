package enums;

import static enums.Separator.SYMBOL_X;

public enum FieldSize {
    FIELD_5("5x5"),
    FIELD_10("10x10"),
    FIELD_15("15x15"),
    FIELD_20("20x20"),
    FIELD_25("25x25"),
    FIELD_30("30x30"),
    FIELD_35("35x35");

    private final String size;

    FieldSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public int getCountSize() {
        return Integer.parseInt(size.split(SYMBOL_X.getSymbol())[0]);
    }
}
