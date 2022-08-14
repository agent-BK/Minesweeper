package enums;

public enum Label {
    VERSION("1.2"),
    YEAR("2022"),
    REPLAY_GAME("Сыграть еще раз"),
    RESIZE_FIELD("Изменить размер поля"),
    SET_LEVEL("Изменить уровень"),
    AUTHOR("Author: Andrey Voitovich, %s version: %s"),
    TITLE("Сапер"),
    LOSE("Вы проиграли!"),
    WIN("Вы победили!");

    private final String text;

    Label(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
