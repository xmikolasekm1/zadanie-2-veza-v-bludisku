package sk.stuba.fei.uim.oop;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class Aplikacia implements ActionListener, KeyListener, MouseListener {
    private JPanel menu;
    private MyJFrame aplikacia;
    private JLabel pocetVyriesenych = new JLabel();
    private MyJButton resetHry= new MyJButton("Reset");;
    private MyJButton tlacitkoHore= new MyJButton("Hore");
    private MyJButton tlacitkoDole= new MyJButton("Dole");
    private MyJButton tlacitkoVpravo= new MyJButton("Vpravo");
    private MyJButton tlacitkoVlavo= new MyJButton("Vlavo");
    private MyCanvas canvas = new MyCanvas();
    private int vyriesene;


    public Aplikacia() {
        aplikacia = new MyJFrame();
        menu = new JPanel();
        vyriesene = 0;

        pocetVyriesenych.setText("Pocet vyriesenych:" + vyriesene);

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

        canvas.addMouseListener(this);

        aplikacia.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            int xP=(canvas.getXp() / 50) - 1;
            int yP=canvas.getYp() / 50;
            boolean stena = (canvas.kontrolaSteny(xP, yP, 1));
            if (!stena) {
                if ((canvas.getXp() + 50) <= 650) {
                    pohybVpravo();
                }
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            int xP=(canvas.getXp() / 50) - 1;
            int yP=canvas.getYp() / 50;
            boolean stena = (canvas.kontrolaSteny(xP, yP, 3));
            if (!stena) {
                if ((canvas.getXp() - 50) >= 50) {
                    pohybVlavo();

                }
            }
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            int xP=(canvas.getXp() / 50) - 1;
            int yP=canvas.getYp() / 50;
            boolean stena = (canvas.kontrolaSteny(xP, yP, 0));
            if (!stena) {
                if ((canvas.getYp() - 50) >= 0) {
                    pohybHore();
                }
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            int xP=(canvas.getXp() / 50) - 1;
            int yP=canvas.getYp() / 50;
            boolean stena = (canvas.kontrolaSteny(xP, yP, 2));
            if (!stena) {
                if ((canvas.getYp() + 50) <= 600) {
                    pohybDole();
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
                pocetVyriesenych.setText("Pocet vyriesenych:" + vyriesene);
                canvas.reset();
            }


            if (e.getSource() == tlacitkoDole) {
                int xP=(canvas.getXp() / 50) - 1;
                int yP=canvas.getYp() / 50;
                boolean stena = (canvas.kontrolaSteny(xP, yP, 2));
                if (!stena) {
                    if ((canvas.getYp() + 50) <= 640) {
                        pohybDole();
                    }
                }
            }

            if (e.getSource() == tlacitkoHore) {
                int xP=(canvas.getXp() / 50) - 1;
                int yP=canvas.getYp() / 50;
                boolean stena = (canvas.kontrolaSteny(xP, yP, 0));
                if (!stena) {
                    if ((canvas.getYp() - 50) >= 0) {
                        pohybHore();

                    }
                }
            }

            if (e.getSource() == tlacitkoVlavo) {
                int xP=(canvas.getXp() / 50) - 1;
                int yP=canvas.getYp() / 50;
                boolean stena = (canvas.kontrolaSteny(xP, yP, 3));
                if (!stena) {
                    if ((canvas.getXp() - 50) >= 50) {
                        pohybVlavo();

                    }
                }
            }

            if (e.getSource() == tlacitkoVpravo) {
                int xP=(canvas.getXp() / 50) - 1;
                int yP=canvas.getYp() / 50;
                boolean stena = (canvas.kontrolaSteny(xP, yP, 1));
                if (!stena) {
                    if ((canvas.getXp() + 50) <= 650) {
                        pohybVpravo();

                    }
                }
            }
    }

    public void pohybHore(){
        canvas.vymazanieGulocky();
        canvas.setYp(canvas.getYp() - 50);
        canvas.setKliknutie(0);
        canvas.nakreslenieGulocky();
    }

    public void pohybVpravo(){
        canvas.vymazanieGulocky();
        canvas.setXp(canvas.getXp() + 50);
        canvas.setKliknutie(0);
        if (canvas.getXp() == 650 && canvas.getYp() == 600) {
            vyriesene++;
            pocetVyriesenych.setText("Pocet vyriesenych:" + vyriesene);
            pocetVyriesenych.repaint();
        }
        canvas.nakreslenieGulocky();
    }


    public void pohybDole(){
        canvas.vymazanieGulocky();
        canvas.setYp(canvas.getYp() + 50);
        canvas.setKliknutie(0);
        if (canvas.getXp() == 650 && canvas.getYp() == 600) {
            vyriesene++;
            pocetVyriesenych.setText("Pocet vyriesenych:" + vyriesene);
            pocetVyriesenych.repaint();
        }
        canvas.nakreslenieGulocky();
    }




    public void pohybVlavo(){
        canvas.vymazanieGulocky();
        canvas.setXp(canvas.getXp() - 50);
        canvas.setKliknutie(0);
        canvas.nakreslenieGulocky();
    }

    public int getVyriesene() {
        return vyriesene;
    }

    public void setVyriesene(int vyriesene) {
        this.vyriesene = vyriesene;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (canvas.getKliknutie() == 0) {
            if ((e.getX() <= 700 && e.getX() >= 50) && (e.getY() >= 0 && e.getY() <= 650)) {
                canvas.setPohyb(canvas.vykresliMoznyPohybPoKliknuti(((e.getX() / 50) - 1), (e.getY() / 50)));
                if (canvas.getPohyb()==0){
                    canvas.setKliknutie(0);
                }
                else{
                    canvas.setKliknutie(canvas.getKliknutie()+1);
                }
            }
        } else if (canvas.getKliknutie() == 1) {
            canvas.setKliknutie(canvas.presunPoKliknuti(((e.getX() / 50) - 1), (e.getY() / 50)));
            if (canvas.getKliknutie()!=1){
                canvas.setPohybPoKliknuti();
                if (canvas.isCiel()) {
                    canvas.setCiel(false);
                    vyriesene++;
                    pocetVyriesenych.setText("Pocet vyriesenych:" + vyriesene);
                    System.out.println("Pocet vyriesenych:" + vyriesene);
                    pocetVyriesenych.repaint();
                }
            }

        }
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
