package gui;

import highscore.Highscore;

import javax.swing.*;

import java.awt.*;
import javax.swing.border.EtchedBorder;
import maps.*;
import constants.GameConstants;
import java.util.Observer;
import java.util.Observable;
import java.awt.event.*;
import characters.*;

import java.io.*;
import javax.swing.filechooser.FileFilter;

/**
 * The town gui
 *
 */
public class TownFrame extends JFrame implements Observer{
	private static final String PICTURE 	 = 	"/pictures/town.jpg";
	private static final String WISEMAN_INFO = 	"At the wise man you can get some advice on what to do next" +
												", learn magic or go on a quest";
	private static final String MAP_INFO     = 	"Click the button below to go back to the map view";
	private static final String TAVERN_INFO  = 	"At the tavern you can get some rest to gain health." +
											  	"You can also have something to eat, which" +
											  	"also increases you health";
	private static final String MARKET_INFO  = 	"At the market you can buy food,armor,weapons and potions" +
											  	"to bring with you on your quest";
	private static final String TOWN_INFO    = 	"Welcome to Le Town :) The best town in the world, "+
												"Add more info here";
											
	private ImageIcon picture;
	private JLabel townImageLabel;
	private JPanel lowerPanel,centerPanel,eastPanel,southPanel;
	private JPanel marketPanel,tavernPanel,mapPanel,wisemanPanel;
	private JPanel playerInfoButtonPanel,marketButtonPanel,tavernButtonPanel,mapButtonPanel
					,wisemanButtonPanel;
	private JPanel playerInfoPanel,townImagePanel;
	private JLabel playerInfoHeading,townInfoHeading;
	private JTextArea marketInfoArea,tavernInfoArea,mapInfoArea,wisemanInfoArea,townInfoArea,playerInfoArea
					  ,townPositionArea;
	private JButton marketButton,tavernButton,mapButton,wisemanButton,
					newGameButton,loadButton,saveButton,quitButton,highscoreButton;
	private Player player;
	private static TownFrame instace;
	private TavernFrame tavernframe;
	private WiseManFrame wisemanframe;
	private MapFrame thegame;
	private MarketFrame marketframe;
	private TownFrame(){
		this.picture = new ImageIcon(this.getClass().getResource(PICTURE));
		makeFrame();
		updatePlayerInfo();
		setPreferredSize(new Dimension(550,660));
		pack();
		setVisible(true);
		
	}
	/**
	 * Returns the instance of the townframe
	 * Singleton
	 * @return
	 */
	public static TownFrame getInstace(){
		if(TownFrame.instace == null){
			TownFrame.instace = new TownFrame();
		}
		return TownFrame.instace;
	}
	private void makeFrame(){
		this.setLayout(new BorderLayout());
		makeTownImagePanel();
		makeLowerPanel();
		activateListeners();
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
	private void makeTownImagePanel(){
		townImagePanel = new JPanel();
		townImagePanel.setBackground(Color.BLACK);
		townImageLabel = new JLabel(picture);
		townImagePanel.add(townImageLabel);
		this.getContentPane().add(townImagePanel,BorderLayout.NORTH);
	}
	private void makeLowerPanel(){
		lowerPanel = new JPanel();
		this.getContentPane().add(lowerPanel,BorderLayout.CENTER);
		lowerPanel.setLayout(new BorderLayout());
		lowerPanel.add(makePlayerInfoPanel(),BorderLayout.WEST);
		lowerPanel.add(makeCenterPanel(),BorderLayout.CENTER);
		//lowerPanel.add(makeEastPanel(),BorderLayout.EAST);
		lowerPanel.add(makeSouthPanel(),BorderLayout.SOUTH);
	}
	private JPanel makeSouthPanel(){
		southPanel = new JPanel();
		townPositionArea = makeTextArea("You are in the town center. "
										+"Feel free to look around,\nvisit" +
												" the wise man or have a" +
												" drink at the tavern");
		townPositionArea.setLineWrap(false);
		
		southPanel.add(townPositionArea);
		townPositionArea.setBackground(Color.orange);
		return southPanel;
	}
	private JPanel makeTownInfoPanel(){
		eastPanel = new JPanel(new BorderLayout());
		eastPanel.setBorder(new EtchedBorder());
		townInfoHeading = new JLabel("Town Info");
		eastPanel.add(townInfoHeading,BorderLayout.NORTH);
		townInfoArea = makeTextArea(TOWN_INFO);
		townInfoArea.setBackground(eastPanel.getBackground());
		eastPanel.add(townInfoArea,BorderLayout.CENTER);
		townInfoArea.setEditable(false);
		townInfoArea.setBackground(Color.orange);
		return eastPanel;
	}
	private JPanel makeCenterPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.add(makeTownInfoPanel());
		centerPanel = new JPanel(new GridLayout(2,2));
		centerPanel.setBorder(new EtchedBorder());
		centerPanel.add(makeMarketPanel());
		centerPanel.add(makeMapPanel());
		centerPanel.add(makeTavernPanel());
		centerPanel.add(makeWisemanPanel());
		panel.add(centerPanel);
		return panel;
	}
	private JPanel makeWisemanPanel(){
		wisemanPanel = new JPanel(new BorderLayout());
		wisemanPanel.setBorder(new EtchedBorder());
		wisemanInfoArea = makeTextArea(WISEMAN_INFO);
		wisemanInfoArea.setBackground(wisemanPanel.getBackground());
		wisemanPanel.add(wisemanInfoArea,BorderLayout.NORTH);
		wisemanButton = new JButton("Wiseman");
		wisemanButtonPanel = new JPanel(new FlowLayout());
		wisemanButton = new JButton("Wiseman");
		wisemanButtonPanel.add(wisemanButton);
		wisemanPanel.add(wisemanButtonPanel,BorderLayout.SOUTH);
		
		return wisemanPanel;
	}
	private JPanel makeMapPanel(){
		mapPanel = new JPanel(new BorderLayout());
		mapPanel.setBorder(new EtchedBorder());
		mapInfoArea = makeTextArea(MAP_INFO);
		mapInfoArea.setBackground(mapPanel.getBackground());
		mapPanel.add(mapInfoArea,BorderLayout.NORTH);
		mapButtonPanel = new JPanel(new FlowLayout());
		mapButton = new JButton("Map");
		mapButtonPanel.add(mapButton);
		mapPanel.add(mapButtonPanel,BorderLayout.SOUTH);
		return mapPanel;
	}
	private JPanel makeTavernPanel(){
		tavernPanel = new JPanel(new BorderLayout());
		tavernPanel.setBorder(new EtchedBorder());
		tavernInfoArea = makeTextArea(TAVERN_INFO);
		tavernInfoArea.setBackground(tavernPanel.getBackground());
		tavernPanel.add(tavernInfoArea,BorderLayout.NORTH);
		tavernButtonPanel = new JPanel(new FlowLayout());
		tavernButton = new JButton("Tavern");
		tavernButtonPanel.add(tavernButton);
		tavernPanel.add(tavernButtonPanel,BorderLayout.SOUTH);
		return tavernPanel;
	}
	private JPanel makeMarketPanel(){
		marketPanel = new JPanel(new BorderLayout());
		marketPanel.setBorder(new EtchedBorder());
		marketInfoArea = makeTextArea(MARKET_INFO);
		marketInfoArea.setBackground(marketPanel.getBackground());
		marketPanel.add(marketInfoArea,BorderLayout.NORTH);
		marketButtonPanel = new JPanel(new FlowLayout());
		marketButton = new JButton("Market");
		marketButtonPanel.add(marketButton);
		marketPanel.add(marketButtonPanel,BorderLayout.SOUTH);
		return marketPanel;
	}
	private JPanel makePlayerInfoPanel(){
		playerInfoPanel = new JPanel(new BorderLayout());
		playerInfoPanel.setBorder(new EtchedBorder());
		playerInfoHeading = new JLabel("Player info");
		playerInfoPanel.add(playerInfoHeading,BorderLayout.NORTH);
		playerInfoArea = makeTextArea("");
		playerInfoArea.setBackground(playerInfoPanel.getBackground());
		playerInfoPanel.add(playerInfoArea,BorderLayout.CENTER);
		playerInfoButtonPanel = new JPanel(new GridLayout(0,1));
		newGameButton = new JButton("New game");
		saveButton = new JButton("Save");
		loadButton = new JButton("Load");
		highscoreButton = new JButton("HighScore");
		quitButton = new JButton("Quit");
		playerInfoButtonPanel.add(newGameButton);
		playerInfoButtonPanel.add(saveButton);
		playerInfoButtonPanel.add(loadButton);
		playerInfoButtonPanel.add(highscoreButton);
		playerInfoButtonPanel.add(quitButton);
		playerInfoPanel.add(playerInfoButtonPanel,BorderLayout.SOUTH);
		return playerInfoPanel;
	}
	private void activateListeners(){
		marketButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				goToMarket();
			};
		});
		tavernButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){goToTavern();};
		});
		mapButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){goToMap();};
		});
		wisemanButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){goToWiseman();};
		});
		newGameButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				createNewPlayer();				
				}
		});
		loadButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				loadPlayer();
				}
		});
		saveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				savePlayer();
				}
		});
		highscoreButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				openHighScore();
				}
		});
		quitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				FileIO.fileIO.saveHighScore();
				System.exit(0);};
		});
	}
	
	/*
	 * Makes the TextAreas, and settings for it.
	 * @param the text
	 * return JTextArea
	 */

	private JTextArea makeTextArea(String text){
		JTextArea textarea = new JTextArea(text);
		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true);
		textarea.setEditable(false);
		textarea.setFont(GameConstants.DEFAULT_FONT);
		return textarea;
	}
	private void createNewPlayer(){
		String name = JOptionPane.showInputDialog(this,"Please enter your name"); //TODO: maybe level1 from somewhere..
		if((name != null) && !(name.equals(""))){
			Player newPlayer = new Player(name, GameConstants.MAX_PLAYER_HEALTH, GameConstants.MAX_PLAYER_HEALTH,new CatacombMap());
			if(this.thegame != null){//if the game already started.. then thegame exsist and can update the Mainframe
				this.thegame.setPlayer(newPlayer);
			}else{
				this.setPlayer(newPlayer);
			}
			this.player.updateObservers();
			updatePlayerInfo();
		}
	}
	private void loadPlayer(){
		Player newplayer = FileIO.fileIO.loadPlayer();
		if(newplayer != null){
		if(this.thegame != null){//game has already started.. mainframe exist.. update all..
			this.thegame.setPlayer(newplayer);
		}else{
			//game has not started.. only this frame to update..
			this.setPlayer(newplayer);
		}
		this.player.updateObservers();//make all observers update the new info..
		}
	}
	private void savePlayer(){
		if(this.player == null){
			noPlayerCreated();
		}else{
			FileIO.fileIO.savePlayer(player);
		}
	}
	private void updatePlayerInfo(){
		if(this.player != null){
			playerInfoArea.setText("\n"+"Name: "+player.getName()
				+"\nHealth: "+player.getHealth() + "/" + player.getMaxHealth()
				+"\nMoney: "+player.getCoins()
				+"\n\n");
		}else{
			playerInfoArea.setText("Press the 'New game' button first to create a player");
		}
	}
	private void goToMap(){
		if(this.player == null){
			noPlayerCreated();
		}else{
			this.player.getCurrentMap().generateEnemys();
			this.setVisible(false);
			thegame = MapFrame.getInstace(player, this);
			thegame.setVisible(true);
		}
	}
	private void goToTavern(){
		if(this.player == null){
			noPlayerCreated();
		}else{
			this.tavernframe = TavernFrame.getInstance(this, player);
			this.setVisible(false);
			tavernframe.setVisible(true);
		}
	}
	private void noPlayerCreated(){
		JOptionPane.showMessageDialog(null, "You must create a player first!, use new Game button");
	}
	/**
	 * Ectended from Observer
	 * Updates the player info
	 */
	public void update(Observable obj,Object o){
		if(obj instanceof Player){
			updatePlayerInfo();
		}
	}
	private void goToMarket(){
		if(this.player == null){
			noPlayerCreated();
		}else{
			marketframe = MarketFrame.getInstance(player, this);
			marketframe.setVisible(true);
			this.setVisible(false);
		}
	}
	private void goToWiseman(){
		if(this.player == null){
			noPlayerCreated();
		}else{
			wisemanframe = WiseManFrame.getInstance(player, this);
			wisemanframe.setVisible(true);
			this.setVisible(false);
		}
	}
	/**
	 * Updates the player
	 * @param player
	 */
	public void setPlayer(Player player){
		if(this.player != null){
			this.player.deleteObserver(this);
			
		}
		if(this.marketframe != null){
			this.marketframe.setPlayer(player);
		}
		this.player = player;
		this.player.addObserver(this);
	}
	private void openHighScore(){
		if(this.player != null){
			Highscore highobj = Highscore.getInstance();
			highobj.insertPlayer(player);
		}
		HighscoreFrame highframe = new HighscoreFrame();
	}
}