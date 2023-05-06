package logic;

import view.Map;
import view.PowerUp;

import view.View;

import java.awt.Rectangle;


public class Logic implements ILogic {

    private static Logic instance = null;

    private Sound backgroundSound = new Sound(getClass().getResource("Sound/background.wav"));
    private Sound bounceSound = new Sound(getClass().getResource("Sound/bouncingBall.wav"));
    private Sound brokenBrickSound = new Sound(getClass().getResource("Sound/breakingBrick.wav"));
    private Sound powerUpSound = new Sound(getClass().getResource("Sound/powerUp.wav"));
    private Sound winSound = new Sound(getClass().getResource("Sound/winGame.wav"));
    private Sound loseSound = new Sound(getClass().getResource("Sound/loseGame.wav"));


    public static ILogic getInstance() {

        if (instance == null)
            instance = new Logic();
        return instance;

    }

    @Override
    public void openMainGUI() {

        View.getInstance().openMainGUI();
        backgroundSound.loopClip();

    }

    @Override
    public Rectangle createNormalPaddleRectForCollision(int paddleXPosition, int paddleYPosition) {
        return new Rectangle(paddleXPosition, paddleYPosition, 100, 23);
    }

    @Override
    public Rectangle createBigPaddleRectForCollision(int paddleXPosition, int paddleYPosition) {
        return new Rectangle(paddleXPosition, paddleYPosition, 200, 23);
    }

    @Override
    public Rectangle createBallRectForCollision(int ballXPosition, int ballYPosition, int ballDimension) {
        return new Rectangle(ballXPosition, ballYPosition, ballDimension, ballDimension);
    }

    @Override
    public void collisionBallPaddle(Map level) {

        if (level.setNormalPaddle) { //collision with normalPaddle dimensions

            Rectangle normalPaddleRect = createNormalPaddleRectForCollision(level.paddleXPosition, level.paddleYPosition);
            Rectangle ballRect = createBallRectForCollision(level.ballXPosition, level.ballYPosition, level.normalBallDimension);

            if (ballRect.intersects(normalPaddleRect) && normalPaddleRect.y > ballRect.y) {

                if (ballRect.x <= normalPaddleRect.x + 30) {

                    if (level.ballXDirection > -1)
                        level.ballXDirection--;
                    level.ballYDirection = -level.ballYDirection;

                } else if (ballRect.x > normalPaddleRect.x + 30 && ballRect.x <= normalPaddleRect.x + 70) {
                    level.ballYDirection = -level.ballYDirection;

                } else if (ballRect.x > normalPaddleRect.x + 70) {
                    if (level.ballXDirection < 1)
                        level.ballXDirection++;
                    level.ballYDirection = -level.ballYDirection;
                }

                bounceSound.playClip();


            } else if (ballRect.intersects(normalPaddleRect))
                level.ballXDirection = -level.ballXDirection;

        }

        if (!level.setNormalPaddle) { //collision with bigPaddle dimensions

            Rectangle bigPaddleRect = createBigPaddleRectForCollision(level.paddleXPosition, level.paddleYPosition);
            Rectangle ballRect = createBallRectForCollision(level.ballXPosition, level.ballYPosition, level.normalBallDimension);

            if (ballRect.intersects(bigPaddleRect) && bigPaddleRect.y > ballRect.y) {

                if (ballRect.x <= bigPaddleRect.x + 60) {
                    if (level.ballXDirection > -1)
                        level.ballXDirection--;
                    level.ballYDirection = -level.ballYDirection;

                } else if (ballRect.x > bigPaddleRect.x + 60 && ballRect.x <= bigPaddleRect.x + 140) {
                    level.ballYDirection = -level.ballYDirection;

                } else if (ballRect.x > bigPaddleRect.x + 140) {
                    if (level.ballXDirection < 1)
                        level.ballXDirection++;
                    level.ballYDirection = -level.ballYDirection;
                }

                bounceSound.playClip();


            } else if (ballRect.intersects(bigPaddleRect))
                level.ballXDirection = -level.ballXDirection;


        }

    }

    @Override
    public void collisionBallBorder(Map level) {

        if (level.ballXPosition < 5 || level.ballXPosition > 755) {
            level.ballXDirection = -level.ballXDirection;
            bounceSound.playClip();
        }
        if (level.ballYPosition < 7) {
            level.ballYDirection = -level.ballYDirection;
            bounceSound.playClip();
        }

    }

    @Override
    public void reduceBrickValue(Map level, int row, int col) {

        level.map[row][col] -= 1;
        if (level.map[row][col] < 0)
            level.map[row][col] = 0;

    }

    @Override
    public void checkTotalBricksValue(Map level, int row, int col) {

        if (level.totalBricks < 0)
            level.totalBricks = 0;

        if (level.map[row][col] == 0)
            level.totalBricks--;
    }

