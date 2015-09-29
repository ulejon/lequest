package gui;

import javax.swing.JFrame;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.*;
import constants.GameConstants;

/**
 * Class to show a frame with the intro of the game
 * Changes info text and picture every 8 seconds
 *
 */
public class IntroFrame extends JFrame implements ActionListener{
	private JPanel picturePanel,lowerPanel,buttonPanel;
	private JLabel pictureLabel;
	private JTextArea textArea;
	private JButton skip;
	private final int period = 8000;
	private int index = 0;
	private BufferedReader br;
	private String[] pictures = {"town.jpg","cave.jpg","burning.jpg","princess.jpg","hero.jpg","introbild.jpg"};
	private TownFrame townframe;
	javax.swing.Timer timer = new javax.swing.Timer(period,this);
	/**
	 * Creates a new intro frame
	 *
	 */
	public IntroFrame(){
		br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/texts/introtext")));
		makeFrame();
		activateListeners();
		pack();
		setVisible(true);
		start();
		
	}
	private void makeFrame(){
		this.setLayout(new BorderLayout());
		this.getContentPane().add(makePicturePanel(),BorderLayout.CENTER);
		this.getContentPane().add(makeLowerPanel(),BorderLayout.SOUTH);
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
	private JPanel makePicturePanel(){
		picturePanel = new JPanel();
		pictureLabel = new JLabel();
		picturePanel.add(pictureLabel);
		picturePanel.setPreferredSize(new Dimension(800,340));
		setPicture();
		return picturePanel;
	}
	private JPanel makeLowerPanel(){
		lowerPanel = new JPanel(new BorderLayout());
		lowerPanel.add(makeTextArea(),BorderLayout.CENTER);
		lowerPanel.add(makeButtonPanel(),BorderLayout.SOUTH);
		return lowerPanel;
	}
	private JPanel makeButtonPanel(){
		buttonPanel = new JPanel(new FlowLayout());
		skip = new JButton("Skip intro");
		buttonPanel.add(skip);
		return buttonPanel;
	}
	private JTextArea makeTextArea(){
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBorder(new EtchedBorder());
		textArea.setFont(GameConstants.DEFAULT_FONT);
		setText();
		return textArea;
	}
	private void activateListeners(){
		skip.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				stop();
			}
		});
	}
	private void start(){
		timer.start();
	}
	private void stop(){
		timer.stop();
		try{
			br.close();
		}catch(IOException e){;}
		this.setVisible(false);
		townframe = TownFrame.getInstace();
	}
	/**
	 * Changes text and picture every  seconds
	 */
	public void actionPerformed(ActionEvent e){
		setText();
		setPicture();
	}
	private void setPicture(){
		if(!(index >= pictures.length)){
				pictureLabel.setIcon(new ImageIcon(this.getClass().getResource("/pictures/"+pictures[index])));
			index++;
		}else{
			stop();
		}
	}
	private void  setText(){
		textArea.setText("");
		String line = "   ";
		try{
			while((line = br.readLine()) != null){
				if(!line.startsWith("#")){
					textArea.append(line+"\n");
				}else {
					break;
				}
			}
		}catch(IOException e){
			//TODO: do something here?
		}
	}
}

