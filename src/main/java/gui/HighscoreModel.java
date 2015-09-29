package gui;
import highscore.HighscoreItem;
import javax.swing.table.AbstractTableModel;
import java.util.Vector;
import items.*;
/**
 * This Class represents the way the JTable shows the highscore..
 * 
 */
public class HighscoreModel extends AbstractTableModel {
	private Vector<HighscoreItem> data;
	private String columnames[];
	/**
	 * Create the tablemodel for highscore.
	 *
	 */
	public HighscoreModel(){
		data = new Vector<HighscoreItem>();
		String tmp[] = {"Rank","Name","Experience","Gold"};
		columnames = tmp;
	}
	/**
	 * Returns the number of Columns
	 */
	public int getColumnCount() {
		return columnames.length;
	}
	/**
	 * Returns the name of the Column
	 */
	public String getColumnName(int col) {
	        return columnames[col];
	}
	/**
	 * Returns the number of Rows 
	 */
	public int getRowCount() {
		return data.size();
	}
	/**
	 * Returns the string representation of the Cell 
	 * at a row / column if not present it returns a empty string
	 */
	public Object getValueAt(int row, int column) {
		switch(column){
		case 0:
			if(data.get(row) != null){
				return data.get(row).getRank();
			}
			return "";
		case 1:
			if(data.get(row) != null){
				return data.get(row).getPlayerName();
			}
			return "";
		case 2:
			if(data.get(row) != null){
				return data.get(row).getExperience();
			}
			return "";
		case 3:
			if(data.get(row) != null){
				return data.get(row).getGoldAmount();
			}
			return "";
		}		
		return "";
	}
	/**
	 * 	Insert a Highscore item to show in the table
	 * @param item
	 */
	public void insertRow(HighscoreItem item){
		data.add(item);
	}
	/**
	 * Clear all the highscore items from table
	 *
	 */
	public void clearData(){
		data.clear();
	}
	/**
	 * Remove a item from the table
	 * @param item
	 */
	public void removeItem(HighscoreItem item){
		data.remove(item);
	}
	/**
	 * Returns the Highscore item in a row in the table..
	 * @param row
	 * @return
	 */
	public HighscoreItem getRow(int row){
		return data.get(row);
	}
	protected Vector<HighscoreItem> getData(){
		return this.data;
	}
}
