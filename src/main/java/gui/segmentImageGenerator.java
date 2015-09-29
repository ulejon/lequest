package gui;
import java.awt.image.*;
import maps.Segment;

import javax.swing.*;
/**
 * This class generates the images that represent segments (rooms)
 * on the mapviewer
 *
 */
public class segmentImageGenerator {
	private int picWidth;
	private int picHeight;
	private final int startsegcolor = 0x000000FF; //BLUE
	private final int segmentbgcolor = 0x99999999;
	private int foreColor = 0xFFFFFFFF; //White (walls) 0xAARRGGBB
	private int backGroundColor = 0x00000000;//Black
	private int playerColor = 0x00FF0000; //RED
	private int endcolor = 0x00FF0000; //RED
	private int bosscolor = 0x00505000; //BLUE
	private int sizeofdoorinpixels;
	private final int widthofwallsinpixels = 2;
	/**
	 * Creates a Segment image Generator object.. 
	 * it will generate imageIcons of size width * width
	 * @param width
	 */
	public segmentImageGenerator(int width){
		this.picWidth = width;
		this.picHeight = width;
		this.sizeofdoorinpixels = picWidth / 6;
		
	}
	/**
	 * Generates a ImageIcon representing the Segments doors..
	 * if player is on segment then add true on isplayeronsegment
	 * @param thesegment
	 * @return
	 */
	public ImageIcon getImageIcon(Segment thesegment,boolean isplayeronsegment){
		BufferedImage newImage = new BufferedImage(picWidth,picHeight,BufferedImage.TYPE_INT_BGR);
		int back = this.backGroundColor;
		int fore = this.foreColor;
		this.backGroundColor = segmentbgcolor;
		if(thesegment.istart()){//this is the startsegemnt.. different color then others..	
			this.foreColor = this.startsegcolor;
			newImage = setAllWallsAndBackground(newImage);
			this.foreColor = fore;
			
		}else if(thesegment.isEnd()){//this is the endsegment.. different color then others..
			this.foreColor = this.endcolor;
			newImage = setAllWallsAndBackground(newImage);
			this.foreColor = fore;
		}else if(thesegment.getIsBoss()){
			this.foreColor = bosscolor;
			newImage = setAllWallsAndBackground(newImage);
			this.foreColor = fore;
		}else{		
		
			newImage = setAllWallsAndBackground(newImage);
		}
		this.backGroundColor = 0x00000000;
		if(thesegment.isNorthDoorOpen()){
			newImage = setNorthDoor(newImage);
		}
		if(thesegment.isSouthDoorOpen()){
			newImage = setSouthDoor(newImage);
		}
		if(thesegment.isEastDoorOpen()){
			newImage = setEastDoor(newImage);
		}
		if(thesegment.isWestDoorOpen()){
			newImage = setWestDoor(newImage);
		}
		if(isplayeronsegment){
			newImage = setPlayerSpot(newImage);
		}
				
		ImageIcon theicon = new ImageIcon(newImage);//debugg.. use newImage
		return theicon;
	}
	/**
	 * Returns a ImageIcon that is solid backcolor, no walls..
	 * @return
	 */
	public ImageIcon getSolidBackgroundPicture(){
		BufferedImage newImage = new BufferedImage(picWidth,picHeight,BufferedImage.TYPE_INT_BGR);
		newImage = setSolidBackColor(newImage);
		ImageIcon theicon = new ImageIcon(newImage);
		return theicon;
	}
	/*
	 * Creates a Image with all walls and the Background color
	 * @param aImage
	 * @return
	 */
	private BufferedImage setAllWallsAndBackground(BufferedImage aImage){
		aImage =  setSolidBackColor(aImage);
		aImage =  setNorthWall(aImage);
		aImage =  setSouthWall(aImage);
		aImage =  setEastWall(aImage);
		aImage =  setWestWall(aImage);
		return aImage;
	}
	/*
	 * Creates a Image with solid Backcolor..
	 * @param aImage
	 * @return
	 */
	private BufferedImage setSolidBackColor(BufferedImage aImage){
		int maxx = aImage.getWidth();
		int maxy = aImage.getHeight();
		for(int i = 0; i < maxy;i++){
			for(int j = 0; j < maxx;j++){
				aImage.setRGB(i, j, this.backGroundColor);
			}
		}
		
		return aImage;
	}
	/*
	 * Creates the NorthWall
	 * @param aImage
	 * @return
	 */

