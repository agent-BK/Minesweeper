package enums;

public enum FontSize {
    FONT_12(12),
    FONT_16(16),
    FONT_20(20);

    private final int size;

    FontSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
