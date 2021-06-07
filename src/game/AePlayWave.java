package game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;

public class AePlayWave extends Thread {
	 List<String> musicList=new ArrayList<String>();
	 private String filename;
	 public boolean loop=false;
	 public AePlayWave(String wavfile,boolean l) {
	 filename = wavfile;
	  loop=l;
	 }
	 public AePlayWave(String wavfile,int v) {
	 filename = wavfile;
	 volume=v;
	 }
	 public AePlayWave(String wavfile) {
	 filename = wavfile;
	 }
	 String location=System.getProperty("user.dir")+"\\src\\game\\mm\\";
	 public AePlayWave(int place,boolean l) {
		 this.place=place;
		 loop=l;
		 if(place==0) {
			 musicList.add(location+"music.wav");
			 musicList.add(location+"home.wav");
			 musicList.add(location+"home4.wav");
		 }else if(place==1) {
			 musicList.add(location+"color3.wav");
			 musicList.add(location+"color4.wav");
		 }else if(place==2) {
			 musicList.add(location+"game.wav");
		 }
	 }
	 int place=100;
	 public SourceDataLine auline;
	 private int volume=50;
	 public void setVolume(int v) {
		 volume=v;
	 }
	 public void Stop() {
		 loop=false;
		 auline.stop();
	 }
	 int ls_num=0;
	 boolean start=false;
	 public void run() {
	 do {
		 if(place==0) {
		filename=musicList.get(ls_num%3);
		 ls_num++;
		 }
		 else if(place==1) {
			 filename=musicList.get(ls_num%2);
			 ls_num++;
		 }
		 else if(place==2) {
			 filename=musicList.get(ls_num%1);
			 ls_num++;
		 }
		 File soundFile = new File(filename);
		 AudioInputStream audioInputStream = null;
		 try {
		 audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		 } catch (Exception e1) {
		 return;
		 }
		  
		 AudioFormat format = audioInputStream.getFormat();
		 auline = null;
		 DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
		  
		 try {
		 auline = (SourceDataLine) AudioSystem.getLine(info);
		 auline.open(format);
		 } catch (Exception e) {
		 e.printStackTrace();
		 return;
		 }
		 try {
			    FloatControl gainControl=(FloatControl)auline.getControl(FloatControl.Type.MASTER_GAIN);
			    BooleanControl muteControl=(BooleanControl)auline.getControl(BooleanControl.Type.MUTE);
			    if (volume == 0) {
			      muteControl.setValue(true);
			    }
			 else {
			      muteControl.setValue(false);
			      gainControl.setValue((float)(Math.log(volume / 100d) / Math.log(10.0) * 20.0));
			    }
			  }
			 catch (  Exception e) {
				 System.out.println("Exception music");
			  }
		 auline.start();
		 int nBytesRead = 0;
		 byte[] abData = new byte[512];
		  
		 try {
		 while (nBytesRead != -1) {
		 nBytesRead = audioInputStream.read(abData, 0, abData.length);
		 if (nBytesRead >= 0)
		  auline.write(abData, 0, nBytesRead);
		 }
		 } catch (IOException e) {
		 e.printStackTrace();
		 return;
		 } finally {
		 auline.drain();
		 auline.close();
		 start=false;
		 }
		 
	 }while(loop);
	
	 }
	}
