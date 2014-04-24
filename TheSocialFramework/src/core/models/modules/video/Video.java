package core.models.modules.video;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.FFmpegFrameRecorder;
import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.FrameRecorder;
import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core;

public class Video {

	public static void capturerVideo(){

		     try {  
		       OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);  
		       grabber.start();  
		       opencv_core.IplImage grabbedImage = grabber.grab();  
		       CanvasFrame canvasFrame = new CanvasFrame("Recording");  
		       canvasFrame.setCanvasSize(grabbedImage.width(), grabbedImage.height());  
		       grabber.setFrameRate(grabber.getFrameRate());  
		       
		       FFmpegFrameRecorder recorder = new FFmpegFrameRecorder("mytestvideo.mp4", grabber.getImageWidth(), grabber.getImageHeight()); 
		      
		       recorder.setFormat("mp4");  
		       recorder.setFrameRate(20);  
		       recorder.setBitrate(20 * 1280 * 1024);  

		       recorder.start();  

		       while (canvasFrame.isVisible() && (grabbedImage = grabber.grab()) != null) {  
		         canvasFrame.showImage(grabbedImage);  
		         recorder.record(grabbedImage);  
		       }  
		       recorder.stop();  
		       grabber.stop();  
		       canvasFrame.dispose();  

		     } catch (FrameGrabber.Exception ex) {  
		       Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);  
		     } catch (FrameRecorder.Exception ex) {  
		       Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);  
		     }  
		   }  

public static ByteArrayOutputStream convertirVidVersBits(String fich) throws FileNotFoundException{
		
		File file = new File(fich);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
	        byte[] buf = new byte[11000];
	        try {
	            for (int readNum; (readNum = fis.read(buf)) != -1;) {
	                bos.write(buf, 0, readNum); 
	                System.out.println("read " + readNum + " bytes,");
	            }
	        } catch (IOException ex) {
	                
	        }
	return bos;
	}
 
 
	
public static File convertirBitsVersVid(ByteArrayOutputStream bos) throws FileNotFoundException{
	 byte[] bytes = bos.toByteArray();
	 File fich = new File("video.avi");
     FileOutputStream fos = new FileOutputStream(fich);
     
     try {
		fos.write(bytes);
     fos.flush();
     fos.close();
     } catch (IOException e) {
 		// TODO Auto-generated catch block
 		e.printStackTrace();
 	}
     return fich;
	}

}