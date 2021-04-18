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
    private JPanel okno;
    private JFrame aplikacia;
    private JLabel pocetVyriesenych;
    private JButton resetHry;
    private JButton tlacitkoHore;
    private JButton tlacitkoDole;
    private JButton tlacitkoVpravo;
    private JButton tlacitkoVlavo;
    private BufferedImage bludisko;
    private Graphics g;
    private JLabel plochaNaKreslenie;
    private GridBagConstraints gbc;
    private int xp;
    private int yp;
    private MyCanvas canvas = new MyCanvas();


    public Aplikacia() {
        aplikacia = new JFrame();
        okno = new JPanel();
        menu = new JPanel();




        resetHry = new JButton("Reset");
        pocetVyriesenych = new JLabel("Pocet vyriesenych:");
        tlacitkoHore = new JButton("Hore");
        tlacitkoDole = new JButton("Dole");
        tlacitkoVpravo = new JButton("Vpravo");
        tlacitkoVlavo = new JButton("Vlavo");
        xp = 0;
        yp = 0;


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
            if ((canvas.getXp() + 50) <= 650) {
                canvas.vymazanieGulocky();
                canvas.setXp(canvas.getXp()+50);
                canvas.nakreslenieGulocky();

                //System.out.println("Vpravo");

            }
        }

        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if ((canvas.getXp() - 50) >= 50) {
                canvas.vymazanieGulocky();
                canvas.setXp(canvas.getXp()-50);
                canvas.nakreslenieGulocky();

            }
        }

        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            if ((canvas.getYp() - 50) >= 0) {
                canvas.vymazanieGulocky();
                canvas.setYp(canvas.getYp()-50);
                canvas.nakreslenieGulocky();

            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if ((canvas.getYp() + 50) <= 640) {
                canvas.vymazanieGulocky();
                canvas.setYp(canvas.getYp()+50);
                canvas.nakreslenieGulocky();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetHry) {
            canvas.vymazanieGulocky();
            canvas.setXp(50);
            canvas.setYp(0);
            canvas.nakreslenieGulocky();
            canvas.reset();



        }


        if (e.getSource() == tlacitkoDole) {
            if ((canvas.getYp() + 50) <= 640) {
                canvas.vymazanieGulocky();
                canvas.setYp(canvas.getYp()+50);
                canvas.nakreslenieGulocky();

            }
        }

        if (e.getSource() == tlacitkoHore) {
            if ((canvas.getYp() - 50) >= 0) {
                canvas.vymazanieGulocky();
                canvas.setYp(canvas.getYp()-50);
                canvas.nakreslenieGulocky();


            }
        }

        if (e.getSource() == tlacitkoVlavo) {
            if ((canvas.getXp() - 50) >= 50) {
                canvas.vymazanieGulocky();
                canvas.setXp(canvas.getXp()-50);
                canvas.nakreslenieGulocky();



            }
        }

        if (e.getSource() == tlacitkoVpravo) {
            if ((canvas.getXp() + 50) <= 650) {
                canvas.vymazanieGulocky();
                //xp += 50;
                canvas.setXp(canvas.getXp()+50);
                canvas.nakreslenieGulocky();



            }
        }
    }
}
