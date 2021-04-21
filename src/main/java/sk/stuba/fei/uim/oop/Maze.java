package sk.stuba.fei.uim.oop;

import java.awt.*;
import java.util.Random;
import java.util.Stack;

public class Maze {
    private int riadok;
    private int stlpec;
    private int sirka = 50;
    private Cell[][] poleVsetkychCells = new Cell[13][13];
    private Cell aktualnaCell = new Cell();
    private int cislo;
    private Cell[] susedneCells = new Cell[4];
    private Cell hore = new Cell();
    private Cell vpravo = new Cell();
    private Cell dole = new Cell();
    private Cell vlavo = new Cell();
    private int pozicia;
    private Stack<Cell> stack=new Stack<>();

    public Maze() {
        riadok = 650 / sirka;
        stlpec = 650 / sirka;

        for (int i = 0; i < riadok; i++) {
            for (int j = 0; j < stlpec; j++) {
                Cell cell = new Cell(i, j);
                poleVsetkychCells[i][j] = cell;
            }
        }
        setAktualnaCell(poleVsetkychCells[0][0]);
        dfs(aktualnaCell);
    }

    public boolean overenieStien(int xp, int yp,int index){
        return poleVsetkychCells[xp][yp].getWalls(index);
    }


    public void nakresli(int i, int j, Graphics g) {
        int xp = i * 50 + 50;
        int yp = j * 50;

        boolean[] walls = poleVsetkychCells[i][j].getSteny();
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
        currentCell.setNavstivene(true);
        Random rand = new Random();

        hore =null;
        vpravo =null;
        dole =null;
        vlavo =null;


        if (currentCell.getI() == 0 && (currentCell.getJ() >= 0 && currentCell.getJ() <= 12)) { //prvy riadok
            if (currentCell.getJ() == 0) {
                vpravo = poleVsetkychCells[currentCell.getI()][currentCell.getJ() + 1];
                dole = poleVsetkychCells[currentCell.getI() + 1][currentCell.getJ()];
            }
            else if (currentCell.getJ() > 0 && currentCell.getJ() < 12) {
                vpravo = poleVsetkychCells[currentCell.getI()][currentCell.getJ() + 1];
                dole = poleVsetkychCells[currentCell.getI() + 1][currentCell.getJ()];
                vlavo = poleVsetkychCells[currentCell.getI()][currentCell.getJ() - 1];
            }
            else if (currentCell.getJ() == 12) {
                dole = poleVsetkychCells[currentCell.getI() + 1][currentCell.getJ()];
                vlavo = poleVsetkychCells[currentCell.getI()][currentCell.getJ() - 1];
            }

        } else if (currentCell.getJ() == 0 && (currentCell.getI() > 0 && currentCell.getI() <= 12)) {   //nalavo stlpec
            if (currentCell.getI() == 12) {
                hore = poleVsetkychCells[currentCell.getI() - 1][currentCell.getJ()];
                vpravo = poleVsetkychCells[currentCell.getI()][currentCell.getJ() + 1];
            }
            else if (currentCell.getI()>0 && currentCell.getI()<12){
                hore = poleVsetkychCells[currentCell.getI() - 1][currentCell.getJ()];
                vpravo = poleVsetkychCells[currentCell.getI()][currentCell.getJ() + 1];
                dole = poleVsetkychCells[currentCell.getI() + 1][currentCell.getJ()];
            }

        } else if (currentCell.getI() == 12 && (currentCell.getJ()>0 && currentCell.getJ() <= 12)) {        //posledny riadok
            if (currentCell.getJ()==12){
                hore = poleVsetkychCells[currentCell.getI() - 1][currentCell.getJ()];
                vlavo = poleVsetkychCells[currentCell.getI()][currentCell.getJ() - 1];
            }
            else if (currentCell.getJ()>0 && currentCell.getJ()<12){
                hore = poleVsetkychCells[currentCell.getI() - 1][currentCell.getJ()];
                vpravo = poleVsetkychCells[currentCell.getI()][currentCell.getJ() + 1];
                vlavo = poleVsetkychCells[currentCell.getI()][currentCell.getJ() - 1];
            }
        }
        else if(currentCell.getJ()==12 &&(currentCell.getI()>0 && currentCell.getI()<12)){          //pravy stlpec
            hore = poleVsetkychCells[currentCell.getI() - 1][currentCell.getJ()];
            dole = poleVsetkychCells[currentCell.getI() + 1][currentCell.getJ()];
            vlavo = poleVsetkychCells[currentCell.getI()][currentCell.getJ() - 1];
        }

        else {
            hore = poleVsetkychCells[currentCell.getI() - 1][currentCell.getJ()];
            vpravo = poleVsetkychCells[currentCell.getI()][currentCell.getJ() + 1];
            dole = poleVsetkychCells[currentCell.getI() + 1][currentCell.getJ()];
            vlavo = poleVsetkychCells[currentCell.getI()][currentCell.getJ() - 1];
        }

        setPozicia(0);

        if (hore != null && !hore.isNavstivene()) {
            susedneCells[getPozicia()] = hore;
            setPozicia(getPozicia() + 1);
        }

        if (vpravo != null && !vpravo.isNavstivene()) {
            susedneCells[getPozicia()] = vpravo;
            setPozicia(getPozicia() + 1);
        }

        if (dole != null && !dole.isNavstivene()) {
            susedneCells[getPozicia()] = dole;
            setPozicia(getPozicia() + 1);
        }

        if (vlavo != null && !vlavo.isNavstivene()) {
            susedneCells[getPozicia()] = vlavo;
            setPozicia(getPozicia() + 1);
        }

        if (getPozicia() > 0) {
            cislo = rand.nextInt(pozicia);
            susedneCells[cislo].setNavstivene(true);
            stack.push(currentCell);

            Cell next= susedneCells[cislo];

            removeWalls(currentCell, susedneCells[cislo]);

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


    public void setAktualnaCell(Cell aktualnaCell) {
        this.aktualnaCell = aktualnaCell;
    }

    public Cell getAktualnaCell() {
        return aktualnaCell;
    }

    public Cell[][] getPoleVsetkychCells() {
        return poleVsetkychCells;
    }

    public Cell getDavamSemCells(int x,int y) {
        return poleVsetkychCells[x][y];
    }

    public int getPozicia() {
        return pozicia;
    }

    public void setPozicia(int pozicia) {
        this.pozicia = pozicia;
    }
}
