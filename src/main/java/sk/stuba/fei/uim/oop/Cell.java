package sk.stuba.fei.uim.oop;

import java.util.Arrays;

public class Cell {
    private int i;
    private int j;
    private boolean[] steny = new boolean[4];
    private boolean navstivene;

    public Cell(){}

    public Cell(int i, int j) {
        this.i = i;
        this.j = j;
        navstivene =false;
        Arrays.fill(steny,true);
    }

    public void setNavstivene(boolean navstivene) {
        this.navstivene = navstivene;
    }

    public boolean isNavstivene() {
        return navstivene;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public boolean[] getSteny() {
        return steny;
    }

    public boolean getWalls(int index){
        return steny[index];
    }

    public void setWalls(int i,boolean walls) {
        this.steny[i] = walls;
    }
}
