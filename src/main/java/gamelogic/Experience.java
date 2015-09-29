package gamelogic;

import java.util.*;

/**
 * Class Experience represents how the experience is
 * handled in Le Quest. A player gains a new level 
 * when it reaches an experience value. It then generates
 * a new experience value for the next level that increases
 * exponentially with each level.
 * @author Eric
 */
public class Experience extends Observable{
	
	private static Experience instance;
	private double NEXT_EXPERIENCE_EXPONENT = 1.50;
	private Double nextExperienceLevel;
	private Double oldExperience;
	
	/**
	 * Creates an object of Experience.
	 */
	public Experience(){
		oldExperience = new Double(0);
		nextExperienceLevel = new Double(100);
	}
	
	/**
	 * Gets an instance of this object.
	 * @return the instance of Experience.
	 */
	public static Experience getInstance(){
		if(instance==null){
			instance = new Experience();
		}
		return instance;
	}
	
	/**
	 * Gets the next Experience value that
	 * the player needs to level up.
	 * @return value of next experience.
	 */
	public int getNextExperienceLevel(){
		return nextExperienceLevel.intValue();
	}
	
	/**
	 * Gets the last experience level.
	 * @return last experience.
	 */
	public int getLastExperience(){
		return oldExperience.intValue();
	}
	
	/**
	 * The player has leveled up, set a new experience
	 * for next level up.
	 * @param level The players current level.
	 */
	public void setNextExperienceLevel(int level){
		oldExperience = nextExperienceLevel;
		nextExperienceLevel = Math.pow(level, NEXT_EXPERIENCE_EXPONENT)*100;
		setChanged();
		notifyObservers();
	}
	
}