    @Override
    public void collisionBallMap(Map level) {

        for (int i = 0; i < level.map.length; i++)
            for (int j = 0; j < level.map[0].length; j++)

                if (level.map[i][j] > 0) {

                    int brickX = j * 75 + 60;
                    int brickY = i * 35 + 40;
                    int brickWidth = level.brickWidth;
                    int brickHeight = level.brickHeight;

                    Rectangle brickRect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                    Rectangle ballRect = createBallRectForCollision(level.ballXPosition, level.ballYPosition, level.normalBallDimension);

                    if (ballRect.intersects(brickRect)) {

                        if (ballRect.getMaxX() <= brickX)
                            level.ballXDirection = -level.ballXDirection;

                        else if (ballRect.getMaxX() >= brickRect.getMaxX())
                            level.ballXDirection = -level.ballXDirection;

                        else
                            level.ballYDirection = -level.ballYDirection;

                        reduceBrickValue(level, i, j);

                        checkTotalBricksValue(level, i, j);

                        brokenBrickSound.playClip();

                    }
                }
    }

    @Override
    public void checkWin(Map level) {

        if (level.totalBricks == 0) {
            level.enableWin = true;
            winSound.playClip();
            resetParameters(level);
        }
    }

    @Override
    public void checkBallLose(Map level) {

        if (level.ballYPosition > 580) {

            level.lifeRemaining--;

            level.ballLose = true;

            resetParameters(level);

        }
    }

    @Override
    public void checkLose(Map level) {

        if (level.lifeRemaining == 0){
            level.enableLose = true;
            loseSound.playClip();
        }

    }

    @Override
    public void resetParameters(Map level) {

        level.ballXPosition = 400;
        level.ballYPosition = 200;

        level.paddleXPosition = 325;
        level.paddleYPosition = 500;

        level.ballXDirection = 0;
        level.ballYDirection = 1;

        level.normalBallDimension = 20;

        level.setNormalPaddle = true;

    }

    @Override
    public void checkCollisionPaddlePowerUp(Map level, PowerUp powerUp) {

        // BIG BALL
        if (level.setNormalPaddle && level.map[2][4] == 0 && powerUp.enableBigBall) {

            Rectangle normalPaddleRect = createNormalPaddleRectForCollision(level.paddleXPosition, level.paddleYPosition);
            Rectangle bigBallRect = new Rectangle(powerUp.bigBallX, powerUp.bigBallY, 30, 30);

            if (normalPaddleRect.intersects(bigBallRect)) {
                level.normalBallDimension += 5;
                powerUp.enableBigBall = false;
                powerUpSound.playClip();
            }

        } else if (!level.setNormalPaddle && level.map[2][4] == 0 && powerUp.enableBigBall) {

            Rectangle bigPaddleRect = createBigPaddleRectForCollision(level.paddleXPosition, level.paddleYPosition);
            Rectangle bigBallRect = new Rectangle(powerUp.bigBallX, powerUp.bigBallY, 30, 30);

            if (bigPaddleRect.intersects(bigBallRect)) {
                level.normalBallDimension += 5;
                powerUp.enableBigBall = false;
                powerUpSound.playClip();
            }

        }

        // EXTRA LIFE
        if (level.setNormalPaddle && level.map[1][2] == 0 && powerUp.enableExtraLife) {

            Rectangle normalPaddleRect = createNormalPaddleRectForCollision(level.paddleXPosition, level.paddleYPosition);
            Rectangle extraLifeRect = new Rectangle(powerUp.extraLifeX, powerUp.extraLifeY, 30, 30);

            if (normalPaddleRect.intersects(extraLifeRect)) {
                if (level.lifeRemaining < 4)
                    level.lifeRemaining++;
                powerUp.enableExtraLife = false;
                powerUpSound.playClip();

            }

        }

        if (!level.setNormalPaddle && level.map[1][2] == 0 && powerUp.enableExtraLife) {

            Rectangle bigPaddleRect = createBigPaddleRectForCollision(level.paddleXPosition, level.paddleYPosition);
            Rectangle extraLifeRect = new Rectangle(powerUp.extraLifeX, powerUp.extraLifeY, 30, 30);

            if (bigPaddleRect.intersects(extraLifeRect)) {
                if (level.lifeRemaining < 4)
                    level.lifeRemaining++;
                powerUp.enableExtraLife = false;
                powerUpSound.playClip();

            }

        }

        // BIG PADDLE
        if (level.setNormalPaddle && level.map[0][5] == 0 && powerUp.enableBigPaddle) {

            Rectangle normalPaddleRect = createNormalPaddleRectForCollision(level.paddleXPosition, level.paddleYPosition);
            Rectangle bigPaddleRect = new Rectangle(powerUp.bigPaddleX, powerUp.bigPaddleY, 30, 30);

            if (normalPaddleRect.intersects(bigPaddleRect)) {
                level.setNormalPaddle = false;
                powerUp.enableBigPaddle = false;
                powerUpSound.playClip();

            }

        }

        if (!level.setNormalPaddle && level.map[0][5] == 0 && powerUp.enableBigPaddle) {

            Rectangle bigPaddleRect1 = createBigPaddleRectForCollision(level.paddleXPosition, level.paddleYPosition);
            Rectangle bigPaddleRect = new Rectangle(powerUp.bigPaddleX, powerUp.bigPaddleY, 30, 30);

            if (bigPaddleRect1.intersects(bigPaddleRect))
                powerUp.enableBigPaddle = false;

        }


    }

}
