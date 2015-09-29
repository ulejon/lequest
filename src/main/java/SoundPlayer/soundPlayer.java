package SoundPlayer;
import java.io.File;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import java.io.*;
/**
 * This class takes care of playing sound in the game
 *
 */
public class soundPlayer {
	/**
	 * Plays the file pauses the thread while playing it..
	 * Plays it by best effort..
	 * @param filename
	 */
	public static void playsound(String filename){
		long numMillisecondsToSleep = audiofilelength(filename) ;
		try{
			InputStream in = new FileInputStream(filename);
			AudioStream as = new AudioStream(in);         
			AudioPlayer.player.start(as);					
			Thread.sleep(numMillisecondsToSleep);	
			AudioPlayer.player.stop(as);
		}catch(Exception e){}
	}
	/**
	 * Plays the file in a new thread..
	 * Plays it by best effort..
	 * @param filename
	 */
	public static void playsoundWithoutSuspendingThread(String filename){
		try{
			InputStream in = new FileInputStream(filename);
			AudioStream as = new AudioStream(in);         
			AudioPlayer.player.start(as);					
		}catch(Exception e){}
	}
	/*
	 * Returns the length of the file in milisecs..
	 * @return
	 */
	private static long audiofilelength(String filename){
		long duration = 0;
		File file = new File(filename);
		try{
			AudioFileFormat audioFileFormat = AudioSystem.getAudioFileFormat(file);
			duration = (long)((1000 * audioFileFormat.getByteLength())/ (audioFileFormat.getFormat().getFrameSize() *audioFileFormat.getFormat().getFrameRate()) );
		}catch(Exception e){}
		return duration;

	}
}