package gui;

import java.io.File;

import javax.swing.filechooser.FileFilter;
/**
 * This is the filter of the save and load files
 * So the filechooser only brings up .player files
 */
public class LeQuestFileFilter extends FileFilter {

	@Override
	/**
	 * Only accept .player files
	 */
	public boolean accept(File arg0) {
		String filename = arg0.getName();
		if(filename.endsWith(".player")){
			return true;
		}
		return false;
	}

	@Override
	/**
	 * Changes the description of .player files to LeQuest Files
	 */
	public String getDescription() {
		return "LeQuest Files";
	}

}
