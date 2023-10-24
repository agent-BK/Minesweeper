package game;

import enums.Icons;
import lombok.Data;
import utils.RandomUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class Game {
    private int cols;
    private int rows;
    private int countCell;
    private int level;
    private int countMine = 0;
    private int countFlag = 0;
    private boolean stopGame = false;
    private boolean gameStatus = true;

    private GameObject[][] gamePanel;

    public Game(int rows, int cols, int level) {
        this.rows = rows;
        this.cols = cols;
        this.level = level;
        this.gamePanel = new GameObject[rows][cols];
        this.countCell = rows * cols;
        initField();
    }

    /**
     * Открываем все мины
     **/
    public void visibleAllMine() {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                visibleMine(x, y);
            }
        }
    }

    /**
     * левое нажатие мышки
     **/
    public void openTileWithLeftClick(int x, int y) {
        GameObject gameObject = gamePanel[x][y];
        if (isTileUnopenable(gameObject)) {
            return;
        }
        gameObject.setOpen(true);
        if (gameObject.isMine()) {
            handleMineTile(gameObject);
        } else {
            handleNonMineTile(gameObject);
        }
    }

    /**
     * правое нажатие мышки
     **/
    public void openTileWithRightClick(int x, int y) {
        if (stopGame) {
            return;
        }
        GameObject gameObject = gamePanel[x][y];
        if (!gameObject.isOpen() && !gameObject.isFlag() && countFlag > 0) {
            gameObject.setFlag(true);
            gameObject.setIcon(Icons.FLAGED);
            countFlag--;
        } else if (!gameObject.isOpen() && gameObject.isFlag()) {
            gameObject.setFlag(false);
            gameObject.setIcon(Icons.CLOSED);
            countFlag++;
        }
    }

    public GameObject getCell(int x, int y) {
        return gamePanel[x][y];
    }

    /**
     * образование поля
     **/
    private void initField() {
        countMine = 0;
        boolean mine_status;
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                mine_status = RandomUtils.getRandomNumber(level) == 1;
                gamePanel[x][y] = new GameObject(x, y, mine_status);
                if (mine_status) countMine++;
            }
        }
        countFlag = countMine;
        initCountMine();
    }

    /**
     * подсчет количества мин соседей
     **/
    private void initCountMine() {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                GameObject gameObject = gamePanel[x][y];
                if (!gameObject.isMine()) {
                    long countNeighbors = getNeighbors(gameObject).stream().filter(GameObject::isMine).count();
                    gameObject.plusCountMineNeighbors(countNeighbors);
                }
            }
        }
    }

    /**
     * список соседних ячеек
     **/
    private List<GameObject> getNeighbors(GameObject gameObject) {
        List<GameObject> result = new ArrayList<>();
        for (int x = gameObject.getX() - 1; x <= gameObject.getX() + 1; x++) {
            for (int y = gameObject.getY() - 1; y <= gameObject.getY() + 1; y++) {
                if (x < 0 || x >= rows || y < 0 || y >= cols || gamePanel[x][y] == gameObject) continue;
                result.add(gamePanel[x][y]);
            }
        }
        return result;
    }

    private boolean isTileUnopenable(GameObject gameObject) {
        return gameObject.isOpen() || gameObject.isFlag() || stopGame;
    }

    private void handleMineTile(GameObject gameObject) {
        gameObject.setIcon(Icons.BOMBED);
        stopGame = true;
    }

    private void handleNonMineTile(GameObject gameObject) {
        countCell--;
        if (gameObject.getCountMineNeighbors() > 0) {
            gameObject.setIcon(Icons.values()[gameObject.getCountMineNeighbors()]);
        } else {
            gameObject.setIcon(Icons.ZERO);
            openNeighbors(gameObject);
        }
    }

    private void openNeighbors(GameObject gameObject) {
        getNeighbors(gameObject).stream()
                .filter(cell -> !cell.isOpen())
                .forEach(cell -> openTileWithLeftClick(cell.getX(), cell.getY()));
    }

    private void visibleMine(int x, int y) {
        GameObject gameObject = gamePanel[x][y];
        if (gameObject.isFlag()) {
            gameObject.setIconAndOpen(gameObject.isMine() ? Icons.BOMB : Icons.NOBOMB, true);
        } else if (gameObject.isMine() && !gameObject.isOpen()) {
            gameObject.setIconAndOpen(Icons.BOMBED, true);
        }
    }
}
