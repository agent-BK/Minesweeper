package game;

import enums.Icons;
import lombok.Data;

import java.awt.*;

@Data
public class GameObject {
    private int x;
    private int y;
    private boolean isMine;
    private boolean isOpen = false;
    private boolean isFlag = false;
    private Icons icon = Icons.CLOSED;
    private int countMineNeighbors = 0;

    public GameObject(int x, int y, boolean isMine) {
        this.x = x;
        this.y = y;
        this.isMine = isMine;
    }

    public Image getIcon() {
        return icon.getIcon();
    }

    public void plusCountMineNeighbors(long num) {
        countMineNeighbors += num;
    }

    public void setIconAndOpen(Icons icon, boolean isOpen) {
        this.icon = icon;
        this.isOpen = isOpen;
    }
}
