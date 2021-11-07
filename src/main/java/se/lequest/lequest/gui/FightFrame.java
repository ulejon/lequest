package se.lequest.lequest.gui;

import se.lequest.lequest.characters.Enemy;
import se.lequest.lequest.characters.Player;
import se.lequest.lequest.constants.GameConstants;
import se.lequest.lequest.gamelogic.Experience;
import se.lequest.lequest.gamelogic.GameLogic;
import se.lequest.lequest.items.Item;
import se.lequest.lequest.items.Weapon;
import se.lequest.lequest.maps.Segment;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * This class takes care of the Fight,, Player VS Enemy..
 */
public class FightFrame extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;
    private JPanel infoPanel;
    private JPanel playerInfo, enemyInfo;
    private JPanel buttonPanel;
    private JButton fight, run;
    private JTextArea textArea;
    private JPanel textPanel, imagePanel;
    private ScrollPane scroller;
    private JLabel fightImage, playerHeading, enemyHeading, currentWeaponHeading, currentWeaponInfoLabelHeading;
    private JLabel armorHeading, hpHeading;
    private JLabel armorLabel, hpLabel, currentWeaponNameLabel, currentWeaponInfoLabel, enemyCurrentWeaponstats;
    private JLabel enemyTypeHeading, enemyTypeLabel, enemyHpHeading, enemyHpLabel, enemyCurrentWeaponHeading, enemyCurrentWeaponLabel;
    private JLabel playerPicture, enemyPicture;
    private Player player;
    private Enemy theEnemy;
    private GameLogic gamelogic;
    private Experience experience;
    private MapFrame mainframe;

    /**
     * Creates a new FightFrame, makes the game gui invisible and enables it again on close.
     *
     * @param mainframe the game gui
     * @param player    the current player
     */
    public FightFrame(MapFrame mainframe, Player player) {
        this.mainframe = mainframe;
        this.player = player;
        this.theEnemy = player.getCurrentMap().getSegment(player.getCurrentPosition()).getAEnemy();
        mainframe.setVisible(false);
        makeFrame();
        activateListeners();
        updateCurrentWeapon();
        updateCurrentArmor();
        updateHp();
        updateEnemyHP();
        updateEnemyType();
        updateEnemyCurrentWeapon();
        updatePicture();
        player.addObserver(this);
        theEnemy.addObserver(this);
        gamelogic = GameLogic.getInstance();
        experience = Experience.getInstance();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setDefaultLookAndFeelDecorated(true);
        pack();
        setVisible(true);

    }

    private void makeFrame() {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.getContentPane().add(makeImagePanel());
        this.getContentPane().add(makeInfoPanel());
        this.getContentPane().add(makeAreaPanel());
        this.getContentPane().add(makeButtonPanel());
        //this.setPreferredSize(new Dimension(670,450));
    }

    private JPanel makeImagePanel() {
        imagePanel = new JPanel();
        imagePanel.setLayout(new GridLayout(1, 3));
        imagePanel.setBackground(Color.black);
        //imagePanel.setBorder(new EtchedBorder(Color.green, Color.green));
        playerPicture = new JLabel();
        imagePanel.add(playerPicture);
        fightImage = new JLabel(new ImageIcon(this.getClass().getResource("/pictures/fight.jpg")));
        imagePanel.add(fightImage);
        enemyPicture = new JLabel();
        imagePanel.add(enemyPicture);
        return imagePanel;
    }

    private JPanel makeAreaPanel() {

        textPanel = new JPanel();
        textPanel.setBackground(Color.black);
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText("");
        textArea.setCaretPosition(textArea.getDocument().getLength());
        textArea.setBackground(Color.black);
        textArea.setForeground(Color.red);
        scroller = new ScrollPane();
        scroller.setPreferredSize(new Dimension(600, 100));
        scroller.add(textArea);
        textPanel.add(scroller);
        return textPanel;
    }

    private JPanel makeButtonPanel() {
        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBorder(new EtchedBorder());
        buttonPanel.setBackground(Color.black);
        fight = new JButton("Fight");
        run = new JButton("Run");
        buttonPanel.add(fight);
        buttonPanel.add(run);
        return buttonPanel;
    }

    private JPanel makeInfoPanel() {
        /*
         * Player stuff below
         */
        infoPanel = new JPanel(new GridLayout(1, 2));
        infoPanel.setBorder(new EtchedBorder(Color.red, Color.black));
        //infoPanel.setBackground(Color.red);
        playerInfo = new JPanel(new GridLayout(0, 2));
        playerInfo.setBorder(new EtchedBorder());
        playerHeading = new JLabel("Player info");
        currentWeaponHeading = new JLabel("Current weapon: ");
        infoPanel.add(playerInfo);
        playerInfo.add(playerHeading);
        //playerPicture = new JLabel();
        //playerInfo.add(playerPicture);
        playerInfo.add(new JLabel()); //Dummy
        armorHeading = new JLabel("Armor:");
        armorLabel = new JLabel();
        hpHeading = new JLabel("HP: ");
        playerInfo.add(armorHeading);
        playerInfo.add(armorLabel);
        playerInfo.add(hpHeading);
        hpLabel = new JLabel();
        playerInfo.add(hpLabel);
        playerInfo.add(currentWeaponHeading);
        currentWeaponNameLabel = new JLabel();
        playerInfo.add(currentWeaponNameLabel);
        currentWeaponInfoLabelHeading = new JLabel("Info about weapon: ");
        playerInfo.add(currentWeaponInfoLabelHeading);
        currentWeaponInfoLabel = new JLabel();
        playerInfo.add(currentWeaponInfoLabel);

        /*
         * Enemy stuff below
         */
        enemyInfo = new JPanel(new GridLayout(0, 2));
        enemyInfo.setBorder(new EtchedBorder());
        infoPanel.add(enemyInfo);
        enemyHeading = new JLabel("Enemy info");
        enemyInfo.add(enemyHeading);
        //enemyPicture = new JLabel();
        //enemyInfo.add(enemyPicture);
        enemyInfo.add(new JLabel()); //Dummy
        enemyHpHeading = new JLabel("HP: ");
        enemyInfo.add(enemyHpHeading);
        enemyHpLabel = new JLabel("9999 / 9999");
        enemyInfo.add(enemyHpLabel);
        enemyTypeHeading = new JLabel("Enemytype: ");
        enemyInfo.add(enemyTypeHeading);
        enemyTypeLabel = new JLabel("Enemytypegoeshere");
        enemyInfo.add(enemyTypeLabel);
        enemyCurrentWeaponHeading = new JLabel("CurrentWeapon: ");
        enemyInfo.add(enemyCurrentWeaponHeading);
        enemyCurrentWeaponLabel = new JLabel("Enemycurrentweapon");
        enemyInfo.add(enemyCurrentWeaponLabel);
        enemyInfo.add(new JLabel("Info About Weapon"));
        enemyCurrentWeaponstats = new JLabel("Weponstats");
        enemyInfo.add(enemyCurrentWeaponstats);
        return infoPanel;
    }

    private void activateListeners() {
        fight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fight();
            }
        });
        run.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                run();
            }
        });
    }

    private void fight() {
        this.textArea.setText(gamelogic.startFightRound(player, theEnemy));
        if (!player.isAlive()) {
            playerisDead();
        }
        if (!theEnemy.isAlive()) {
            //the enemy is dead..
            Segment theroom = player.getCurrentMap().getSegment(player.getCurrentPosition());
            theroom.addItem(theEnemy.getCurrentWeapon());
            List<Item> itemlist = theEnemy.getItems();
            for (Item temp : itemlist) {
                theroom.addItem(temp);
            }
            //adds experience to player. if he levels up,
            //set a new value to next experience level.
            if (player.addExperience(theEnemy.getExperiencePoints(), experience.getNextExperienceLevel())) {
                experience.setNextExperienceLevel(player.getPlayerLevel());
            }
            theroom.removeEnemy(theEnemy);
            javax.swing.JOptionPane.showMessageDialog(null, "The enemy is now dead...\n" +
                    "You gained " + theEnemy.getExperiencePoints() + " experience points.");
            frameExit();
        }

    }

    private void run() {
        if (!gamelogic.playerTryToRun(player)) {
            javax.swing.JOptionPane.showMessageDialog(null, "You cant run the Enemy is blocking the way! ");
            //the Enemy will still hit you ! =)
            this.textArea.setText(gamelogic.EnemyHitsPlayer(player, theEnemy));
            if (!player.isAlive()) {
                playerisDead();
            }
        } else {
            frameExit();
        }
    }

    /*
     * What happens when the player dies..
     */
    private void playerisDead() {
        javax.swing.JOptionPane.showMessageDialog(null, "You were killed.. GAME OVER ");
        System.exit(0);
    }

    /**
     * Extended from Observer..
     */
    public void update(Observable obj, Object o) {
        if (o instanceof Integer) {
            int updatecode = ((Integer) o).intValue();
            switch (updatecode) {
                case GameConstants.PLAYER_CHANGE_HP:
                    this.player = ((Player) obj);
                    updateHp();
                    break;
                case GameConstants.PLAYER_CHANGE_CURRENTWEAPON:
                    this.player = ((Player) obj);
                    updateCurrentWeapon();
                    break;
                case GameConstants.ENEMY_CHANGE_HP:
                    this.theEnemy = ((Enemy) obj);
                    updateEnemyHP();
                    break;
                case GameConstants.PLAYER_CHANGE_ARMOR:
                    this.player = ((Player) obj);
                    updateCurrentArmor();
                    break;
            }
        }
    }

    /*
     * Updates the PlayersCurrentWeapon in the fightgui..
     *
     */
    private void updateCurrentWeapon() {
        Weapon weapon = player.getCurrentWeapon();
        currentWeaponNameLabel.setText(weapon.getName());
        currentWeaponInfoLabel.setText("Damage: (" + weapon.getMinDamageValue() + "-" + weapon.getMaxDamageValue() + ")");
    }

    /*
     * Updates the PlayersArmor in the fightgui..
     *
     */
    private void updateCurrentArmor() {
        armorLabel.setText("Max: " + player.getActiveArmor().getTotalMaximumProtection()
                + " ,Min: " + player.getActiveArmor().getTotalMinimumProtection());
    }

    /*
     * Updates the Players Hp in the fightgui..
     *
     */
    private void updateHp() {
        hpLabel.setText(player.getHealth() + " / " + player.getMaxHealth());
    }

    /*
     * Updates the Enemys Hp in the fightgui..
     *
     */
    private void updateEnemyHP() {
        this.enemyHpLabel.setText(theEnemy.getHealth() + " / " + theEnemy.getMaxHealth());
    }

    /*
     * Updates the Enemys type..
     *
     */
    private void updateEnemyType() {
        this.enemyTypeLabel.setText(theEnemy.getName());
    }

    private void updateEnemyCurrentWeapon() {
        Weapon EnemyWeapon = theEnemy.getCurrentWeapon();
        this.enemyCurrentWeaponLabel.setText(EnemyWeapon.getName());
        this.enemyCurrentWeaponstats.setText("Damage: (" + EnemyWeapon.getMinDamageValue() + "-" + EnemyWeapon.getMaxDamageValue() + ")");
    }

    private void updatePicture() {
        ImageIcon playerIcon = player.getPicture();
        ImageIcon enemyIcon = theEnemy.getPicture();
        if (playerIcon != null) {
            playerPicture.setIcon(playerIcon);
        } else {
            playerPicture.setText("No picture");
        }
        if (enemyIcon != null) {
            enemyPicture.setIcon(enemyIcon);
        } else {
            enemyPicture.setText("No picture");
        }

    }

    private void frameExit() {
        this.setVisible(false);
        if (!gamelogic.checkifStartFrame(player)) {
            mainframe.setVisible(true);
        }
    }
}
