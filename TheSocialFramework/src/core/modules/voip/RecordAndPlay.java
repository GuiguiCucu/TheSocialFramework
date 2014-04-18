package core.modules.voip;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.sound.sampled.*;

public class RecordAndPlay implements ActionListener, Runnable {

	Thread thread;
	AudioInputStream ais;
	boolean recordstatus = false;
	boolean playstatus = false;
	float samplerate =  11025.0f;
	float framerate = 11025.0f;
    	int buffersize = 16384;

	public static void main(String[] args){

		RecordAndPlay recordandplay = new RecordAndPlay();
		recordandplay.init();
	}

	public void init(){
        	JFrame frame = new JFrame("RecordAndPlay");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton recordbutton = new JButton("Record");
		JButton stopbutton = new JButton("Stop");
		JButton playbutton = new JButton("Play");
		JButton savebutton = new JButton("Save");
		JButton exitbutton = new JButton("Exit");
		recordbutton.addActionListener(this);
		stopbutton.addActionListener(this);
		playbutton.addActionListener(this);
		savebutton.addActionListener(this);
		exitbutton.addActionListener(this);
		JPanel panel = new JPanel();
		panel.add(recordbutton);
		panel.add(stopbutton);
		panel.add(playbutton);
		panel.add(savebutton);
		panel.add(exitbutton);
		frame.getContentPane().add(panel,"Center");
        	frame.show();
        	frame.pack();

	}

	public void actionPerformed(ActionEvent actionevent){
		String command = actionevent.getActionCommand();
		System.out.println(command);
		if (command.compareTo("Record")==0){
			recordstatus = true;
			playstatus = false;
			start();
		} else if (command.compareTo("Stop")==0){
			stop();
		} else if (command.compareTo("Play")==0){
			recordstatus = false;
			playstatus = true;
			start();
		}else if (command.compareTo("Save")==0){
			saveToFile();
		}else if (command.compareTo("Exit")==0){
			if (thread != null) stop();
			System.exit(0);
		}

	}

	public void start() {
            thread = new Thread(this);
            thread.start();
	}

	public void stop() {
		recordstatus = false;
		playstatus = false;
		thread = null;
	}        

	public void run(){
		if ((recordstatus)&&(!playstatus)) record();
		if ((playstatus)&& (!recordstatus)) playback();
	}

	public void playback(){
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
            AudioFormat audioformat = new AudioFormat(AudioFormat.Encoding.PCM_UNSIGNED, 
								samplerate, 8, 1, 1, framerate,false);
            AudioInputStream playbackstream = AudioSystem.getAudioInputStream(audioformat, ais);
                        
            if (playbackstream == null) {
                System.out.println("Unable to convert stream of audioformat " + ais + " to audioformat " + audioformat);
                return;
            }


            // define the required attributes for our line, 
            // and make sure a compatible line is supported.

            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioformat);
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Line matching " + info + " not supported.");
                return;
            }
            // get and open the source data line for playback.
            try{
                	line = (SourceDataLine) AudioSystem.getLine(info);
                	line.open(audioformat, buffersize);
            }catch (LineUnavailableException lue) { 
                	System.out.println("Unable to open the line to the microphone input.");
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

	public void record() {
		TargetDataLine line = null;            
            // define the required attributes for our line, 
            // and make sure a compatible line is supported.
            AudioFormat audioformat = new AudioFormat(AudioFormat.Encoding.PCM_UNSIGNED, 
								samplerate, 8, 1, 1, framerate,false);
            DataLine.Info info = new DataLine.Info(TargetDataLine.class,audioformat);                        
            if (!AudioSystem.isLineSupported(info)) {
                	System.out.println("Line matching " + info + " not supported.");
                	System.exit(0);
            }
            // get and open the target data line for capture.		
            try{
                	line = (TargetDataLine) AudioSystem.getLine(info);
                	line.open(audioformat, line.getBufferSize());
            }catch (LineUnavailableException lune) { 
                	System.err.println("Unable to open the line: " + lune.getMessage());
                	System.exit(0);
            }catch (SecurityException se) { 
                	System.err.println("SecurityException: " + se.toString());
                	System.exit(0);
            }catch(Exception ex) { 
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
                if((bytenumber = line.read(data, 0, bufferLengthInBytes)) == -1) {
                    break;
                }
                baos.write(data, 0, bytenumber);
            }
		System.out.println("Stopped Recording");
		//stop and close the line
            line.stop();
            line.close();
            line = null;
            // stop and close the output stream
            try{
                	baos.flush();
                	baos.close();
            }catch (IOException ioe) {
                	System.err.println(ioe.getMessage());
            }
            // load bytes into the audio input stream for playback
            byte audioBytes[] = baos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(audioBytes);
            ais = new AudioInputStream(bais, audioformat, audioBytes.length / frameSizeInBytes);
            try{
                	ais.reset();
            }catch (Exception e) { 
                	System.err.println("Exception: " + e.getMessage());
                	return;
            }
	}

    	public void saveToFile() {

		AudioFileFormat.Type audiofileformattype = AudioFileFormat.Type.WAVE;

        	if (ais == null) {
			System.out.println("No recorded audio to save");
			return;
        	}

        	// reset to the beginnning of the recorded data
        	try{
			ais.reset();
        	}catch (Exception e) { 
			System.err.println("Unable to reset stream " + e);
			return;
        	}

        	File file = getFileToSave();
        	try {
			if (AudioSystem.write(ais, audiofileformattype, file) == -1) {
                		throw new IOException("Problems writing to file");
			}
        	}catch (IOException ioe) { 
			System.err.println("IOException: " + ioe.getMessage());
        	}catch (Exception e) { 
			System.err.println("Exception: " + e.getMessage());
		}
    	}
	/**	Uses the showSaveDialog method of JFileChooser to request the user to
	*	select the file to save to
	*/
	public File getFileToSave(){
		File file = null;
		JFrame jframe = new JFrame();
		JFileChooser chooser = new JFileChooser(".");
		int returnvalue = chooser.showSaveDialog(jframe);
	 	if(returnvalue == JFileChooser.APPROVE_OPTION) { 
			file = chooser.getSelectedFile();
		} 
		return file;
	}

}