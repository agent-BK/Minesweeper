package game;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static enums.Icons.ICON_LOGO;
import static enums.Label.*;
import static enums.Size.SIZE_30;
import static menu.MainMenu.initMenu;
import static menu.MessageMenu.showMessage;

@EqualsAndHashCode(callSuper = true)
@Data
public class InitGame extends JFrame {

    private JPanel panel;
    private Game game;
    private int cols = 10;
    private int rows = 10;
    private int level = 10;

    public InitGame() {
        initMenu(this);
        initPanel();
        initFrame();
    }

    public void gameStart() {
        game = new Game(rows, cols, level);
    }

    public void panelRepaint() {
        panel.repaint();
    }

    public void setSizeField(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
    }

    public void initPanel() {
        gameStart();
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int x = 0; x < rows; x++) {
                    for (int y = 0; y < cols; y++) {
                        g.drawImage(
                                game.getCell(x, y).getIcon(),
                                y * SIZE_30.getSize(),
                                x * SIZE_30.getSize(),
                                this);
                    }
                }
            }
        };
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (game.isGameStatus()) {
                    int y = e.getX() / SIZE_30.getSize();
                    int x = e.getY() / SIZE_30.getSize();
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        game.openTileWithLeftClick(x, y);
                    } else if (e.getButton() == MouseEvent.BUTTON3) {
                        game.openTileWithRightClick(x, y);
                    }
                    if ((game.getCountCell() == game.getCountMine() && game.getCountFlag() == 0) || game.isStopGame()) {
                        game.visibleAllMine();
                        game.setGameStatus(false);
                    }
                    panel.repaint();
                    if (!game.isGameStatus()) {
                        if (game.isStopGame()) {
                            showMessage(InitGame.this, LOSE.getText());
                        } else {
                            showMessage(InitGame.this, WIN.getText());
                        }
                    }
                }
            }
        });
        panel.setPreferredSize(
                new Dimension(cols * SIZE_30.getSize(), rows * SIZE_30.getSize())
        );
        add(panel);
    }

    public void initFrame() {
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(TITLE.getText());
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setIconImage(ICON_LOGO.getIcon());
    }
}