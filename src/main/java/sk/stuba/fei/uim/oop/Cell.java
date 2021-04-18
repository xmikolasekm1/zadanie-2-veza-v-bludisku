package sk.stuba.fei.uim.oop;

import java.awt.*;
import java.util.Arrays;

public class Cell {
    private int i;
    private int j;
    private boolean[] walls = new boolean[4];
    private boolean visited;

    public Cell(){}

    public Cell(int i, int j) {
        this.i = i;
        this.j = j;
        visited=false;
        for (int m = 0; m < walls.length; m++) {
                walls[m] = true;
        }
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isVisited() {
        return visited;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public boolean[] getWalls() {
        return walls;
    }

    public void setWalls(int i,boolean walls) {
        this.walls[i] = walls;
    }
}
