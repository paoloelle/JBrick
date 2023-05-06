package view;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class PowerUp {

    public BufferedImage heart;
    public int extraLifeX = 2 * 75 + 40;
    public int extraLifeY = 1 * 35 + 40;

    public BufferedImage arrowsPaddle;
    public int bigPaddleX = 5 * 75 + 70;;
    public int bigPaddleY = 0 * 35 + 40;

    public BufferedImage arrowsBall;
    public int bigBallX = 4 * 75 + 70;
    public int bigBallY = 2 * 35 + 40;

    public boolean enableBigBall = true;
    public boolean enableExtraLife = true;
    public boolean enableBigPaddle = true;


    public PowerUp(){


        try {
            heart = ImageIO.read(new File("images/life.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            arrowsPaddle = ImageIO.read(new File("images/arrowsPaddle.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            arrowsBall = ImageIO.read(new File("images/arrowsBall.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }




    }


    public void drawExtraLife(Graphics g){

        g.setColor(new Color(141, 187, 169, 139));
        g.fillOval(extraLifeX, extraLifeY, 30, 30);

        g.setColor(Color.WHITE);
        g.drawOval(extraLifeX, extraLifeY, 30, 30);

        g.drawImage(heart, extraLifeX + 5, extraLifeY + 5, null);

    }

    public void drawBigPaddle(Graphics g){

        g.setColor(new Color(141, 187, 169, 139));
        g.fillOval(bigPaddleX, bigPaddleY, 30, 30);

        g.setColor(Color.WHITE);
        g.drawOval(bigPaddleX, bigPaddleY, 30, 30);

        g.drawImage(arrowsPaddle, bigPaddleX + 5, bigPaddleY + 10, null);

    }

    public void drawBigBall(Graphics g){

        g.setColor(new Color(141, 187, 169, 139));
        g.fillOval(bigBallX, bigBallY, 30, 30);

        g.setColor(Color.WHITE);
        g.drawOval(bigBallX, bigBallY, 30, 30);

        g.drawImage(arrowsBall, bigBallX + 5, bigBallY + 5, null);

    }


}

