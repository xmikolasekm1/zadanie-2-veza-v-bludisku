package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MyCanvas extends Canvas implements KeyListener,MouseListener {
    Canvas c;
    private int xp;
    private int yp;

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

    public MyCanvas() {
        setXp(50);
        setYp(0);
    }



    public void vymazanieGulocky(){

        getGraphics().setColor(Color.WHITE);
        getGraphics().fillOval(xp,yp,20,20);


        /*setColor(Color.WHITE);
        fillOval(xp, yp, 20, 20);*/
    }

    public void nakreslenieGulocky(){

        getGraphics().setColor(Color.BLACK);
        getGraphics().fillOval(xp,yp,20,20);
        repaint();


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);
        g.fillRect(50,0,650,650);
        getGraphics().setColor(Color.BLACK);
        getGraphics().fillOval(xp,yp,20,20);


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        /*if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if ((xp + 50) <= 640) {
                vymazanieGulocky();
                xp += 50;
                nakreslenieGulocky();
                c.repaint();
                System.out.println("Vpravo");

            }
        }

        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if ((xp - 50) >= 0) {
                vymazanieGulocky();
                xp -= 50;
                nakreslenieGulocky();
                c.repaint();

            }
        }

        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            if ((yp - 50) >= 0) {
                vymazanieGulocky();
                yp -= 50;
                nakreslenieGulocky();
                c.repaint();

            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if ((yp + 50) <= 640) {
                System.out.println("DOLE");
                vymazanieGulocky();
                yp += 50;
                nakreslenieGulocky();
                c.repaint();
            }
        }*/
    }

    @Override
    public void keyReleased(KeyEvent e) {

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
}
