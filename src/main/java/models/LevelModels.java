package models;

import lombok.Data;

@Data
public class LevelModels {
    String name;
    String levelsNum;

    public LevelModels(String name, String num) {
        this.name = name;
        this.levelsNum = num;
    }
}
