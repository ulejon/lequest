package se.lequest.lequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.lequest.lequest.fileio.FileIO;
import se.lequest.lequest.gui.IntroFrame;

import javax.swing.UIManager;


public class LeQuestGame {
    private static final Logger LOG = LoggerFactory.getLogger(LeQuestGame.class);

    public static void main(String[] args) {
        LOG.debug("Starting game");

        try {
            //Makes the GUI look more "Windows"-like.
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            LOG.warn("Unable to use desired look and feel");
        }

        FileIO.loadHighScore();
        new IntroFrame();
    }

}
