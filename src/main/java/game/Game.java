package game;

import enums.Icons;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
                GameObject obj = gamePanel[x][y];
                if (obj.isFlag()) {
                    if (obj.isMine()) {
                        obj.setIcon(Icons.BOMB);
                    } else {
                        obj.setIcon(Icons.NOBOMB);
                    }
                    obj.setOpen(true);
                } else if (obj.isMine() && !obj.isOpen()) {
                    obj.setIcon(Icons.BOMBED);
                    obj.setOpen(true);
                }
            }
        }
    }

    /**
     * левое нажатие мышки
     **/
    public void openTileLeft(int x, int y) {
        GameObject obj = gamePanel[x][y];
        if (!obj.isOpen() && !obj.isFlag() && !stopGame) {
            obj.setOpen(true);
            if (obj.isMine()) {
                obj.setIcon(Icons.BOMBED);
                stopGame = true;
            } else {
                countCell--;
                if (obj.getCountMineNeighbors() > 0) {
                    obj.setIcon(Icons.values()[obj.getCountMineNeighbors()]);
                } else {
                    obj.setIcon(Icons.ZERO);
                    List<GameObject> result = getNeighbors(obj);
                    for (GameObject cell : result) {
                        if (!cell.isOpen()) {
                            openTileLeft(cell.getX(), cell.getY());
                        }
                    }
                }
            }
        }
    }

    /**
     * правое нажатие мышки
     **/
    public void openTileRight(int x, int y) {
        GameObject gameObject = gamePanel[x][y];
        if (!stopGame) {
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
                mine_status = getRandomNumber(level) == 1;
                gamePanel[x][y] = new GameObject(x, y, mine_status);
                if (mine_status) {
                    countMine++;
                }
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
                    List<GameObject> lists = getNeighbors(gameObject);
                    for (GameObject list : lists) {
                        if (list.isMine()) {
                            gameObject.incCountMineNeighbors();
                        }
                    }
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
                if (x < 0 || x >= rows || y < 0 || y >= cols || gamePanel[x][y] == gameObject) {
                    continue;
                }
                result.add(gamePanel[x][y]);
            }
        }
        return result;
    }

    /**
     * случайное число для расположения мин
     **/
    private int getRandomNumber(int num) {
        return new Random().nextInt(num);
    }
}