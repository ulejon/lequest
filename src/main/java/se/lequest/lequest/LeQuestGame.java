package se.lequest.lequest;

import javax.swing.*;

import se.lequest.lequest.fileio.FileIO;
import se.lequest.lequest.gui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LeQuestGame {
    private static final Logger LOG = LoggerFactory.getLogger(LeQuestGame.class);

    public static void main(String[] args) {
        LOG.trace("Starting game");

        try {
            //Makes the GUI look more "Windows"-like.
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            LOG.warn("Unable to use desired look and feel");
        }

        FileIO.loadHighScore();
        new IntroFrame();
    }

}
