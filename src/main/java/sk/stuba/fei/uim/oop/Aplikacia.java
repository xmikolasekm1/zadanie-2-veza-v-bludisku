package sk.stuba.fei.uim.oop;


import javax.swing.*;
import java.awt.*;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;


public class Aplikacia implements ActionListener, KeyListener {
    private JPanel menu;
    private JFrame aplikacia;
    private JLabel pocetVyriesenych = new JLabel();
    private JButton resetHry;
    private JButton tlacitkoHore;
    private JButton tlacitkoDole;
    private JButton tlacitkoVpravo;
    private JButton tlacitkoVlavo;
    private MyCanvas canvas = new MyCanvas();
    private int vyriesene;
    private int i;
    private int j;


    /*public Aplikacia(int reset){
        i=0;
        j=0;
    }*/

    public Aplikacia() {
        aplikacia = new JFrame();
        menu = new JPanel();
        vyriesene = 0;
        i = 0;
        j = 0;


        resetHry = new JButton("Reset");
        pocetVyriesenych.setText("Pocet vyriesenych:" + vyriesene);
        tlacitkoHore = new JButton("Hore");
        tlacitkoDole = new JButton("Dole");
        tlacitkoVpravo = new JButton("Vpravo");
        tlacitkoVlavo = new JButton("Vlavo");


        aplikacia.setTitle("Veza v bludisku");
        aplikacia.setSize(800, 800);
        aplikacia.setResizable(false);
        aplikacia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aplikacia.setLayout(new BorderLayout());
        aplikacia.add(menu, BorderLayout.SOUTH);


        menu.setLayout(new GridLayout(2, 3));
        menu.add(resetHry);
        menu.add(tlacitkoHore);
        menu.add(pocetVyriesenych);
        menu.add(tlacitkoVlavo);
        menu.add(tlacitkoDole);
        menu.add(tlacitkoVpravo);


        aplikacia.add(canvas);
        canvas.setFocusable(true);
        canvas.requestFocus();
        canvas.addKeyListener(this);


        tlacitkoDole.addActionListener(this);
        tlacitkoVpravo.addActionListener(this);
        tlacitkoVlavo.addActionListener(this);
        tlacitkoHore.addActionListener(this);
        resetHry.addActionListener(this);


        tlacitkoHore.setFocusable(false);
        tlacitkoDole.setFocusable(false);
        tlacitkoVpravo.setFocusable(false);
        tlacitkoVlavo.setFocusable(false);
        resetHry.setFocusable(false);

        aplikacia.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            boolean stena = (canvas.kontrolaSteny(((canvas.getXp() / 50) - 1), canvas.getYp() / 50, 1));
            if (!stena) {
                if ((canvas.getXp() + 50) <= 650) {
                    canvas.vymazanieGulocky();
                    canvas.setXp(canvas.getXp() + 50);
                    if (canvas.getXp() == 650 && canvas.getYp() == 600) {
                        vyriesene++;
                        pocetVyriesenych.setText("Pocet vyriesenych:" + vyriesene);
                        pocetVyriesenych.repaint();
                    }
                    canvas.nakreslenieGulocky();

                }
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            boolean stena = (canvas.kontrolaSteny(((canvas.getXp() / 50) - 1), canvas.getYp() / 50, 3));
            if (!stena) {
                if ((canvas.getXp() - 50) >= 50) {
                    canvas.vymazanieGulocky();
                    canvas.setXp(canvas.getXp() - 50);
                    canvas.nakreslenieGulocky();

                }
            }
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            boolean stena = (canvas.kontrolaSteny(((canvas.getXp() / 50) - 1), canvas.getYp() / 50, 0));
            if (!stena) {
                if ((canvas.getYp() - 50) >= 0) {
                    canvas.vymazanieGulocky();
                    canvas.setYp(canvas.getYp() - 50);
                    canvas.nakreslenieGulocky();
                }
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            boolean stena = (canvas.kontrolaSteny(((canvas.getXp() / 50) - 1), canvas.getYp() / 50, 2));
            if (!stena) {
                if ((canvas.getYp() + 50) <= 600) {
                    canvas.vymazanieGulocky();
                    canvas.setYp(canvas.getYp() + 50);
                    if (canvas.getXp() == 650 && canvas.getYp() == 600) {
                        vyriesene++;
                        pocetVyriesenych.setText("Pocet vyriesenych:" + vyriesene);
                        pocetVyriesenych.repaint();
                    }
                    canvas.nakreslenieGulocky();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetHry) {
            vyriesene = 0;
            i = 0;
            j = 0;
            pocetVyriesenych.setText("Pocet vyriesenych:" + vyriesene);
            canvas.reset();
        }


        if (e.getSource() == tlacitkoDole) {
            boolean stena = (canvas.kontrolaSteny(((canvas.getXp() / 50) - 1), canvas.getYp() / 50, 2));
            if (!stena) {
                if ((canvas.getYp() + 50) <= 640) {
                    canvas.vymazanieGulocky();
                    canvas.setYp(canvas.getYp() + 50);
                    if (canvas.getXp() == 650 && canvas.getYp() == 600) {
                        vyriesene++;
                        pocetVyriesenych.setText("Pocet vyriesenych:" + vyriesene);
                        pocetVyriesenych.repaint();
                    }
                    canvas.nakreslenieGulocky();

                }
            }
        }

        if (e.getSource() == tlacitkoHore) {
            boolean stena = (canvas.kontrolaSteny(((canvas.getXp() / 50) - 1), canvas.getYp() / 50, 0));
            if (!stena) {
                if ((canvas.getYp() - 50) >= 0) {
                    canvas.vymazanieGulocky();
                    canvas.setYp(canvas.getYp() - 50);
                    canvas.nakreslenieGulocky();

                }
            }
        }

        if (e.getSource() == tlacitkoVlavo) {
            boolean stena = (canvas.kontrolaSteny(((canvas.getXp() / 50) - 1), canvas.getYp() / 50, 3));
            if (!stena) {
                if ((canvas.getXp() - 50) >= 50) {
                    canvas.vymazanieGulocky();
                    canvas.setXp(canvas.getXp() - 50);
                    canvas.nakreslenieGulocky();

                }
            }
        }

        if (e.getSource() == tlacitkoVpravo) {
            boolean stena = (canvas.kontrolaSteny(((canvas.getXp() / 50) - 1), canvas.getYp() / 50, 1));
            if (!stena) {
                if ((canvas.getXp() + 50) <= 650) {
                    canvas.vymazanieGulocky();
                    canvas.setXp(canvas.getXp() + 50);
                    if (canvas.getXp() == 650 && canvas.getYp() == 600) {
                        vyriesene++;
                        pocetVyriesenych.setText("Pocet vyriesenych:" + vyriesene);
                        pocetVyriesenych.repaint();
                    }
                    canvas.nakreslenieGulocky();

                }
            }
        }
    }

    public int getVyriesene() {
        return vyriesene;
    }

    public void setVyriesene(int vyriesene) {
        this.vyriesene = vyriesene;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
}
