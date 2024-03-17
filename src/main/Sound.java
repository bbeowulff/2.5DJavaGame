package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sound() {
		soundURL[0] = getClass().getResource("/sounds/soundtrack_1.wav");
		soundURL[1] = getClass().getResource("/sounds/chest_open.wav");
		soundURL[2] = getClass().getResource("/sounds/door_open.wav");
		soundURL[3] = getClass().getResource("/sounds/water-drinking.wav");
		soundURL[4] = getClass().getResource("/sounds/sticky-keys-sound.wav");
		soundURL[5] = getClass().getResource("/sounds/WINNER.wav");
	}
	
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		}catch(Exception e) {
			
		}
	}
	public void play() {
		clip.start();
	}
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop() {
		clip.stop();
	}
	
}
