package view;

import java.awt.image.BufferedImage;

public interface IView {

    void openMainGUI();

    BufferedImage getBackgroundImage(Map level, String imagePath);

    BufferedImage getLifeImage(Map level);

    BufferedImage getNormalPaddleImage(Map level);

    BufferedImage getBigPaddleImage(Map level);

    void repaintAfterCollisionWithMap(Map level);

    void repaintAfterCollision(Map level, PowerUp powerUp);

}
