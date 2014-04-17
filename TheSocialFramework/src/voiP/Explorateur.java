package voiP;

import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
import javax.sound.sampled.TargetDataLine;

public class Explorateur {
	/*
	 * http://stackoverflow.com/questions/18641040/delay-with-voice-streams-on-java
	 * http://www.artima.com/forums/flat.jsp?forum=1&thread=1031
	 * http://www.developpez
	 * .net/forums/d149268/java/general-java/apis/multimedia
	 * /enregistrer-l-entree-micro/
	 * http://stackoverflow.com/questions/3705581/java
	 * -sound-api-capturing-microphone
	 * http://forum.allaboutcircuits.com/archive/index.php/t-28560.html
	 * http://systembash.com/content/a-simple-java-udp-server-and-udp-client/
	 */

	private static final String IP_TO_STREAM_TO = "localhost";
	private static final int PORT_TO_STREAM_TO = 8888;

	public static void main(String[] args) throws LineUnavailableException {
		TargetDataLine line;
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, 
				getAudioFormat()); // format is an AudioFormat object
		if (!AudioSystem.isLineSupported(info)) {
		    System.out.println("NOT SUPPORTED"); 

		}
		// Obtain and open the line.
		 System.out.println("SUPPORTED"); 
		try {
		    line = (TargetDataLine) AudioSystem.getLine(info);
		    line.open(getAudioFormat());
		} catch (LineUnavailableException ex) {
		    // Handle the error ... 
			System.out.println("NOT HANDLE YET");
		}
		
		/*
		 * if (AudioSystem.isLineSupported(Port.Info.MICROPHONE)) {
		 * try {
		 * DataLine.Info dataLineInfo = new DataLine.Info( TargetDataLine.class,
		 * getAudioFormat()); TargetDataLine targetDataLine = (TargetDataLine)
		 * AudioSystem .getLine(dataLineInfo);
		 * targetDataLine.open(getAudioFormat()); targetDataLine.start(); byte
		 * tempBuffer[] = new byte[1000]; int cnt = 0; while (true) {
		 * targetDataLine.read(tempBuffer, 0, tempBuffer.length);
		 * System.out.println("test : " + tempBuffer); }
		 * 
		 * } catch (Exception e) { System.out.println(" not correct ");
		 * System.exit(0); } }
		 */
	}
	
	public TargetDataLine getOsMicro() throws LineUnavailableException {
		// get the default micro set by your OS.
		AudioFormat format = new AudioFormat(8000.0f, 16, 1, true, true);
		TargetDataLine microphone = AudioSystem.getTargetDataLine(format);
		// OR (big Endian to false
		// TargetDataLine microphone2 =
		// AudioSystem.getTargetDataLine(getAudioFormat());
		return microphone;
	}

	public void enumMicro() {
		/* Affichage des périphériques */
		Mixer.Info minfo[] = AudioSystem.getMixerInfo();
		for (int i = 0; i < minfo.length; i++) {
			System.out.println(minfo[i]);
		}
	}

	public void enumInputDevice() throws LineUnavailableException {
		/*
		 * select a particular input device (TargetDataLine) enumerate the
		 * mixers and filter the name
		 */
		Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
		for (Mixer.Info info : mixerInfos) {
			Mixer m = AudioSystem.getMixer(info);
			Line.Info[] lineInfos = m.getSourceLineInfo();
			for (Line.Info lineInfo : lineInfos) {
				System.out.println(info.getName() + "---" + lineInfo);
				Line line = m.getLine(lineInfo);
				System.out.println("\t-----" + line);
			}
			lineInfos = m.getTargetLineInfo();
			for (Line.Info lineInfo : lineInfos) {
				System.out.println(m + "---" + lineInfo);
				Line line = m.getLine(lineInfo);
				System.out.println("\t-----" + line);

			}
		}
	}

	public static AudioFormat getAudioFormat() {
		float sampleRate = 8000.0F;
		// 8000,11025,16000,22050,44100
		int sampleSizeInBits = 16;
		// 8,16
		int channels = 1;
		// 1,2
		boolean signed = true;
		// true,false
		boolean bigEndian = false;
		// true,false
		return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed,
				bigEndian);
	}

	public static void sendThruUDP(byte soundpacket[]) {
		try {
			DatagramSocket sock = new DatagramSocket();
			sock.send(new DatagramPacket(soundpacket, soundpacket.length,
					InetAddress.getByName(IP_TO_STREAM_TO), PORT_TO_STREAM_TO));
			sock.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" Unable to send soundpacket using UDP ");
		}

	}

	public void listerRepertoireCourant() {
		String repertoireCourant = System.getProperty("user.dir");
		File repertoire = new File(repertoireCourant);
		File[] listefichiers;
		listefichiers = repertoire.listFiles();
		for (int i = 0; i < listefichiers.length; i++) {
			if (listefichiers[i].isDirectory()) {
				System.out.println("Dossier : " + listefichiers[i].getName());
			} else if (listefichiers[i].isFile()) {
				System.out.println("Fichier : " + listefichiers[i].getName());
			}
		}
	}
}
