package gui;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import characters.Player;
import gamelogic.GameLogic;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.JScrollPane;

import java.awt.event.ActionEvent;
import constants.GameConstants;

/**
 * Class to make the map frame. This is the most important frame of the game
 * This holds the map,playerinfo,roominfo etc
 */
public class MapFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private GameLogic gamelogic;
	@SuppressWarnings("unused")
	private Player player;
	private TownFrame townframe; 
	private MapPanel mappanel;
	private PlayerPanel playerpanel;
	private ControlPanel controlpanel;
	private MapMenuBar menubar;
	private static MapFrame instace;
	private MapFrame(Player player,TownFrame townframe){
		super(GameConstants.NAME +" v."+ GameConstants.VERSION);
		this.townframe = townframe;
		this.gamelogic = GameLogic.getInstance(this);        
		this.setLayout(new BorderLayout());
		this.player = player;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JScrollPane thescroller = new JScrollPane();
		mappanel = new MapPanel(player);
		thescroller.getViewport().add(mappanel);
		
		this.getContentPane().add(thescroller, BorderLayout.CENTER);
		playerpanel = new PlayerPanel(player);
		this.getContentPane().add(playerpanel, BorderLayout.WEST);
		controlpanel = new ControlPanel(player);
		this.getContentPane().add(controlpanel, BorderLayout.EAST);
		menubar = new MapMenuBar(player);
		this.setJMenuBar(menubar);
		JComponent c = this.getRootPane();
		c.getInputMap().put(KeyStroke.getKeyStroke(GameConstants.KEY_MOVE_NORTH,0), "Y");
        c.getActionMap().put("Y", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
            	movenorth();
            }
        });
        c.getInputMap().put(KeyStroke.getKeyStroke(GameConstants.KEY_MOVE_SOUTH,0), "X");
        c.getActionMap().put("X", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
            	movesouth();
            }
        });
        c.getInputMap().put(KeyStroke.getKeyStroke(GameConstants.KEY_MOVE_WEST,0), "Z");
        c.getActionMap().put("Z", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
            	movewest();
            }
        });
        c.getInputMap().put(KeyStroke.getKeyStroke(GameConstants.KEY_MOVE_EAST,0), "K");
        c.getActionMap().put("K", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
            	moveeast();
            }
        });
        
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.pack();
		this.setLocationRelativeTo(null);	//centralized window
		this.setBounds(new java.awt.Rectangle(GameConstants.WINDOW_STARUP_WIDTH,GameConstants.WINDOW_STARUP_HEIGHT));
		
		this.setVisible(true);
	}
	private void movenorth(){
		this.gamelogic.move(player, GameLogic.NORTH);
	}
	private void moveeast(){
		this.gamelogic.move(player, GameLogic.EAST);
	}
	private void movewest(){
		this.gamelogic.move(player, GameLogic.WEST);
	}
	private void movesouth(){
		this.gamelogic.move(player, GameLogic.SOUTH);
	}
	/**
	 * Returns the instance of the town frame
	 * @return
	 */
	public TownFrame getTownFrame(){
		return this.townframe;
	}
	/**
	 * Returns the instance of the map gui
	 * Singleton
	 * @param player
	 * @param townframe
	 * @return
	 */
	public static MapFrame getInstace(Player player,TownFrame townframe){
		if(MapFrame.instace == null){
			MapFrame.instace = new MapFrame(player,townframe);
		}
		return MapFrame.instace;
	}
	/**
	 * Update the current player
	 * Used after load player
	 * @param player
	 */
	public void setPlayer(Player player){
		this.player = player;
		mappanel.setPlayer(player);
		townframe.setPlayer(player);
		playerpanel.setPlayer(player);
		controlpanel.setPlayer(player);
		menubar.setPlayer(player);
	}
}
