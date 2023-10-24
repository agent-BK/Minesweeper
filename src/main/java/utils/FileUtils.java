package utils;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Objects;

public class FileUtils {
    public static Image getImage(String filename) {
        return getImageIcon(filename).getImage();
    }

    public static ImageIcon getImageIcon(String filename) {
        URL icon = FileUtils.class.getClassLoader().getResource(filename);
        return new ImageIcon(Objects.requireNonNull(icon));
    }
}
