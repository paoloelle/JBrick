package view;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;


public class StartWindow extends JPanel implements ActionListener {


    private BufferedImage background;
    private BufferedImage logo;
    public boolean enableStartWindow = true;
    private int scoreStringJ = 0;
    private int scoreStringB = 1000;
    private int scoreStringR = -300;
    private int scoreStringI = 1200;
    private int scoreStringC = -400;
    private int scoreStringK = 1400;
    private Timer timer;

    public StartWindow() {

        timer = new Timer(1, this);
        timer.start();

        try {
            background = ImageIO.read(new File("images/backgroundStartWindow.jpg"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            logo = ImageIO.read(new File("images/logo.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    private void drawBackground(Graphics g) {

        g.drawImage(background, 0, 0, null);
        g.drawImage(logo, 150, 0, null);

    }

    private void drawString(Graphics g) {

        g.setColor(new Color(226, 255, 218, 116));
        g.setFont(new Font("SansSerif", Font.BOLD, 100));

        if (scoreStringK == 449)
            g.setColor(new Color(209, 0, 22, 248));
        g.drawString("J", 210, scoreStringJ);
        g.drawString("B", 260, scoreStringB);
        g.drawString("R", 330, scoreStringR);
        g.drawString("I", 410, scoreStringI);
        g.drawString("C", 440, scoreStringC);
        g.drawString("K", 510, scoreStringK);

        if (scoreStringK == 449) {
            g.setColor(Color.GREEN);
            g.setFont(new Font("SansSerif", Font.BOLD, 20));
            g.drawString("PRESS SPACE BAR TO START THE GAME", 200, 500);
        }
    }

    public void paint(Graphics g) {

        super.paint(g);
        this.drawBackground(g);
        this.drawString(g);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        timer.start();

        if (scoreStringJ < 450)
            scoreStringJ += 3;

        if (scoreStringB > 450)
            scoreStringB -= 3;

        if (scoreStringR < 450)
            scoreStringR += 3;

        if (scoreStringI > 450)
            scoreStringI -= 3;

        if (scoreStringC < 450)
            scoreStringC += 3;

        if (scoreStringK > 450)
            scoreStringK -= 3;

        repaint();

    }

}

