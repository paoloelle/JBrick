package view;


import logic.Logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class View implements IView{

    private static View instance = null;

    private MainGUI mainGUI = null;

    public static IView getInstance(){

        if(instance == null)
            instance = new View();
        return instance;

    }

    @Override
    public void openMainGUI() {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                if(mainGUI == null)
                    mainGUI = new MainGUI();
                mainGUI.mainGUISettings();

            }
        });

    }

    @Override
    public BufferedImage getBackgroundImage(Map level, String imagePath) {


        try {
            level.background = ImageIO.read(new File(imagePath));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return level.background;

    }

    @Override
    public BufferedImage getLifeImage(Map level) {

        try {
            level.life = ImageIO.read(new File("images/life.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return level.life;

    }

    @Override
    public BufferedImage getNormalPaddleImage(Map level) {

        try {
            level.normalPaddle = ImageIO.read(new File("images/normalPaddle.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return level.normalPaddle;

    }

    @Override
    public BufferedImage getBigPaddleImage(Map level) {

        try {
            level.bigPaddle = ImageIO.read(new File("images/bigPaddle.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return level.bigPaddle;

    }


    @Override
    public void repaintAfterCollisionWithMap(Map level) {
        Logic.getInstance().collisionBallMap(level);
    }

    @Override
    public void repaintAfterCollision(Map level, PowerUp powerUp) {

        Logic.getInstance().collisionBallPaddle(level);

        Logic.getInstance().collisionBallBorder(level);

        Logic.getInstance().checkWin(level);

        Logic.getInstance().checkBallLose(level);

        Logic.getInstance().checkLose(level);

        Logic.getInstance().checkCollisionPaddlePowerUp(level, powerUp);

    }

}

