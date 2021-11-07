package se.lequest.lequest.classloader;

import se.lequest.lequest.constants.GameConstants;
import se.lequest.lequest.items.Item;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This is a class that takes care of reflection (thats is loading ) items from
 * files to the game..
 */
public class ClassLoader extends java.lang.ClassLoader implements java.io.Serializable {
    private final List<File> files;
    private int currentLevel;
    private final Random randGen;

    /**
     * Creates a ClassLoader object..
     */
    public ClassLoader() {
        files = new ArrayList<>();
        randGen = new Random(System.currentTimeMillis());
        this.currentLevel = -1;
    }

    /*
     * Fills the internal array of Files..
     */
    private void fillList(int level) {
        if (level == this.currentLevel) {
            //fileArray already up to date.. just return..
            return;
        }
        if (level == GameConstants.KEY_TO_GET_ALL) {
            //The market frame requests alla the items that can be bought in market..
            String marketsource = "Classes/items/market/";
            fillList(marketsource);
            this.currentLevel = GameConstants.KEY_TO_GET_ALL;
        } else {
            //A enemy or something requests drop..
            String forsource = "Classes/items/level";
            String endsource = "/";
            fillList(forsource + level + endsource);
            this.currentLevel = level;
        }
    }

    /**
     * Returns a Arraylist of the items that share the same level..
     * Use GameConstants.KEY_TO_GET_ALL to get All the items for
     * market
     *
     * @param level
     * @return
     */
    public List<Item> getItemCollection(int level) {
        ArrayList<Item> theitemlist = new ArrayList<Item>();
        fillList(level);
        for (File tmpfile : this.files) {
            theitemlist.add(fileToItem(tmpfile));
        }
        return theitemlist;
    }

    /*
     * Fills the internal array of files with all the .class files in the sourcefolder
     * @param sourcefolder
     */
    private void fillList(String sourcefolder) {
        files.clear();
        String source = sourcefolder;
        File thesource = new File(source);
        File[] files;
        if (thesource.exists() && thesource.isDirectory()) {
            //the Directory exist.  fill her upp.!
            files = thesource.listFiles();
            for (File tmpfile : files) {
                //if class file then add it..
                if (tmpfile.getName().toLowerCase().endsWith(".lequest")) {
                    this.files.add(tmpfile);
                }
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "The path to the source: " + thesource.getAbsolutePath() + "\nThe source is a directory? " + thesource.isDirectory() + "\nThe source exists? " + thesource.exists());
            javax.swing.JOptionPane.showMessageDialog(null, "The games files & folders is Currupt.. Exiting.. ReInstall game!");
            System.exit(0);
        }

    }
    /*
     * prints out all the files in the fileCollection to the console.
     *
     */
	/*public void printfilelist(){
		for(File tmpfile: this.files){
			System.out.println(tmpfile.getName());
		}
	}*/

    /**
     * Returns a random item .. with the right level..
     *
     * @param level
     * @return
     */
    public Item getRandomitem(int level) {
        fillList(level);
        Item randitem = fileToItem(getRandomfilefromlist());
        if (randitem == null) {
            System.exit(0);
        }
        return randitem;
    }

    /*
     * Returns a random File from the fileCollection..
     * @return
     */
    private File getRandomfilefromlist() {
        if (0 <= this.files.size() && this.files.size() < Integer.MAX_VALUE) {
            int randint = randGen.nextInt(this.files.size());
            return files.get(randint);
        } else {
            return null;
        }
    }


    /*
     * Creates a Item of a .class file..
     * @param thafile
     * @return
     */
    private Item fileToItem(File thafile) {
        if (thafile != null) {
            Object aobject = fileToObject(thafile);
            if (aobject instanceof Item) {
                return (Item) aobject;
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "The Item file do not exist!");
        }
        return null;
    }

    /*
     * This function trys to read a file into a byte array..
     * @param file
     * @return
     */
    private byte[] readfile(File file) {
        byte[] bytes = new byte[1];
        try {
            InputStream is = new FileInputStream(file);
            long length = file.length();
            bytes = new byte[(int) length];
            int offset = 0;
            int numread = 0;
            while (offset < bytes.length && (numread = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numread;
            }
            is.close();
        } catch (Exception e) {
        }
        return bytes;

    }

    /*
     * This function trys to load a class from a .class file..
     * it will check if the class is already loaded.. in that case the function
     * will return a new Instace of the object..
     * if the class have not been loaded yet.. this function will loadit and return a
     * instace of it..
     * @param thafile
     * @return
     */
    private Object fileToObject(File thafile) {
        Object theobject = null;
        String classname = "se.lequest.lequest.items." + thafile.getName().replace(".leQuest", "");
        Class thaclassobject = this.findLoadedClass(classname);
        if (thaclassobject == null) {
            byte[] thebytes = readfile(thafile);
            Class theclass = this.defineClass(null, thebytes, 0, (int) thafile.length());
            try {
                theobject = theclass.newInstance();
            } catch (Exception e) {
                ;
            }
        } else {
            try {
                theobject = thaclassobject.newInstance();
            } catch (Exception e) {
            }
        }
        return theobject;
    }

}
