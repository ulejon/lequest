package se.lequest.lequest.highscore;

import se.lequest.lequest.characters.Player;

import java.util.Collection;
import java.util.HashMap;

/**
 * Constans the collection of highscores
 */
public class Highscore implements java.io.Serializable {
    private HashMap<String, HighscoreItem> highscorelist;
    private static Highscore instance;

    private Highscore() {
        highscorelist = new HashMap<String, HighscoreItem>();
    }

    /**
     * Returns the instance of the highscore
     *
     * @return
     */
    public static Highscore getInstance() {
        if (Highscore.instance == null) {
            Highscore.instance = new Highscore();
        }
        return Highscore.instance;
    }

    /**
     * Inserts a new, or updates the old, hoghscore
     *
     * @param player to update/insert
     */
    public void insertPlayer(Player player) {
        HighscoreItem thaitem;
        if (highscorelist.containsKey(player.getName())) {
            HighscoreItem newitem = new HighscoreItem(player);
            highscorelist.get(player.getName()).sync(newitem);
        } else {
            thaitem = new HighscoreItem(player);
            highscorelist.put(thaitem.getPlayerName(), thaitem);
        }
        calculaterank();
    }

    /**
     * Returns the complete list of highscores
     *
     * @return the list of highscores
     */
    public Collection<HighscoreItem> getList() {
        return highscorelist.values();
    }

    /**
     * When you load a highscore file from the disc
     * this function will update the current highscore to the loaded one
     *
     * @param loadscore
     */
    public void setScore(Highscore loadscore) {
        this.highscorelist = loadscore.highscorelist;
    }

    private void calculaterank() {
        HashMap<String, HighscoreItem> newmap = new HashMap<String, HighscoreItem>();
        HighscoreItem tmp;
        int gotonr = this.highscorelist.size();
        for (int i = 1; i < gotonr + 1; i++) {
            tmp = gethighestscore(this.highscorelist);
            tmp.setRank(i);
            newmap.put(tmp.getPlayerName(), tmp);
            this.highscorelist.remove(tmp.getPlayerName());
        }
        this.highscorelist = newmap;
    }

    private HighscoreItem gethighestscore(HashMap<String, HighscoreItem> dalist) {
        HighscoreItem highest = null;
        for (HighscoreItem item : dalist.values()) {
            highest = item;
            break;
        }
        if (dalist.size() == 1) {
            return highest;
        }
        for (HighscoreItem item : dalist.values()) {
            if (!highest.isHighestScored(item)) {
                highest = item;
            }
        }
        return highest;
    }
}
