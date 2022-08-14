package enums;

import models.LevelModels;

public enum Level {
    DEFAULT(new LevelModels("Простой", "10")),
    MEDIUM(new LevelModels("Средний", "6")),
    HARD(new LevelModels("Профессионал", "3"));

    private final LevelModels obj;

    Level(LevelModels obj) {
        this.obj = obj;
    }

    public String getLevelName() {
        return obj.getName();
    }

    public String getLevelsNum() {
        return obj.getLevelsNum();
    }
}
