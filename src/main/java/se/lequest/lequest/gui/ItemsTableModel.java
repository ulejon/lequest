package se.lequest.lequest.gui;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;
import se.lequest.lequest.items.*;

/**
 * Class that handles the way items are showed in the market table
 *
 */
public class ItemsTableModel extends AbstractTableModel {
	private Vector<Item> data;
	private String columnames[];
	
	/**
	 * Creates a new model
	 *
	 */
	public ItemsTableModel(){
		data = new Vector<Item>();
		String tmp[] = {"Name","Type","Price"};
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
				return data.get(row).getName();
			}
			return "";
		case 1:
			if(data.get(row) != null){
				return data.get(row).getClass().getSuperclass().getName();
			}
			return "";
		case 2:
			if(data.get(row) != null){
				return new Integer(data.get(row).getPurchaseValue());
			}
			return "";
		}
		return "";
	}
	/**
	 * 	Insert a item to show in the table
	 * @param item
	 */
	public void insertRow(Item item){
		data.add(item);
	}
	/**
	 * Clear all the items from table
	 *
	 */
	public void clearData(){
		data.clear();
	}
	/**
	 * Remove a item from the table
	 * @param item
	 */
	public void removeItem(Item item){
		data.remove(item);
	}
	/**
	 * Returns the item in a row in the table..
	 * @param row
	 * @return
	 */
	public Item getRow(int row){
		return data.get(row);
	}
	protected Vector<Item> getData(){
		return this.data;
	}
}
