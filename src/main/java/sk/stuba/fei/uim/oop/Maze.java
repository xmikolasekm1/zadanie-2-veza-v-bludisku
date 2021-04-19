package sk.stuba.fei.uim.oop;

import java.awt.*;
import java.util.Random;
import java.util.Stack;

public class Maze {
    private int rows;
    private int cols;
    private int w = 50;
    private Cell[][] davamSemCells = new Cell[13][13];
    private Cell currentCell = new Cell();
    private int cislo;
    private Cell[] neighbours = new Cell[4];
    private Cell top = new Cell();
    private Cell right = new Cell();
    private Cell bottom = new Cell();
    private Cell left = new Cell();
    private int pozicia;
    private Stack<Cell> stack=new Stack<>();

    public Maze() {
        rows = 650 / w;
        cols = 650 / w;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Cell cell = new Cell(i, j);
                davamSemCells[i][j] = cell;
            }
        }
        setCurrentCell(davamSemCells[0][0]);
        dfs(currentCell);
    }

    public boolean overenieStien(int xp, int yp,int index){
        return davamSemCells[xp][yp].getWalls(index);
    }


    public void nakresli(int i, int j, Graphics g) {
        int xp = i * 50 + 50;
        int yp = j * 50;

        boolean[] walls = davamSemCells[i][j].getWalls();
        if (walls[0]) {
            g.setColor(Color.BLACK);
            g.drawLine(xp, yp, xp + 50, yp);
        }
        if (walls[1]) {
            g.setColor(Color.BLACK);
            g.drawLine(xp + 50, yp, xp + 50, yp + 50);
        }
        if (walls[2]) {
            g.setColor(Color.BLACK);
            g.drawLine(xp + 50, yp + 50, xp, yp + 50);
        }
        if (walls[3]) {
            g.setColor(Color.BLACK);
            g.drawLine(xp, yp + 50, xp, yp);
        }


    }


    public int dfs(Cell currentCell) {
        currentCell.setVisited(true);
        Random rand = new Random();

        top=null;
        right=null;
        bottom=null;
        left=null;


        if (currentCell.getI() == 0 && (currentCell.getJ() >= 0 && currentCell.getJ() <= 12)) { //prvy riadok
            if (currentCell.getJ() == 0) {
                right = davamSemCells[currentCell.getI()][currentCell.getJ() + 1];
                bottom = davamSemCells[currentCell.getI() + 1][currentCell.getJ()];
            }
            else if (currentCell.getJ() > 0 && currentCell.getJ() < 12) {
                right = davamSemCells[currentCell.getI()][currentCell.getJ() + 1];
                bottom = davamSemCells[currentCell.getI() + 1][currentCell.getJ()];
                left = davamSemCells[currentCell.getI()][currentCell.getJ() - 1];
            }
            else if (currentCell.getJ() == 12) {
                bottom = davamSemCells[currentCell.getI() + 1][currentCell.getJ()];
                left = davamSemCells[currentCell.getI()][currentCell.getJ() - 1];
            }

        } else if (currentCell.getJ() == 0 && (currentCell.getI() > 0 && currentCell.getI() <= 12)) {   //nalavo stlpec
            if (currentCell.getI() == 12) {
                top = davamSemCells[currentCell.getI() - 1][currentCell.getJ()];
                right = davamSemCells[currentCell.getI()][currentCell.getJ() + 1];
            }
            else if (currentCell.getI()>0 && currentCell.getI()<12){
                top = davamSemCells[currentCell.getI() - 1][currentCell.getJ()];
                right = davamSemCells[currentCell.getI()][currentCell.getJ() + 1];
                bottom = davamSemCells[currentCell.getI() + 1][currentCell.getJ()];
            }
        } else if (currentCell.getI() == 12 && (currentCell.getJ()>0 && currentCell.getJ() <= 12)) {
            if (currentCell.getJ()==12){
                top = davamSemCells[currentCell.getI() - 1][currentCell.getJ()];
                left = davamSemCells[currentCell.getI()][currentCell.getJ() - 1];
            }
            else if (currentCell.getJ()>0 && currentCell.getJ()<12){
                top = davamSemCells[currentCell.getI() - 1][currentCell.getJ()];
                right = davamSemCells[currentCell.getI()][currentCell.getJ() + 1];
                left = davamSemCells[currentCell.getI()][currentCell.getJ() - 1];
            }
        }
        else if(currentCell.getJ()==12 &&(currentCell.getI()>0 && currentCell.getI()<12)){
            top = davamSemCells[currentCell.getI() - 1][currentCell.getJ()];
            bottom = davamSemCells[currentCell.getI() + 1][currentCell.getJ()];
            left = davamSemCells[currentCell.getI()][currentCell.getJ() - 1];
        }

        else {
            top = davamSemCells[currentCell.getI() - 1][currentCell.getJ()];
            right = davamSemCells[currentCell.getI()][currentCell.getJ() + 1];
            bottom = davamSemCells[currentCell.getI() + 1][currentCell.getJ()];
            left = davamSemCells[currentCell.getI()][currentCell.getJ() - 1];
        }

        setPozicia(0);

        if (top != null && !top.isVisited()) {
            neighbours[getPozicia()] = top;
            setPozicia(getPozicia() + 1);
        }

        if (right != null && !right.isVisited()) {
            neighbours[getPozicia()] = right;
            setPozicia(getPozicia() + 1);
        }

        if (bottom != null && !bottom.isVisited()) {
            neighbours[getPozicia()] = bottom;
            setPozicia(getPozicia() + 1);
        }

        if (left != null && !left.isVisited()) {
            neighbours[getPozicia()] = left;
            setPozicia(getPozicia() + 1);
        }

        if (getPozicia() > 0) {
            cislo = rand.nextInt(pozicia);
            neighbours[cislo].setVisited(true);
            stack.push(currentCell);

            Cell next=neighbours[cislo];

            removeWalls(currentCell,neighbours[cislo]);

            dfs(next);
        }
        else if (stack.size()>0){
            Cell next=stack.pop();
            dfs(next);
        }

        return 0;

    }

    public void removeWalls(Cell current,Cell next){
        int rozdielJ=current.getI()- next.getI();
        if (rozdielJ==1){
            current.setWalls(3,false);
            next.setWalls(1,false);
        }
        else if (rozdielJ==-1){
            current.setWalls(1,false);
            next.setWalls(3,false);
        }
        int rozdielI=current.getJ()- next.getJ();
        if (rozdielI==1){
            current.setWalls(0,false);
            next.setWalls(2,false);
        }
        else if(rozdielI==-1){
            current.setWalls(2,false);
            next.setWalls(0,false);
        }
    }


    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public Cell[][] getDavamSemCells() {
        return davamSemCells;
    }

    public int getPozicia() {
        return pozicia;
    }

    public void setPozicia(int pozicia) {
        this.pozicia = pozicia;
    }
}
