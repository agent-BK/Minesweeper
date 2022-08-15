package menu;

import game.InitGame;

import javax.swing.*;

import static enums.TextMenu.*;
import static menu.LevelMenu.initLevelMenu;
import static menu.ResizeFieldMenu.initSizeField;

public class MainMenu {

    public static void initMenu(InitGame game) {
        JMenuBar menuBar = new JMenuBar();
        game.setJMenuBar(menuBar);

        JMenu gameMenu = new JMenu(SETTINGS.getText());
        JMenu helpMenu = new JMenu(HELP.getText());
        menuBar.add(gameMenu);
        menuBar.add(helpMenu);

        JMenuItem newGame = new JMenuItem(NEW_GAME.getText());
        JMenuItem sizeField = new JMenuItem(FIELD_SIZE.getText());
        JMenuItem levelGame = new JMenuItem(LEVEL.getText());
        JMenuItem exitGame = new JMenuItem(EXIT.getText());
        gameMenu.add(newGame);
        gameMenu.add(sizeField);
        gameMenu.add(levelGame);
        gameMenu.addSeparator();
        gameMenu.add(exitGame);

        JMenuItem helpGame = new JMenuItem(INFORMATION.getText());
        JMenuItem aboutGame = new JMenuItem(ABOUT.getText());
        helpMenu.add(helpGame);
        helpMenu.addSeparator();
        helpMenu.add(aboutGame);

        setExitGame(exitGame, game);
        setNewGame(newGame, game);

        sizeField.addActionListener(arg0 -> initSizeField(game, FIELD_SIZE.getText(), FIELD_SIZE.getText()));
        levelGame.addActionListener(arg0 -> initLevelMenu(game, LEVEL.getText(), LEVEL.getText()));
    }

    private static void setExitGame(JMenuItem menuItem, InitGame game) {
        menuItem.addActionListener(item -> {
            game.setVisible(false);
            System.exit(0);
        });
    }

    private static void setNewGame(JMenuItem menuItem, InitGame game) {
        menuItem.addActionListener(item -> {
            game.gameStart();
            game.panelRepaint();
        });
    }
}
