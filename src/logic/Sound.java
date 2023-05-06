package logic;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {

    Clip clip;

    public Sound(URL stringa) {
        try {
            // Crea un file da un URL passato come stringa
            //URL fileSuono = (getClass().getResource(stringa));
            // Crea uno stream d'input audio dal file del suono.
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(stringa);
            // Ottieni il clip.
            clip = AudioSystem.getClip();
            // Apri l'audio del clip.
            clip.open(audioInputStream);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        }
    }

    public void playClip() {
        if (clip.isRunning())
            clip.stop();   // Ferma il suono se è ancora in esecuzione.
        clip.setFramePosition(0); // Riavvolgi il suono.
        clip.start();     // Esegui il suono.
    }

    public void loopClip() {
        if (clip.isRunning())
            clip.stop();
        clip.setFramePosition(0);
        clip.loop(100);
    }

    public void stopClip() {
        if (clip.isRunning())
            clip.stop();
    }

}

