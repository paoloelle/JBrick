package logic;

import view.Map;
import view.PowerUp;

import java.awt.Rectangle;


public interface ILogic {

    void openMainGUI();

    Rectangle createNormalPaddleRectForCollision(int paddleXPosition, int paddleYPosition);

    Rectangle createBigPaddleRectForCollision(int paddleXPosition, int paddleYPosition);

    Rectangle createBallRectForCollision(int ballXPosition, int ballYPosition, int ballDimension);

    void collisionBallPaddle(Map level);

    void collisionBallBorder(Map level);

    void reduceBrickValue(Map level, int row, int col);

    void collisionBallMap(Map level);

    void checkTotalBricksValue(Map level, int row, int col);

    void checkWin(Map level);

    void checkBallLose(Map level);

    void checkLose(Map level);

    void resetParameters(Map level);

    void checkCollisionPaddlePowerUp(Map level, PowerUp powerUp);


}

