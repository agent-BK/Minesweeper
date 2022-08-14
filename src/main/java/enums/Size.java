package enums;

public enum Size {
    SIZE_5(5),
    SIZE_25(25),
    SIZE_30(30),
    SIZE_180(180),
    SIZE_220(220),
    SIZE_320(320),
    SIZE_480(480);

    private final int size;

    Size(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
