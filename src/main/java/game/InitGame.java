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
    private int cols = 10;
    private int rows = 10;
    private int level = 10;
    private JPanel panel;
    private Game game;

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
        createPanel();
        addMouseListenerToPanel();
        setPanelPreferredSize();
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

    private void createPanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                paintGameCells(g);
            }
        };
    }

    private void addMouseListenerToPanel() {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!game.isGameStatus()) {
                    return;
                }
                int y = e.getX() / SIZE_30.getSize();
                int x = e.getY() / SIZE_30.getSize();
                if (e.getButton() == MouseEvent.BUTTON1) {
                    game.openTileWithLeftClick(x, y);
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    game.openTileWithRightClick(x, y);
                }
                handleGameCompletion();
                panel.repaint();
                if (game.isGameStatus()) {
                    return;
                }
                String message = game.isStopGame() ? LOSE.getText() : WIN.getText();
                showMessage(InitGame.this, message);
            }
        });
    }

    private void setPanelPreferredSize() {
        Dimension dimension = new Dimension(cols * SIZE_30.getSize(), rows * SIZE_30.getSize());
        panel.setPreferredSize(dimension);
    }

    private void paintGameCells(Graphics g) {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                Image icon = game.getCell(x, y).getIcon();
                g.drawImage(icon, y * SIZE_30.getSize(), x * SIZE_30.getSize(), this);
            }
        }
    }

    private void handleGameCompletion() {
        if (game.getCountCell() == game.getCountMine() && game.getCountFlag() == 0 || game.isStopGame()) {
            game.visibleAllMine();
            game.setGameStatus(false);
        }
    }
}
