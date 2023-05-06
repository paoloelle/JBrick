package view;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;


public class Level1 extends Map implements KeyListener, ActionListener {


    public boolean enableLevel1 = false;

    private Timer timer;


    public Level1() {

        row = 3;
        col = 9;

        map = new int[row][col];

        totalBricks = row * col;

        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[0].length; j++) {

                if (i == 0)
                    map[i][j] = 3;

                if (i == 1)
                    map[i][j] = 2;

                if (i == 2)
                    map[i][j] = 1;

            }

        brickWidth = 75;
        brickHeight = 35;

        imagePath = "images/backgroundLevel1.jpg";
        View.getInstance().getBackgroundImage(this, imagePath);

        View.getInstance().getLifeImage(this);

        View.getInstance().getNormalPaddleImage(this);

        View.getInstance().getBigPaddleImage(this);

        powerUp = new PowerUp();

        timer = new Timer(1, this);
        timer.start();

    } // end constructor

    @Override
    public void drawLevel(Graphics g) {

        // BACKGROUND
        g.drawImage(background, 0, 0, null);


        // BORDERS
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 5, 600);
        g.fillRect(0, 0, 800, 5);
        g.fillRect(781, 0, 5, 600);

        // LIFE REMAINING
        for (int i = 0; i < lifeRemaining; i++)
            g.drawImage(life, i * 20 + 700, 525, null);


        // PADDLE
        if (setNormalPaddle)
            g.drawImage(normalPaddle, paddleXPosition, paddleYPosition, null);
        else
            g.drawImage(bigPaddle, paddleXPosition, paddleYPosition, null);

        // BALL
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(ballXPosition, ballYPosition, normalBallDimension, normalBallDimension);
        g.setColor(Color.GRAY);
        g.drawOval(ballXPosition, ballYPosition, normalBallDimension, normalBallDimension);
        g.setColor(Color.WHITE);
        g.fillOval(ballXPosition + 5, ballYPosition + 5, 5, 5);


        // MAP
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[0].length; j++)

                if (map[i][j] > 0) {

                    if (map[i][j] == 1) {
                        g.setColor(new Color(255, 194, 15, 139));
                        g.fillRect(j * 75 + 60, i * 35 + 40, brickWidth, brickHeight);
                        g.setColor(Color.WHITE);
                        g.drawRect(j * 75 + 60, i * 35 + 40, brickWidth, brickHeight);
                    }

                    if (map[i][j] == 2) {
                        g.setColor(new Color(255, 67, 204, 139));
                        g.fillRect(j * 75 + 60, i * 35 + 40, brickWidth, brickHeight);
                        g.setColor(Color.WHITE);
                        g.drawRect(j * 75 + 60, i * 35 + 40, brickWidth, brickHeight);
                    }

                    if (map[i][j] == 3) {
                        g.setColor(new Color(255, 0, 25, 139));
                        g.fillRect(j * 75 + 60, i * 35 + 40, brickWidth, brickHeight);
                        g.setColor(Color.WHITE);
                        g.drawRect(j * 75 + 60, i * 35 + 40, brickWidth, brickHeight);
                    }

                }

    } // end drawLevel

    public void paint(Graphics g) {

        super.paint(g);
        this.drawLevel(g);


        // draw bigBall powerUp
        if (map[2][4] == 0 && powerUp.enableBigBall) {

            powerUp.drawBigBall(g);
            powerUp.bigBallY++;

        }

        // draw ExtraLife powerUp
        if (map[1][2] == 0 && powerUp.enableExtraLife) {

            powerUp.drawExtraLife(g);
            powerUp.extraLifeY++;

        }

        //draw bigPaddle powerUP
        if (map[0][5] == 0 && powerUp.enableBigPaddle) {

            powerUp.drawBigPaddle(g);
            powerUp.bigPaddleY++;

        }

        // draw pause window
        if (enableLevel1 && enablePause && !enableWin && !ballLose) {

            g.setColor(new Color(24, 24, 24, 91));
            g.fillRect(0, 0, 800, 600);

            g.setColor(Color.WHITE);
            g.setFont(new Font("SansSerif", Font.BOLD, 80));
            g.drawString("PAUSE", 250, 350);
            g.setFont(new Font("SansSerif", Font.BOLD, 30));
            g.drawString("(press P to resume the game)", 180, 400);

        }

        // draw win window
        if (enableWin) {

            g.setColor(new Color(24, 24, 24, 91));
            g.fillRect(0, 0, 800, 600);

            g.setColor(Color.WHITE);
            g.setFont(new Font("SansSerif", Font.BOLD, 80));
            g.drawString("YOU WIN", 220, 200);
            g.setFont(new Font("SansSerif", Font.BOLD, 30));
            g.drawString("(press SPACE BAR to go to the next level)", 100, 250);

        }

        // draw 1 ball lose window
        if (ballLose && !enableLose) {

            g.setColor(new Color(24, 24, 24, 91));
            g.fillRect(0, 0, 800, 600);

            g.setColor(Color.WHITE);
            g.setFont(new Font("SansSerif", Font.BOLD, 50));
            g.drawString("YOU LOSE 1 LIFE", 170, 350);
            g.setFont(new Font("SansSerif", Font.BOLD, 20));
            g.drawString("(press SPACE BAR to restart the game)", 200, 400);

            // if powerUp is unlocked and the ball is lost, powerUp is delete from the screen
            if (map[2][4] == 0 && powerUp.enableBigBall) {
                powerUp.enableBigBall = false;
            }

            if (map[1][2] == 0 && powerUp.enableExtraLife) {
                powerUp.enableExtraLife = false;
            }

            if (map[0][5] == 0 && powerUp.enableBigPaddle) {
                powerUp.enableBigPaddle = false;
            }

        }

        // draw lose window
        if (enableLose) {

            g.setColor(new Color(24, 24, 24, 91));
            g.fillRect(0, 0, 800, 600);

            g.setColor(Color.WHITE);
            g.setFont(new Font("SansSerif", Font.BOLD, 80));
            g.drawString("YOU LOSE", 200, 200);
            g.setFont(new Font("SansSerif", Font.BOLD, 30));
            g.drawString("(press SPACE BAR to restart the game)", 100, 250);

            // if powerUp is unlocked and lose the ball, powerUp is delete from the screen
            if (map[2][4] == 0 && powerUp.enableBigBall) {
                powerUp.enableBigBall = false;
            }

            if (map[1][2] == 0 && powerUp.enableExtraLife) {
                powerUp.enableExtraLife = false;
            }

            if (map[0][5] == 0 && powerUp.enableBigPaddle) {
                powerUp.enableBigPaddle = false;
            }

        }


    } // end paint

    @Override
    public void keyPressed(KeyEvent e) {

        if (enableLevel1 && !enablePause && !enableWin && !ballLose && !enableLose) {

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) { //move paddle right
                if (paddleXPosition < 660 && setNormalPaddle){
                    paddleXPosition += 15;
                    //normalPaddleRect.translate(15, 0);
                }

                if (paddleXPosition < 580 && !setNormalPaddle)
                    paddleXPosition += 15;
            }

            if (e.getKeyCode() == KeyEvent.VK_LEFT) //move paddle left
                if (paddleXPosition > 10){
                    paddleXPosition -= 15;
                    //normalPaddleRect.translate(-15, 0);
                }

        }

        if (e.getKeyCode() == KeyEvent.VK_P && !ballLose && enableLevel1 && !enableLose)
            if (enablePause)
                enablePause = false;

            else
                enablePause = true;

        if (e.getKeyCode() == KeyEvent.VK_SPACE && ballLose)
            ballLose = false;

        repaint();

    } // end keyPressed

    @Override
    public void keyTyped(KeyEvent e) {
        // DO NOTHING
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // DO NOTHING
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        View.getInstance().repaintAfterCollisionWithMap(this);

        if (enableLevel1 && !enablePause && !enableWin && !ballLose && !enableLose) {

            timer.start();

            ballXPosition += ballXDirection;
            ballYPosition += ballYDirection;


            View.getInstance().repaintAfterCollision(this, powerUp);


            repaint();

        }
    } // end actionPerformed

}
