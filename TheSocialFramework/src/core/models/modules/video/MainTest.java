package core.models.modules.video;

import java.io.FileNotFoundException;

public class MainTest {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		//Photo.prendrePhoto();
		Video.capturerVideo();
		Video.convertirBitsVersVid(Video.convertirVidVersBits("video1.mp4"));
		
	}

}
