package core.models.modules.module_video;

import java.io.FileNotFoundException;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Video.capturerVideo("./booya.mp4");
		try {
			
			Video.convertirBitsVersVid(Video.convertirVidVersBits("./booya.mp4"), "booya2.mp4");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
