package sk.stuba.fei.uim.oop;

import java.awt.*;
import java.awt.event.*;


public class MyCanvas extends Canvas implements MouseListener {
    private int xp;
    private int yp;
    private int x;
    private int y;
    private Cell[][] array;
    private Maze maze=new Maze();


    public MyCanvas() {
        setXp(50);
        setYp(0);
        setArray(maze.getDavamSemCells());
    }


    public void reset(){
        maze=new Maze();
        repaint();
    }

    public void vymazanieGulocky(){

        getGraphics().setColor(Color.WHITE);
        getGraphics().fillRect(xp,yp,20,20);

    }

    public void nakreslenieGulocky(){

        getGraphics().setColor(Color.BLACK);
        getGraphics().fillRect(xp,yp,20,20);
        repaint();


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);
        g.fillRect(50,0,650,650);

        g.setColor(Color.CYAN);
        g.fillRect(50,0,50,50);
        g.setColor(Color.RED);
        g.fillRect(650,600,50,50);
        getGraphics().setColor(Color.BLACK);
        getGraphics().fillOval(xp+12,yp+12,25,25);

        for (int i=0;i<13;i++){
            for (int j=0;j<13;j++){
                maze.nakresli(i,j,g);
            }
        }



    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void setArray(Cell[][] array) {
        this.array = array;
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
}
