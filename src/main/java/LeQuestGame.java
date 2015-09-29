import javax.swing.*;

import constants.GameConstants;
import javax.swing.LookAndFeel;
import gui.*;
import characters.Player;
import maps.CatacombMap;
import FileIO.*;
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
        FileIO.fileIO.loadHighScore();
        IntroFrame i = new IntroFrame();
    }

}
