package FileIO;
import gui.LeQuestFileFilter;
import highscore.Highscore;
import java.io.*;

import javax.swing.JFileChooser;

import characters.Player;
/**
 * This Class takes care of all the IO to files.. 
 *
 */
public class fileIO {

	/**
	 * Starts up the Filechooserdialog to load a player to the game.. 
	 * returns the player object..
	 * 
	 * @return
	 */
	public static Player loadPlayer(){
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new LeQuestFileFilter());
		int ret = fc.showOpenDialog(null);		
		if(ret == JFileChooser.APPROVE_OPTION){
			if(fc.getSelectedFile().exists() == false){
				javax.swing.JOptionPane.showMessageDialog(null,"Load error,File can not be found!");
			}
			String filetoOpen = fc.getSelectedFile().getAbsolutePath();		
			try{
				FileInputStream f_in = new FileInputStream (filetoOpen);
				ObjectInputStream obj_in = new ObjectInputStream (f_in);
				Object obj = obj_in.readObject ();
				if (obj instanceof Player)
				{
					return (Player)obj;
				}else{
					javax.swing.JOptionPane.showMessageDialog(null,"Load error,This is not a LeQuest file!");
				}
			}catch(Exception e){
				javax.swing.JOptionPane.showMessageDialog(null,"Load error, Reson: " + e.toString());
			}
		}
		return null;
	}
	/**
	 * Saves a player to the disk.. fires up a Filechooser.. 
	 * @param player
	 */
	public static void savePlayer(Player player){
			JFileChooser fc = new JFileChooser();
			fc.setFileFilter(new LeQuestFileFilter());
			int ret = fc.showSaveDialog(null);
			if(ret == JFileChooser.APPROVE_OPTION){
				String filetoSave = fc.getSelectedFile().getAbsolutePath();
				if(!filetoSave.endsWith(".player")){
					filetoSave += ".player";
				}
				try{
				java.io.FileOutputStream f_out = new FileOutputStream (filetoSave);
				ObjectOutputStream obj_out = new ObjectOutputStream (f_out);
				obj_out.writeObject (player);
				}catch(Exception e){
					javax.swing.JOptionPane.showMessageDialog(null,"Save unsuccessful reson: " + e.toString());
				}
			}
	}
	/**
	 * Saves the highscore object to disk..
	 *
	 */
	public static void saveHighScore(){
		String filetoSave = "HighScore.save";
		Highscore thescore = Highscore.getInstance();
		try{
			java.io.FileOutputStream f_out = new FileOutputStream (filetoSave);
			ObjectOutputStream obj_out = new ObjectOutputStream (f_out);
			obj_out.writeObject (thescore);
			}catch(Exception e){
				
			}
	}
	/**
	 * Loads the highscore from disk.. if none is present it will just create a new highscore object.. 
	 *
	 */
	public static void loadHighScore(){
		Highscore thescore = Highscore.getInstance();			
		String filetoOpen = "HighScore.save";
		File thefile = new File(filetoOpen);
		if(thefile.exists()){
			try{
				FileInputStream f_in = new FileInputStream (filetoOpen);
				ObjectInputStream obj_in = new ObjectInputStream (f_in);
				Object obj = obj_in.readObject ();
				if(obj instanceof Highscore){
					thescore.setScore((Highscore)obj);
				}

			}catch(Exception e){

			}
		}
	}	
}
