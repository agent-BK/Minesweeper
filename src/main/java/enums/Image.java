package enums;

import javax.swing.*;

import static utils.FileUtils.getImageIcon;

public enum Image {
    INTRO("intro.jpg");

    private final String fileName;
    private static final String PATH_RESOURCE_IMAGE = "images/%s";

    Image(String fileName) {
        this.fileName = fileName;
    }

    public ImageIcon getImage() {
        return getImageIcon(String.format(PATH_RESOURCE_IMAGE, fileName));
    }
}
