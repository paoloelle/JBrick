package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;


public class MainGUI extends JFrame implements KeyListener {

    public StartWindow startWindow;
    public Level1 level1;
    public Level2 level2;


    public MainGUI() {

        super("JBrick");

        startWindow = new StartWindow();

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

    }

    public void mainGUISettings() {

        this.setSize(800, 600);
        this.setResizable(false);

        // set the window at the center of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(screenSize.width / 2 - this.getWidth() / 2, screenSize.height / 2 - this.getHeight() / 2);

        this.add(startWindow);

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }


    @Override
    public void keyPressed(KeyEvent e) {

        // from startWindow to level1
        if(e.getKeyCode() == KeyEvent.VK_SPACE && startWindow.enableStartWindow){

            level1 = new Level1();
            addKeyListener(level1);
            level2 = new Level2();
            this.remove(startWindow);
            this.add(level1);
            startWindow.enableStartWindow = false;
            level1.enableLevel1 = true;
            this.repaint();
            this.revalidate();
        }

        // from level1 to level2
        if (e.getKeyCode() == KeyEvent.VK_SPACE && level1.enableLevel1 && level1.enableWin) {

            //level2 = new Level2();
            addKeyListener(level2);
            this.remove(level1);
            this.add(level2);
            level1.enableLevel1 = false;
            level2.enableLevel2 = true;
            this.repaint();
            this.revalidate();
        }

        // from level1 to startWindow
        if (e.getKeyCode() == KeyEvent.VK_SPACE && level1.enableLevel1 && level1.enableLose) {

            this.remove(level1);
            this.add(startWindow);
            level1.enableLevel1 = false;
            startWindow.enableStartWindow = true;
            this.repaint();
            this.revalidate();

        }

        // from level2 to startWindow
        if(e.getKeyCode() == KeyEvent.VK_SPACE && level2.enableLevel2 && (level2.enableWin || level2.enableLose)){

            this.remove(level2);
            this.add(startWindow);
            level2.enableLevel2 = false;
            startWindow.enableStartWindow = true;
            this.repaint();
            this.revalidate();

        }

    } // end keyPressed

    @Override
    public void keyReleased(KeyEvent e) {
        // DO NOTHING
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // DO NOTHING
    }
}


