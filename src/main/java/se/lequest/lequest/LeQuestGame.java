package se.lequest.lequest;

import javax.swing.*;

import se.lequest.lequest.fileio.FileIO;
import se.lequest.lequest.gui.*;

public class LeQuestGame {

    /**
     * @param args
     */
    public static void main(String[] args) {

        try{	//Makes the GUI look more "Windows"-like.
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (Exception e ) {}
        FileIO.loadHighScore();
        IntroFrame i = new IntroFrame();
    }

}
