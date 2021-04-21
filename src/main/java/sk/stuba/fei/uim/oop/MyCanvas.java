package sk.stuba.fei.uim.oop;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class MyCanvas extends Canvas {
    private int xp;
    private int yp;
    private Maze maze = new Maze();
    private int kliknutie = 0;
    private int pohyb;
    private ArrayList<Cell> pohybPoKliknuti = new ArrayList<>();
    private boolean ciel;


    public MyCanvas() {
        setXp(50);
        setYp(0);
    }


    public boolean kontrolaSteny(int i, int j, int index) {
        return maze.overenieStien(i, j, index);
    }


    public void reset() {
        vymazanieGulocky();
        setXp(50);
        setYp(0);
        kliknutie = 0;
        pohybPoKliknuti.clear();
        nakreslenieGulocky();
        maze = new Maze();
        repaint();
    }

    public void vymazanieGulocky() {

        getGraphics().setColor(Color.WHITE);
        getGraphics().fillRect(xp, yp, 20, 20);

    }

    public void nakreslenieGulocky() {

        getGraphics().setColor(Color.BLACK);
        getGraphics().fillRect(xp, yp, 20, 20);
        if (getXp() == 650 && getYp() == 600) {
            ciel = true;
            reset();

        }
        repaint();


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);
        g.fillRect(50, 0, 650, 650);

        g.setColor(Color.CYAN);
        g.fillRect(50, 0, 50, 50);
        g.setColor(Color.RED);
        g.fillRect(650, 600, 50, 50);
        getGraphics().setColor(Color.BLACK);
        getGraphics().fillOval(xp + 12, yp + 12, 25, 25);

        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                maze.nakresli(i, j, g);
            }
        }

    }

    public int vykresliMoznyPohybPoKliknuti(int x, int y) {
        int xPosition = (x + 1) * 50;
        int yPosition = y * 50;
        int xPos = x;
        int yPos = y;


        while ((!maze.overenieStien(xPos, yPos, 0) && yPos != 0) && (getXp() == xPosition && getYp() == yPosition)) {
            getGraphics().setColor(Color.BLUE);
            getGraphics().drawOval(((xPos + 1) * 50) + 12, ((yPos - 1) * 50) + 12, 25, 25);
            yPos--;
            pohybPoKliknuti.add(maze.getDavamSemCells(xPos, yPos));


        }
        xPos = x;
        yPos = y;
        while (!maze.overenieStien(xPos, yPos, 1) && xPos != 12 && (getXp() == xPosition && getYp() == yPosition)) {
            getGraphics().setColor(Color.BLUE);
            getGraphics().drawOval(((xPos + 2) * 50) + 12, ((yPos) * 50) + 12, 25, 25);
            xPos++;
            pohybPoKliknuti.add(maze.getDavamSemCells(xPos, yPos));


        }
        xPos = x;
        yPos = y;
        while (!maze.overenieStien(xPos, yPos, 2) && yPos != 12 && (getXp() == xPosition && getYp() == yPosition)) {
            getGraphics().setColor(Color.BLUE);
            getGraphics().drawOval(((xPos + 1) * 50) + 12, ((yPos + 1) * 50) + 12, 25, 25);
            yPos++;
            pohybPoKliknuti.add(maze.getDavamSemCells(xPos, yPos));
        }
        xPos = x;
        yPos = y;
        while (!maze.overenieStien(xPos, yPos, 3) && xPos != 0 && (getXp() == xPosition && getYp() == yPosition)) {
            getGraphics().setColor(Color.BLUE);
            getGraphics().drawOval(((xPos) * 50) + 12, ((yPos) * 50) + 12, 25, 25);
            xPos--;
            pohybPoKliknuti.add(maze.getDavamSemCells(xPos, yPos));
        }

        if ((getXp() != xPosition && getYp() != yPosition) || (getXp() == xPosition && getYp() != yPosition) || (getXp() != xPosition && getYp() == yPosition)) {
            return 0;
        }

        return 1;
    }

    public void presun(int x, int y) {
        vymazanieGulocky();
        setXp((x + 1) * 50);
        setYp(y * 50);
        nakreslenieGulocky();
    }


    public int presunPoKliknuti(int x, int y) {
        int xPosition = (x + 1) * 50;
        int yPosition = y * 50;
        if (!maze.overenieStien((getXp() / 50) - 1, getYp() / 50, 0) && ((getYp() > yPosition) && getXp() == xPosition)) {
            for (Cell cell : pohybPoKliknuti) {
                if (cell.getI() == x && cell.getJ() == y) {
                    presun(x, y);
                    return 0;
                }
            }
            return 1;
        } else if (!maze.overenieStien((getXp() / 50) - 1, getYp() / 50, 1) && ((getXp() < xPosition) && getYp() == yPosition)) {
            for (Cell cell : pohybPoKliknuti) {
                if (cell.getI() == x && cell.getJ() == y) {
                    presun(x, y);
                    return 0;
                }


            }
            return 1;
        } else if (!maze.overenieStien((getXp() / 50) - 1, getYp() / 50, 2) && ((getYp() < yPosition) && getXp() == xPosition)) {
            for (Cell cell : pohybPoKliknuti) {
                if (cell.getI() == x && cell.getJ() == y) {
                    presun(x, y);
                    return 0;
                }

            }
            return 1;
        } else if (!maze.overenieStien((getXp() / 50) - 1, getYp() / 50, 3) && ((getXp() > xPosition) && getYp() == yPosition)) {
            for (Cell cell : pohybPoKliknuti) {
                if (cell.getI() == x && cell.getJ() == y) {
                    presun(x, y);
                    return 0;
                }
            }
            return 1;
        } else if ((getXp() > xPosition && getYp() < yPosition) || (getXp() > xPosition && getYp() > yPosition) ||
                (getXp() < xPosition && getYp() < yPosition) || (getXp() < xPosition && getYp() > yPosition) ||
                (getXp() == xPosition && getYp() > yPosition) || (getXp() == xPosition && getYp() < yPosition)) {
            return 1;
        } else {
            return 1;
        }

    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getYp() {
        return yp;
    }

    public void setYp(int yp) {
        this.yp = yp;
    }

    public void setKliknutie(int kliknutie) {
        this.kliknutie = kliknutie;
    }

    public void setPohyb(int pohyb) {
        this.pohyb = pohyb;
    }

    public ArrayList<Cell> getPohybPoKliknuti() {
        return pohybPoKliknuti;
    }

    public void setPohybPoKliknuti() {
        this.pohybPoKliknuti.clear();
    }

    public int getKliknutie() {
        return kliknutie;
    }

    public int getPohyb() {
        return pohyb;
    }

    public boolean isCiel() {
        return ciel;
    }

    public void setCiel(boolean ciel) {
        this.ciel = ciel;
    }
}
