package gui;
/**
 * Subclass to ItemsTableModel
 * Represents the player's table looks in marketframe
 */
public class playerItemsTableModel extends ItemsTableModel {
	
	/**
	 * Returns the String representation of a cell
	 */
	public Object getValueAt(int row, int column) {
		switch(column){
		case 0:
			if(this.getData().get(row) != null){
				return this.getData().get(row).getName();
			}
			return "";
		case 1:
			if(this.getData().get(row) != null){
				return this.getData().get(row).getClass().getSuperclass().getName();
			}
			return "";
		case 2:
			if(this.getData().get(row) != null){
				return this.getData().get(row).getSellValue();
			}
			return "";
		}
		return "";
	}
}
