package gui;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.*;
import javax.swing.JTextField;
import characters.*;
import items.*;
import java.util.ArrayList;
import constants.GameConstants;
import java.util.*;
import java.awt.ScrollPane;
/**
 * Class view the market frame.
 * The market frame shows that items that can be bought and sold
 *
 */
public class MarketFrame extends JFrame implements Observer{
	private static MarketFrame instance;
	private Player player;
	private TownFrame townframe;
	private JPanel picturePanel,lowerPanel,marketPanel,playerPanel,playerButtonsPanel, info;
	private JLabel pictureLabel;
	private String PICTURE = "/pictures/market.jpg";
	private JTable marketInventoryTable,playerInventoryTable;
	private ItemsCollection collection;
	private ItemsTableModel itemModelMarket,itemModelPlayer;
	private JScrollPane scrollerMarket,scrollerPlayer;
	private JButton sell,buy,gotown,inspect;
	private JTextField sellInfo, moneyInfo;
	private TableSorter marketsorter,playersorter;
	private static final int SELL = 0;
	private static final int BUY= 1;
	private static final int NONE_SELECTED = 2;
	private static final int NO_MONEY= 3;
	private static final int CANT_BE_SOLD= 4;
	private MarketFrame(Player player,TownFrame townframe){
		this.player = player;
		this.townframe = townframe;
		this.collection = ItemsCollection.getInstace();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		player.addObserver(this);
		makeFrame();
		activateListeners();
		this.setPreferredSize(new Dimension(900,670));
		pack();
		setVisible(true);
	}
	/**
	 * Returns the instance of the market frame
	 * Singleton
	 * @param player
	 * @param townframe
	 * @return
	 */
	public static MarketFrame getInstance(Player player,TownFrame townframe){
		if(MarketFrame.instance == null){
			MarketFrame.instance = new MarketFrame(player,townframe);
		}
		return MarketFrame.instance;
	}
	private void makeFrame(){
		this.setLayout(new GridLayout(2,1));
		this.getContentPane().add(makePicturePanel());
		this.getContentPane().add(makeLowerPanel());
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
	private JPanel makePicturePanel(){
		picturePanel = new JPanel();
		picturePanel.setBackground(Color.BLACK);
		pictureLabel = new JLabel(new ImageIcon(this.getClass().getResource(PICTURE)));
		picturePanel.add(pictureLabel);
		picturePanel.setPreferredSize(new Dimension(640,pictureLabel.getIcon().getIconHeight()+20));
		return picturePanel;
	}
	private JPanel makeLowerPanel(){
		lowerPanel = new JPanel(new GridLayout(1,2));
		lowerPanel.add(makeMarketPanel());
		lowerPanel.add(makePlayerPanel());
		return lowerPanel;
	}
	private JPanel makeMarketPanel(){
		marketPanel = new JPanel(new BorderLayout());
		scrollerMarket = new JScrollPane(makeMarketInventoryTable());
		marketPanel.add(scrollerMarket,BorderLayout.CENTER);
		return marketPanel;
	}
	private JTable makeMarketInventoryTable(){
		marketInventoryTable = new JTable();
		itemModelMarket = new ItemsTableModel();
		this.marketsorter = new TableSorter(itemModelMarket);		
		marketInventoryTable.setModel(marketsorter);
		marketsorter.setTableHeader(marketInventoryTable.getTableHeader());
		fillMarketInventoryTable();
		marketInventoryTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
	
		return marketInventoryTable;
	}
	private JPanel makePlayerPanel(){
		playerPanel = new JPanel(new BorderLayout());
		scrollerPlayer = new JScrollPane(makePlayerMarketInventoryTable());
		playerPanel.add(scrollerPlayer,BorderLayout.CENTER);
		playerPanel.add(makePlayerButtonsPanel(),BorderLayout.SOUTH);
		playerPanel.add(makeSellInfoArea(),BorderLayout.NORTH);
		return playerPanel;
	}
	private JPanel makeSellInfoArea(){
		info = new JPanel(new BorderLayout());
		sellInfo = new JTextField();
		moneyInfo = new JTextField();
		
		String coins = new Integer(player.getCoins()).toString();
		moneyInfo.setText("You have " +coins+ " coins");
		
		sellInfo.setEditable(false);
		moneyInfo.setEditable(false);
		
		info.add(sellInfo, BorderLayout.NORTH);
		info.add(moneyInfo, BorderLayout.CENTER);

		return info;
	}

	private JTable makePlayerMarketInventoryTable(){
		playerInventoryTable = new JTable();
		itemModelPlayer = new playerItemsTableModel();
		this.playersorter = new TableSorter(itemModelPlayer);
		playerInventoryTable.setModel(playersorter);
		playersorter.setTableHeader(playerInventoryTable.getTableHeader());
		fillPlayerInventoryTable();
		playerInventoryTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		return playerInventoryTable;
	}
	private void fillMarketInventoryTable(){
		ArrayList<Item> tmplist = this.collection.getItemCollection(GameConstants.KEY_TO_GET_ALL);
		itemModelMarket.clearData();
		for(Item tmp : tmplist){
			itemModelMarket.insertRow(tmp);
		}
		this.marketsorter.asort();
		marketInventoryTable.updateUI();
		
	}
	private void fillPlayerInventoryTable(){
		ArrayList<Item> tmplist = player.getItems();
		itemModelPlayer.clearData();
		for(Item tmp : tmplist){
			itemModelPlayer.insertRow(tmp);
		}
		this.playersorter.asort();
		playerInventoryTable.updateUI();
	}
	/**
	 * Ectended from Observer
	 * Updates the players inventory table
	 */
	public void update(Observable obj,Object o){
		if(obj instanceof Player){
			if(o instanceof Integer){
				int code = (Integer)o;
				if(code == GameConstants.PLAYER_CHANGE_INVENTORY){
					itemModelPlayer.fireTableStructureChanged();
					fillPlayerInventoryTable();
					fillMarketInventoryTable();
				}
				if(code == GameConstants.PLAYER_CHANGE_MONEY){
					updatePlayersCoins();
				}
			}
		}
	}
	private JPanel makePlayerButtonsPanel(){
		playerButtonsPanel = new JPanel(new GridLayout(1,4));
		sell = new JButton("Sell item");
		buy = new JButton("Buy item");
		gotown = new JButton("Go to town");
		inspect = new JButton("Inspect item");
		playerButtonsPanel.add(buy);
		playerButtonsPanel.add(sell);
		playerButtonsPanel.add(inspect);
		playerButtonsPanel.add(gotown);
		return playerButtonsPanel;
	}
	private void activateListeners(){
		sell.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sellItem();};
		});
		gotown.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				goBackToTown();};
		});
		buy.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				buy();};
		});
		inspect.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				inspect();};
		});
	}
	private void sellItem(){
		int row = this.playerInventoryTable.getSelectedRow();
		if( row != -1){
			try{
			Item theitem = playersorter.getRow(row);
			if(theitem.isSellable()){
				int newmoney = theitem.getSellValue();
				player.addCoins(newmoney);
				player.removeItem(theitem);
				updateInfoField(theitem,newmoney,SELL);
			}else{
				updateInfoField(CANT_BE_SOLD);
			}
			}catch(Exception e){
				//happen when trying to sell again..
				updateInfoField(NONE_SELECTED);
			}
		}else{
			updateInfoField(NONE_SELECTED);
		}
		
	}
	private void goBackToTown(){
		this.setVisible(false);
		townframe.setVisible(true);
	}
	private void buy(){
		int row = this.marketInventoryTable.getSelectedRow();
		if( row != -1){
			Item theitem = marketsorter.getRow(row);
			int newmoney = theitem.getPurchaseValue();
			if(player.getCoins() >= newmoney){
				player.addItem(theitem);
				player.decreaseCoins(newmoney);
				updateInfoField(theitem,newmoney,BUY);
			}else{
				updateInfoField(NO_MONEY);
			}
		}else{
			updateInfoField(NONE_SELECTED);
		}

	}
	private void updateInfoField(int whattype){
		String message = new String();
		Color color = Color.white;
		switch(whattype){
		case NO_MONEY:
			message =  "You don't have enough money. Maybe you can sell something?";
			color = Color.red;
			break;
		case NONE_SELECTED:
			message =  "No item selected";
			color = Color.green;
			break;
		case CANT_BE_SOLD:
			message =  "This item can't be sold";
			color = Color.orange;
			break;
		}
		sellInfo.setText(message);
		sellInfo.setBackground(color);
	}
	private void updateInfoField(Item theitem,int newmoney,int whattype){
		String message = new String();
		Color color = Color.white;
		switch(whattype){
		case BUY:
			message = "You bought the "+theitem.getName() +" for "+newmoney+" coins";
			color = Color.magenta;
			break;
		case SELL:
			message =  "You sold the "+theitem.getName()+" for "+newmoney+" coins";
			color = Color.orange;
			break;
		}
		//updatePlayersCoins();//temp
		
		sellInfo.setText(message);
		sellInfo.setBackground(color);
	}
	private void updatePlayersCoins(){
		String coins = new Integer(player.getCoins()).toString();
		moneyInfo.setText("You have "+coins+ " coins");
	}
	private void inspect(){
		int rowMarket = this.marketInventoryTable.getSelectedRow();
		int rowPlayer = this.playerInventoryTable.getSelectedRow();
		Item theitem = null;
		if(rowMarket != -1){
			theitem = marketsorter.getRow(rowMarket);
		}else if(rowPlayer != -1){
			try{
				theitem = playersorter.getRow(rowPlayer);
			}catch(Exception e){
				updateInfoField(NONE_SELECTED);
				return;
			}
		}else{
			updateInfoField(NONE_SELECTED);
			return;
		}
		InspectFrame inspectframe = new InspectFrame(theitem);
	}
	/**
	 * Update the current player
	 * Used after load player
	 * @param player
	 */
	public void setPlayer(Player player){
		if(this.player != null){
			this.player.deleteObserver(this);
		}
		this.player = player;
		this.player.addObserver(this);
	}
}
