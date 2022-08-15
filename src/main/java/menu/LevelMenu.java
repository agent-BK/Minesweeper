package menu;

import enums.Level;
import game.InitGame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static enums.Label.SET_LEVEL;
import static enums.Size.*;

public class LevelMenu extends BaseMenu {

    public static void initLevelMenu(InitGame game, String title, String text) {
        ArrayList<Object> elements = new ArrayList<>();
        JDialog dialog = new JDialog(game, title, true);
        JPanel panel = new JPanel();
        elements.add(getLabel(text));
        ButtonGroup btnGroup = setRadioButton(elements, game.getLevel());
        elements.add(getBtnApply(dialog, game, btnGroup));
        setElementsMenu(panel, elements);
        setAttachmentsToDialog(dialog, panel, game, SIZE_220.getSize());
    }

    private static ButtonGroup setRadioButton(ArrayList<Object> elements, int levelGame) {
        ButtonGroup btnGroup = new ButtonGroup();
        Arrays.stream(Level.values()).forEach(level -> {
            JRadioButton btn = new JRadioButton(
                    level.getLevelName(),
                    Integer.toString(levelGame).equals(level.getLevelsNum()));
            btn.setActionCommand(level.getLevelsNum());
            btn.setPreferredSize(new Dimension(
                    SIZE_180.getSize(),
                    SIZE_25.getSize()));
            btnGroup.add(btn);
            elements.add(btn);
        });
        return btnGroup;
    }

    private static JButton getBtnApply(JDialog dialog, InitGame game, ButtonGroup btnGroup) {
        JButton btn = new JButton(SET_LEVEL.getText());
        btn.addActionListener(e -> {
            dialog.dispose();
            game.setLevel(Integer.parseInt(btnGroup.getSelection().getActionCommand()));
            game.initPanel();
            game.initFrame();
            game.panelRepaint();
        });
        return btn;
    }
}
