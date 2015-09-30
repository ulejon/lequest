package se.lequest.lequest.items;

/**
 * Placeholder Item class for money.
 * @author Magnus 
 * @version v0.01
 */
public class Money extends Item {
	private static final int MONEY_LEVEL = 1; //dummy
	private static final String IMAGE 	 = "pictures/gold.jpg";
	public Money( int value ){
		super("Money", value,IMAGE,MONEY_LEVEL);
	}
	
	/**
	 * Returns the sellValue
	 * @return the sellValue of the item
	 */
	public int getSellValue(){
		return value;
	}
	
	public String getDescription(){
		return "A total of " +value+ " Gold Coins.";
	}
}
