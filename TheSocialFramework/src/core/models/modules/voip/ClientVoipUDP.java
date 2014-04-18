package core.models.modules.voip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
import javax.sound.sampled.TargetDataLine;

public class ClientVoipUDP implements Runnable{

	private static final String IP_TO_STREAM_TO = "localhost";
	private static final int PORT_TO_STREAM_TO = 8888;



	public ClientVoipUDP() {

	}

	public static void main(String[] args) {
		Mixer.Info minfo[] = AudioSystem.getMixerInfo();
		for (int i = 0; i < minfo.length; i++) {
			System.out.println(minfo[i]);
		}
		System.out.println("TEST");
		if (AudioSystem.isLineSupported(Port.Info.MICROPHONE)) {
			System.out.println("TEST IN");
			try {
				DataLine.Info dataLineInfo = new DataLine.Info(
						TargetDataLine.class, getAudioFormat());
				final TargetDataLine targetDataLine = (TargetDataLine) AudioSystem
						.getLine(dataLineInfo);
				targetDataLine.open(getAudioFormat());
				targetDataLine.start();
				byte tempBuffer[] = new byte[targetDataLine.getBufferSize() / 5];
				int cnt = 0;
				while (true) {
					targetDataLine.read(tempBuffer, 0, tempBuffer.length);
					sendThruUDP(tempBuffer);
				}
			} catch (Exception e) {
				System.out.println(" not correct ");
				System.exit(0);
			}
		}
		System.out.println("TEST OUT");
		
		//record();
	}

	public static void record() {
		float samplerate = 11025.0f;
		float framerate = 11025.0f;
		int buffersize = 16384;
		TargetDataLine line = null;
		AudioFormat audioformat = new AudioFormat(
				AudioFormat.Encoding.PCM_UNSIGNED, samplerate, 8, 1, 1,
				framerate, false);
		DataLine.Info info = new DataLine.Info(TargetDataLine.class,
				audioformat);
		if (!AudioSystem.isLineSupported(info)) {
			System.out.println("Line matching " + info + " not supported.");
			System.exit(0);
		}
		try {
			line = (TargetDataLine) AudioSystem.getLine(info);
			line.open(audioformat, line.getBufferSize());
		} catch (LineUnavailableException lune) {
			System.err.println("Unable to open the line: " + lune.getMessage());
			System.exit(0);
		} catch (SecurityException se) {
			System.err.println("SecurityException: " + se.toString());
			System.exit(0);
		} catch (Exception ex) {
			System.err.println("Exception: " + ex.getMessage());
			System.exit(0);
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int frameSizeInBytes = audioformat.getFrameSize();
		int bufferLengthInFrames = line.getBufferSize() / 8;
		int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
		byte[] data = new byte[bufferLengthInBytes];
		int bytenumber = 0;
		line.start();
		System.out.println("Started Recording");
		while (true) {
			if ((bytenumber = line.read(data, 0, bufferLengthInBytes)) == -1) {
				break;
			}
			baos.write(data, 0, bytenumber);
			sendThruUDP(data);
		}
	}

	public static AudioFormat getAudioFormat() {
		float sampleRate = 8000.0F;
		int sampleSizeInBits = 16;
		int channels = 1;
		boolean signed = true;
		boolean bigEndian = false;
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

	@Override
	public void run() {
		record();
		
	}
}
