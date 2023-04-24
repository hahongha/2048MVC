package model;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	private HashMap< String , Clip> sound;
//	private static Clip[] clip;
	public Sound() {
		sound = new HashMap<String, Clip>();
		openClip("ting.wav");
		openClip("piano.wav");
		openClip("victory.wav");
		openClip("lose.wav");
	}
	private void openClip(String name){
		String path = System.getProperty("user.dir") + File.separator + "bin" + File.separator+"lib"+ File.separator+ name;
		AudioInputStream inputStream = null;
			try {
				inputStream = AudioSystem.getAudioInputStream(new File(path));
				Clip clip = AudioSystem.getClip();
				clip.open(inputStream);
				sound.put(name, clip);
				inputStream.close();
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void play(String name, int loop) {
		if(sound.get(name).isRunning()) {
			sound.get(name).stop();
		}
		sound.get(name).start();
		sound.get(name).setFramePosition(0);//bat dau chay tu dau clip
		sound.get(name).loop(loop);
		System.out.println(name);
	}
	protected void stop(String name) {
		if(sound.get(name).isRunning()) {
			sound.get(name).stop();
			System.out.println("stop");
		}
	}
	protected void start(String name) {
		if(!sound.get(name).isRunning()) {
		sound.get(name).start();
		System.out.println(sound.get(name).toString());
		}
	}
	protected void change(String name) {
		if(sound.get(name).isRunning()) {
			stop(name);
		}else start(name);
	}
	
	protected void changeVolume(String name, float value) {
		FloatControl fc = (FloatControl) sound.get(name).getControl(FloatControl.Type.MASTER_GAIN);
		fc.setValue(value);
	}
	protected double getVolume(String name) {
		FloatControl fc = (FloatControl) sound.get(name).getControl(FloatControl.Type.MASTER_GAIN);
		return fc.getValue();
	}
}
