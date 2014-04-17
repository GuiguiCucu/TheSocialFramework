package voiP;

/*	AudioSystemExplorer.java
*	@author: Charles Bell
*	@version: May 12, 2001
*/

import javax.sound.sampled.*;

public class AudioSystemExplorer{

	public static void main(String[] args){

		AudioSystemExplorer audiosystemexplorer = new AudioSystemExplorer();
		audiosystemexplorer.explore();
		System.exit(0);
	}


	public void explore(){
		displayMixerInfo();
		displayAudioSystemInfo();
	}
	
	public void displayAudioSystemInfo(){
		try{
			Line compactdiskline = null;
			Line headphoneline = null;
			Line lineinline = null;
			Line lineoutline = null;
			Line speakerline = null;
			Line microphoneline = null;
			System.out.println("This System supports:");
			if (AudioSystem.isLineSupported(Port.Info.COMPACT_DISC)) {
				System.out.println("COMPACT_DISC");
        			compactdiskline = (Port) AudioSystem.getLine(
            			Port.Info.COMPACT_DISC);
    			}
			if (AudioSystem.isLineSupported(Port.Info.HEADPHONE)) {
				System.out.println("HEADPHONE");
        			headphoneline = (Port) AudioSystem.getLine(
            			Port.Info.HEADPHONE);
    			}
			if (AudioSystem.isLineSupported(Port.Info.LINE_IN)) {
				System.out.println("LINE_IN");
        			lineinline = (Port) AudioSystem.getLine(
            			Port.Info.LINE_IN);
    			}
			if (AudioSystem.isLineSupported(Port.Info.LINE_OUT)) {
				System.out.println("LINE_OUT");
        			lineoutline = (Port) AudioSystem.getLine(
            			Port.Info.LINE_OUT);
    			}
			if (AudioSystem.isLineSupported(Port.Info.MICROPHONE)) {
				System.out.println("MICROPHONE");
        			microphoneline = (Port) AudioSystem.getLine(
            			Port.Info.MICROPHONE);
    			}
			if (AudioSystem.isLineSupported(Port.Info.SPEAKER)) {
				System.out.println("SPEAKER");
        			speakerline = (Port) AudioSystem.getLine(
            			Port.Info.SPEAKER);
    			}


		}catch(LineUnavailableException lue){
			System.out.println("LineUnavailableException: " + lue.getMessage());
		}
	}

	public void displayMixerInfo(){
		Mixer.Info[] mixerinfo = AudioSystem.getMixerInfo();
		System.out.println("This System has the following Mixers which can be used as Sound Resources:");
		for (int i=0;i<mixerinfo.length;i++){
			String description = mixerinfo[i].getDescription();
			String name = mixerinfo[i].getName();
			String vendor = mixerinfo[i].getVendor();
			String version = mixerinfo[i].getVersion();
			System.out.println("Name: " + name);
			System.out.println("Vendor: " + vendor);
			System.out.println("Version: " + version);
			System.out.println("Description: " + description);
			Mixer mixer = AudioSystem.getMixer(mixerinfo[i]);
			Line.Info[] sourcelineinfo = mixer.getSourceLineInfo();
			System.out.println("This Mixer has the following SourceLines:");
			for (int s = 0;s<sourcelineinfo.length;s++){
				System.out.println(sourcelineinfo[s].toString());
				Class sourcelineclass = sourcelineinfo[s].getLineClass();
				System.out.println("Source Line Class name: " + sourcelineclass.getName());
				try{
					Line line = AudioSystem.getLine(sourcelineinfo[s]);
					if (line.isOpen()){
						System.out.println("This line is open.");
					}else{
						System.out.println("This line is closed.");
					}
					if (sourcelineclass.getName().indexOf("Clip") >= 0){
						Clip clip = (Clip)line;
						System.out.println("FrameLength: " + String.valueOf(clip.getFrameLength()));
						System.out.println("MicrosecondLength: " + String.valueOf(clip.getMicrosecondLength()));
					}else if (sourcelineclass.getName().indexOf("SourceDataLine") >= 0){
						if (((DataLine)line).isActive()) {
							System.out.println("This line is active.");
						}else{
							System.out.println("This line is inactive.");
						}
					}else if (sourcelineclass.getName().indexOf("TargetDataLine") >= 0){
						if (((DataLine)line).isActive()) {
							System.out.println("This line is active.");
						}else{
							System.out.println("This line is inactive.");
						}

					}else if (sourcelineclass.getName().indexOf("DataLine") >= 0){
						if (((DataLine)line).isActive()) {
							System.out.println("This line is active.");
						}else{
							System.out.println("This line is inactive.");
						}

					} 
				}catch(LineUnavailableException lue){
					System.out.println("LineUnavailableException: " + lue.getMessage());
				}
			}
			Line.Info[] targetlineinfo = mixer.getTargetLineInfo();
			System.out.println("This System has the following TargetLines:");
			for (int t = 0;t<targetlineinfo.length;t++){
				System.out.println(targetlineinfo[t].toString());
				Class targetlineclass = targetlineinfo[t].getLineClass();
				System.out.println("Target Line Class name: " + targetlineclass.getName());
				try{
					Line line = AudioSystem.getLine(targetlineinfo[t]);	
					if (line.isOpen()){
						System.out.println("This line is open.");
					}else{
						System.out.println("This line is closed.");
					}
					if (targetlineclass.getName().indexOf("Clip") >= 0){
						Clip clip = (Clip)line;
						System.out.println("FrameLength: " + String.valueOf(clip.getFrameLength()));
						System.out.println("MicrosecondLength: " + String.valueOf(clip.getMicrosecondLength()));
					}else if (targetlineclass.getName().indexOf("SourceDataLine") >= 0){
						if (((DataLine)line).isActive()) {
							System.out.println("This line is active.");
						}else{
							System.out.println("This line is inactive.");
						}
					}else if (targetlineclass.getName().indexOf("TargetDataLine") >= 0){
						if (((DataLine)line).isActive()) {
							System.out.println("This line is active.");
						}else{
							System.out.println("This line is inactive.");
						}

					}else if (targetlineclass.getName().indexOf("DataLine") >= 0){
						if (((DataLine)line).isActive()) {
							System.out.println("This line is active.");
						}else{
							System.out.println("This line is inactive.");
						}

					} 

				}catch(LineUnavailableException lue){
					System.out.println("LineUnavailableException: " + lue.getMessage());
				}
			}

			System.out.println("-----------------------------");			
		}	
	}
}