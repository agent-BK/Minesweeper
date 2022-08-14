package enums;

public enum TextMenu {
    SETTINGS("Настройки"),
    HELP("Помощь"),
    NEW_GAME("Новая игра"),
    FIELD_SIZE("Размер поля"),
    LEVEL("Уровень"),
    EXIT("Выход"),
    INFORMATION("Справка"),
    ABOUT("О игре");

    private final String text;

    TextMenu(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
