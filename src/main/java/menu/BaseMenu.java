package menu;

import game.InitGame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static enums.FontSize.FONT_20;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public abstract class BaseMenu {

    protected static JLabel getLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(Font.SERIF, Font.BOLD, FONT_20.getSize()));
        return label;
    }

    protected static JComboBox<String> getComboBox(String[] items, int width, int height, int fontSize, Color textColor, int maxRow, String selected) {
        JComboBox<String> sizesList = new JComboBox<>(items);
        sizesList.setMaximumRowCount(maxRow);
        sizesList.setForeground(textColor);
        sizesList.setFont(new Font(Font.SERIF, Font.BOLD, fontSize));
        sizesList.setPreferredSize(new Dimension(width, height));
        sizesList.setSelectedItem(selected);
        return sizesList;
    }

    protected static void setAttachmentsToDialog(JDialog dialog, JPanel panel, InitGame obj, int size) {
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.add(panel);
        dialog.setSize(size, size);
        dialog.setLocationRelativeTo(obj.getPanel());
        dialog.setResizable(false);
        dialog.setVisible(true);
    }

    protected static void setElementsMenu(JPanel panel, ArrayList<Object> elements) {
        for (Object element : elements) {
            panel.add((Component) element);
        }
    }
}
