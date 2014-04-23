package core.models.modules.module_voip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class CaptureAudio implements Runnable {

	/**
	 * Le processus de capture
	 */
	Thread thread;
	/**
	 * Le flux audio du périphérique séléctionné
	 */
	AudioInputStream ais;
	/**
	 * Booléens pour l'utilisation du thread de capture
	 */
	boolean recordstatus;
	boolean playstatus;
	/**
	 * Valeurs pour l'obtention d'un format audio standard
	 */
	float samplerate;
	float framerate;
	int buffersize;

	/**
	 * Constructeur
	 */
	public CaptureAudio() {
		boolean recordstatus = false;
		boolean playstatus = false;
		float samplerate = 11025.0f;
		float framerate = 11025.0f;
		int buffersize = 16384;
	}

	/**
	 * Enregistrement audio
	 */
	public void startRecord() {
		recordstatus = true;
		playstatus = false;
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * Ecoute de l'enregistrement audio
	 */
	public void startPlay() {
		recordstatus = false;
		playstatus = true;
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * Arrêt de la détection des flux
	 */
	public void stop() {
		recordstatus = false;
		playstatus = false;
		thread = null;
	}

	/**
	 * Redéfinition de la méthode run() du processus chargé de la capture
	 */
	@Override
	public void run() {
		if ((recordstatus) && (!playstatus))
			record();
		if ((playstatus) && (!recordstatus))
			playback();
	}

	/**
	 * Ecoute du flux stocké
	 */
	public void playback() {
		SourceDataLine line;
		if (ais == null) {
			System.out.println("You need to record something first");
			playstatus = false;
			return;
		}
		// reset to the beginnning of the stream
		try {
			ais.reset();
		} catch (Exception e) {
			System.out.println("Unable to reset the stream\n" + e);
			return;
		}

		// get an AudioInputStream of the desired audioformat for playback
		AudioFormat audioformat = new AudioFormat(
				AudioFormat.Encoding.PCM_UNSIGNED, samplerate, 8, 1, 1,
				framerate, false);
		AudioInputStream playbackstream = AudioSystem.getAudioInputStream(
				audioformat, ais);

		if (playbackstream == null) {
			System.out.println("Unable to convert stream of audioformat " + ais
					+ " to audioformat " + audioformat);
			return;
		}

		// define the required attributes for our line,
		// and make sure a compatible line is supported.

		DataLine.Info info = new DataLine.Info(SourceDataLine.class,
				audioformat);
		if (!AudioSystem.isLineSupported(info)) {
			System.out.println("Line matching " + info + " not supported.");
			return;
		}
		// get and open the source data line for playback.
		try {
			line = (SourceDataLine) AudioSystem.getLine(info);
			line.open(audioformat, buffersize);
		} catch (LineUnavailableException lue) {
			System.out
					.println("Unable to open the line to the microphone input.");
			System.out.println("LineUnavailableException: " + lue.getMessage());
			return;
		}
		// play back the captured audio data
		int frameSizeInBytes = audioformat.getFrameSize();
		int bufferLengthInFrames = line.getBufferSize() / 8;
		int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
		byte[] data = new byte[bufferLengthInBytes];
		int bytenumber = 0;
		// start the source data line
		line.start();
		while (thread != null) {
			try {
				if ((bytenumber = playbackstream.read(data)) == -1) {
					break;
				}
				int numBytesRemaining = bytenumber;
				while (numBytesRemaining > 0) {
					numBytesRemaining -= line.write(data, 0, numBytesRemaining);
				}
			} catch (Exception e) {
				System.out.println("Error during playback: " + e);
				System.out.println("Exception: " + e.getMessage());
				break;
			}

		}
		if (thread != null) {
			line.drain();
		}
		line.stop();
		line.close();
		line = null;
	}

	/**
	 * Detection du périphérique et enregistrement du flux
	 */
	public void record() {
		TargetDataLine line = null;
		// define the required attributes for our line,
		// and make sure a compatible line is supported.
		AudioFormat audioformat = new AudioFormat(
				AudioFormat.Encoding.PCM_UNSIGNED, samplerate, 8, 1, 1,
				framerate, false);
		DataLine.Info info = new DataLine.Info(TargetDataLine.class,
				audioformat);
		if (!AudioSystem.isLineSupported(info)) {
			System.out.println("Line matching " + info + " not supported.");
			System.exit(0);
		}
		// get and open the target data line for capture.
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
		while (thread != null) {
			if ((bytenumber = line.read(data, 0, bufferLengthInBytes)) == -1) {
				break;
			}
			baos.write(data, 0, bytenumber);
		}
		System.out.println("Stopped Recording");
		// stop and close the line
		line.stop();
		line.close();
		line = null;
		// stop and close the output stream
		try {
			baos.flush();
			baos.close();
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		// load bytes into the audio input stream for playback
		byte audioBytes[] = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(audioBytes);
		ais = new AudioInputStream(bais, audioformat, audioBytes.length
				/ frameSizeInBytes);
		try {
			ais.reset();
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			return;
		}
	}

	/**
	 * Conversion du flux et enregistrement au format WAVE
	 */
	public void saveToFile() {
		AudioFileFormat.Type audiofileformattype = AudioFileFormat.Type.WAVE;
		if (ais == null) {
			System.out.println("No recorded audio to save");
			return;
		}
		try {
			ais.reset();
		} catch (Exception e) {
			System.err.println("Unable to reset stream " + e);
			return;
		}
		File file = getFileToSave();
		try {
			if (AudioSystem.write(ais, audiofileformattype, file) == -1) {
				throw new IOException("Problems writing to file");
			}
		} catch (IOException ioe) {
			System.err.println("IOException: " + ioe.getMessage());
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
	}

	/**
	 * Demande un chemin ou enregistrer le flux audio
	 * 
	 * @return le fichier devant contenir le flux
	 */
	public File getFileToSave() {
		File file = null;
		JFrame jframe = new JFrame();
		JFileChooser chooser = new JFileChooser(".");
		int returnvalue = chooser.showSaveDialog(jframe);
		if (returnvalue == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
		}
		return file;
	}

	// CLASSE STATIQUES

	/**
	 * Obtention du périphérique d'entrée audio définie par le système
	 * d'exploitation
	 * 
	 * @return Données audio exploitables
	 * @throws LineUnavailableException
	 */
	public static TargetDataLine getOsMicro() throws LineUnavailableException {
		AudioFormat format = new AudioFormat(8000.0f, 16, 1, true, true);
		TargetDataLine microphone = AudioSystem.getTargetDataLine(format);
		return microphone;
	}

	/**
	 * Enumération des informations sur les périphériques d'entrées audio
	 * 
	 * @return une liste d'informations
	 */
	public static ArrayList<Mixer.Info> enumMixer() {
		ArrayList<Mixer.Info> infos = new ArrayList<Mixer.Info>();
		Mixer.Info minfo[] = AudioSystem.getMixerInfo();
		for (int i = 0; i < minfo.length; i++) {
			infos.add(minfo[i]);
		}
		return infos;
	}

	/**
	 * Méthode construisant un objet AudioFormat à partir de valeurs standards
	 * @return le format audio standard pour la voix
	 */
	public static AudioFormat getStandardAudioFormat() {
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
