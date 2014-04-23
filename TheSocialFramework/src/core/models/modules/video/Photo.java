package core.models.modules.video;

import com.googlecode.javacv.*;
import com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;


public class Photo {

	/**
	 * @param args
	 */
	public static void prendrePhoto() {
		OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
		try {
			grabber.start();
			IplImage img = grabber.grab();
			if (img != null) {
				cvSaveImage("image.jpg", img);

			}

		} catch (Exception e) {

		}

	}
}
