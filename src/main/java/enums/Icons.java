package enums;

import java.awt.Image;

import static utils.FileUtils.getImage;

public enum Icons {
    ZERO("zero"),
    NUM1("num1"),
    NUM2("num2"),
    NUM3("num3"),
    NUM4("num4"),
    NUM5("num5"),
    NUM6("num6"),
    NUM7("num7"),
    NUM8("num8"),
    BOMB("bomb"),
    OPENED("opened"),
    CLOSED("closed"),
    FLAGED("flaged"),
    BOMBED("bombed"),
    NOBOMB("nobomb"),
    ICON_LOGO("icon");

    private final String name;
    private static final String PATH_RESOURCE_IMAGE = "icons/%s.png";

    Icons(String name) {
        this.name = name;
    }

    public Image getIcon() {
        return getImage(String.format(PATH_RESOURCE_IMAGE, name));
    }
}
