package menu;

import enums.FieldSize;
import game.InitGame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static enums.FontSize.FONT_16;
import static enums.Label.RESIZE_FIELD;
import static enums.Separator.SYMBOL_X;
import static enums.Size.*;

public class ResizeFieldMenu extends BaseMenu {
    public static void initSizeField(InitGame game, String title, String text) {
        ArrayList<Object> elements = new ArrayList<>();
        JDialog dialog = new JDialog(game, title, true);
        JPanel panel = new JPanel();
        elements.add(getLabel(text));
        JComboBox<String> sizesList = getComboBox(
                getListValuesFieldSizes(),
                SIZE_180.getSize(),
                SIZE_25.getSize(),
                FONT_16.getSize(),
                Color.DARK_GRAY,
                SIZE_5.getSize(),
                getSelectedSize(game)
        );
        elements.add(sizesList);
        elements.add(getBtnApply(dialog, game, sizesList));
        setElementsMenu(panel, elements);
        setAttachmentsToDialog(dialog, panel, game, SIZE_220.getSize());
    }

    private static JButton getBtnApply(JDialog dialog, InitGame game, JComboBox<String> sizesList) {
        JButton btn = new JButton(RESIZE_FIELD.getText());
        btn.addActionListener(e -> {
            dialog.dispose();
            int size = Integer.parseInt(
                    Objects.requireNonNull(sizesList.getSelectedItem())
                            .toString()
                            .split(SYMBOL_X.getSymbol())[0]
            );
            game.setSizeField(size, size);
            game.initPanel();
            game.initFrame();
            game.panelRepaint();
        });
        return btn;
    }

    private static String[] getListValuesFieldSizes() {
        return Arrays.stream(FieldSize.values())
                .map(FieldSize::getSize)
                .toArray(String[]::new);
    }

    private static String getSelectedSize(InitGame game) {
        int size = game.getRows();
        return Arrays.stream(FieldSize.values())
                .filter(value -> size == value.getCountSize())
                .map(Enum::toString)
                .findFirst()
                .orElse(null);
    }
}
