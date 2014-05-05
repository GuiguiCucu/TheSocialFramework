package core.models.modules.module_voip.test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class ServeurVoipUDP extends Thread {
	private static final String IP_TO_STREAM_TO = "152.77.116.163";
	private static final int PORT_TO_STREAM_TO = 8888;

	public static void main(String[] args) {
		ServeurVoipUDP r = new ServeurVoipUDP();
		r.start();
	}

	public void run() {
		byte b[] = null;
		System.out.println("Serveur running...");
		while (true) {
			receiveThruUDP();
			
		}
	}

	public static void receiveThruUDP() {
		try {
			DatagramSocket sock = new DatagramSocket(PORT_TO_STREAM_TO);
			byte soundpacket[] = new byte[8192];
			DatagramPacket datagram = new DatagramPacket(soundpacket,
					soundpacket.length, InetAddress.getByName(IP_TO_STREAM_TO),
					PORT_TO_STREAM_TO);
			sock.receive(datagram);
			sock.close();
			toSpeaker(datagram.getData());
		} catch (Exception e) {
			System.out.println(" Unable to receive soundpacket using UDP ");
		}
	}

	public static void toSpeaker(byte soundbytes[]) {
		try {
			
			DataLine.Info dataLineInfo = new DataLine.Info(
					SourceDataLine.class, getAudioFormat());
			SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem
					.getLine(dataLineInfo);
			sourceDataLine.open(getAudioFormat());
			sourceDataLine.start();
			int cnt = 0;
			sourceDataLine.write(soundbytes, 0, soundbytes.length);
			sourceDataLine.drain();
			sourceDataLine.close();
		} catch (Exception e) {
			System.out.println("not working in speakers ");
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
}
