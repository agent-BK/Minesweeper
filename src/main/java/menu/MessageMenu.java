package menu;

import game.InitGame;

import javax.swing.*;
import java.util.ArrayList;

import static enums.Label.REPLAY_GAME;
import static enums.Size.SIZE_180;

public class MessageMenu extends BaseMenu {
    public static void showMessage(InitGame game, String text) {
        ArrayList<Object> elements = new ArrayList<>();
        JDialog dialog = new JDialog(game);
        JPanel panel = new JPanel();
        elements.add(getLabel(text));
        elements.add(getBtnNewGame(dialog, game));
        setElementsMenu(panel, elements);
        setAttachmentsToDialog(dialog, panel, game, SIZE_180.getSize());
    }

    private static JButton getBtnNewGame(JDialog dialog, InitGame game) {
        JButton btn = new JButton(REPLAY_GAME.getText());
        btn.addActionListener(e -> {
            game.gameStart();
            game.panelRepaint();
            dialog.setVisible(false);
        });
        return btn;
    }
}
