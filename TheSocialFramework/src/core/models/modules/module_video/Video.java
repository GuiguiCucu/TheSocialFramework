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

/**
 * Classe mettant en oeuvre plusieurs fonctionnalités liées au streaming video
 * @author Gwenael
 */
public class Video {


 /**
  * Permet de capturer une video a l'aide de la webcam, puis de l'enregistrer à un endroit défini en parametre
  * @param nomFichier : chemin et nom du fichier video a créer
  */
	public static void capturerVideo(String nomFichier){

		     try {  
		       OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);  
		       grabber.start();  
		       opencv_core.IplImage grabbedImage = grabber.grab();  
		       CanvasFrame canvasFrame = new CanvasFrame("Recording");  
		       canvasFrame.setCanvasSize(grabbedImage.width(), grabbedImage.height());  
		       grabber.setFrameRate(grabber.getFrameRate());  

		       FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(nomFichier, grabber.getImageWidth(), grabber.getImageHeight()); 
		      
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
 * Convertit une video en ByteOuputString
 * @param nomFichier: le chemin du fichier
 * @return un tableau de bit correspondant à la video
 * @throws FileNotFoundException
 */
public static ByteArrayOutputStream convertirVidVersBits(String nomFichier) throws FileNotFoundException{
		
		File file = new File(nomFichier);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
	        byte[] buf = new byte[10000];
	        try {
	            for (int readNum; (readNum = fis.read(buf)) != -1;) {
	                bos.write(buf, 0, readNum); 
	                System.out.println("read " + readNum + " bytes,"); 
	            	}
		       fis.close();
	        } catch (IOException ex) {
	                
	        }
	return bos;
	}
 
 
/**
 * 
 * Convertit un tableau de bit en un fichier
 * @param bos : le ByteArrayOutputStream à convertir
 * @param nomFichier : chemin du fichier video à créer
 * @return un fichier video
 * @throws FileNotFoundException
 */
public static File convertirBitsVersVid(ByteArrayOutputStream bos, String nomFichier) throws FileNotFoundException{
	 byte[] bytes = bos.toByteArray();
	 File fich = new File(nomFichier);
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