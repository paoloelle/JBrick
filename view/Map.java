package view;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public abstract class Map extends JPanel {


    public String imagePath;
    public BufferedImage background;
    public BufferedImage normalPaddle;
    public BufferedImage bigPaddle;
    public BufferedImage life;

    public int paddleXPosition = 325;
    public int paddleYPosition = 500;

    public int ballXPosition = 400;
    public int ballYPosition = 200;
    public int ballXDirection = 0;
    public int ballYDirection = 1;
    public int normalBallDimension = 20;

    public int[][] map;
    public int row;
    public int col;
    public int brickWidth;
    public int brickHeight;

    public boolean setNormalPaddle = true;

    public int totalBricks = 0;
    public int lifeRemaining = 3;

    public PowerUp powerUp;

    public boolean enablePause = false;
    public boolean enableWin = false;
    public boolean ballLose = false;
    public boolean enableLose = false;



    public abstract void drawLevel(Graphics g);

}
