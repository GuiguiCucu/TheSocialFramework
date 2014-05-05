package core.models.modules.module_video;

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

	/** 
	 * Capture une video et l'enregistre dans le dossier courant sous le nom video1.mp4
	 */
	public static void capturerVideo(){

		     try {  
		       OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);  
		       grabber.start();  
		       opencv_core.IplImage grabbedImage = grabber.grab();  
		       CanvasFrame canvasFrame = new CanvasFrame("Recording");  
		       canvasFrame.setCanvasSize(grabbedImage.width(), grabbedImage.height());  
		       grabber.setFrameRate(grabber.getFrameRate());  
		       
		       FFmpegFrameRecorder recorder = new FFmpegFrameRecorder("video1.mp4", grabber.getImageWidth(), grabber.getImageHeight()); 
		      
		       recorder.setFormat("mp4");  
		       recorder.setFrameRate(30);  
		       recorder.setVideoBitrate(30 * 640 * 480);  
       
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

/**
 * Convertit une video en un tableau de bits
 * @param fich
 * @return un tableau de bit correspondant à la video
 * @throws FileNotFoundException
 */
public static ByteArrayOutputStream convertirVidVersBits(String fich) throws FileNotFoundException{
		
		File file = new File(fich);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
	        byte[] buf = new byte[10000];
	        try {
	            for (int readNum; (readNum = fis.read(buf)) != -1;) {
	                bos.write(buf, 0, readNum); 
	                System.out.println("read " + readNum + " bytes,");
	            }
	        } catch (IOException ex) {
	                
	        }
	return bos;
	}
 
 
/**
 * Convertit un tableau de bit en un fichier
 * @param bos
 * @return un fichier video
 * @throws FileNotFoundException
 */
public static File convertirBitsVersVid(ByteArrayOutputStream bos) throws FileNotFoundException{
	 byte[] bytes = bos.toByteArray();
	 File fich = new File("video2.mp4");
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