	private BufferedImage setNorthWall(BufferedImage aImage){
		return setHorizontalLine(aImage,0);
	}
	/*
	 * Creates the Southwall
	 * @param aImage
	 * @return
	 */
	private BufferedImage setSouthWall(BufferedImage aImage){
		return setHorizontalLine(aImage,aImage.getHeight() - widthofwallsinpixels);
	}
	/*
	 * Creates the Westwall
	 * @param aImage
	 * @return
	 */
	private BufferedImage setWestWall(BufferedImage aImage){
		return setVerticalline(aImage,0);
	}
	/*
	 * Creates the EastWall
	 * @param aImage
	 * @return
	 */
	private BufferedImage setEastWall(BufferedImage aImage){
		return setVerticalline(aImage,aImage.getWidth() - widthofwallsinpixels);
	}
	/*
	 * Creates a horizontal line in the image widthofwallsinpixels wide starting at height
	 * @param aImage
	 * @param height
	 * @return
	 */
	private BufferedImage setHorizontalLine(BufferedImage aImage,int height){
		int maxx = aImage.getWidth();
		//set the north wall
		for(int j = height; j < height + widthofwallsinpixels; j++){
			for(int i = 0; i < maxx; i++){
								//0xAARRGGBB
					aImage.setRGB(i,j,foreColor);
			}
		}
		return aImage;
	}
	/*
	 * Creates a verticalline on the image widthofwallsinpixels wide starting at atxvalue
	 * @param aImage
	 * @param atxvalue
	 * @return
	 */
	private BufferedImage setVerticalline(BufferedImage aImage,int atxvalue){
		int maxy = aImage.getHeight();
		for(int i = 0; i < maxy; i++){
			for(int j = atxvalue; j < atxvalue + widthofwallsinpixels;j++){
				aImage.setRGB(j, i, foreColor);
			}			
		}		
		return aImage;
	}
	/*
	 * Creates a door on the northwall.. northwall must be created first
	 * @param aImage
	 * @return
	 */
	private BufferedImage setNorthDoor(BufferedImage aImage){
		return setHorizontalDoor(aImage,0);
	}
	/*
	 * Creates a door on the Southwall.. Southwall must be created first
	 * @param aImage
	 * @return
	 */
	private BufferedImage setSouthDoor(BufferedImage aImage){
		return setHorizontalDoor(aImage,aImage.getHeight() - widthofwallsinpixels );
	}
	/*
	 * Creates the door on the WestWall.. WestWall must be created first
	 * @param aImage
	 * @return
	 */
	private BufferedImage setWestDoor(BufferedImage aImage){
		return setVerticalDoor(aImage,0);
	}
	/*
	 * Creates the door on the EastWall.. EastWall must be created first
	 * @param aImage
	 * @return
	 */
	private BufferedImage setEastDoor(BufferedImage aImage){
		return setVerticalDoor(aImage,aImage.getWidth() - widthofwallsinpixels);
	}
	/*
	 * Creates a Horizontal door.. used by East & West doorfunctions
	 * @param aImage
	 * @return
	 */
	private BufferedImage setHorizontalDoor(BufferedImage aImage, int height){
		int maxx = aImage.getWidth();
		//set the dooropening.. 
		for(int i = (sizeofdoorinpixels *-1); i < sizeofdoorinpixels; i++){
			for(int j = height; j < height + widthofwallsinpixels; j++){
				aImage.setRGB(maxx/2 +i,j,backGroundColor);
			}	
		}
		return aImage;
	}
	/*
	 * Creates a Verticaldoor used by south and north doorfunctions
	 * @param aImage
	 * @param atxvalue
	 * @return
	 */
	private BufferedImage setVerticalDoor(BufferedImage aImage, int atxvalue){
		int maxy = aImage.getHeight();
		//set the dooropening.. 
		for(int i = (sizeofdoorinpixels *-1); i < sizeofdoorinpixels; i++){
			for(int j = atxvalue; j < atxvalue + widthofwallsinpixels; j++){
				aImage.setRGB(j,maxy/2 +i,backGroundColor);
			}	
		}
		return aImage;
	}
	
	private BufferedImage setPlayerSpot(BufferedImage aImage){
		int middlex = aImage.getWidth() / 2;
		int middley = aImage.getHeight() / 2;
		for(int i = -2;i<3;i++){
			for(int j=-2;j<3;j++){
				aImage.setRGB(middlex+i,middley+j,this.playerColor);
			}
		}
		/*
		aImage.setRGB(middlex,middley,this.playerColor);
		aImage.setRGB(middlex+1,middley,this.playerColor);
		aImage.setRGB(middlex-1,middley,this.playerColor);
		aImage.setRGB(middlex,middley + 1,this.playerColor);
		aImage.setRGB(middlex,middley - 1,this.playerColor);
		*/
		return aImage;
	}
}
