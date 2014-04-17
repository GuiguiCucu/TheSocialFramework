package dropbox.tests;

import java.io.IOException;
import java.util.Scanner;

import com.dropbox.core.DbxException;

import dropbox.Dropbox;

public class TestDropbox {

	public static void main(String[] args) throws IOException, DbxException {
		String APP_KEY = "tajxrbh3ovhxxxp";
	    String APP_SECRET = "password";
	    
	    Dropbox dropbox = new Dropbox();
	    
	    //String url = dropbox.identification(APP_KEY, APP_SECRET);
	    //03h9bWNCFbUAAAAAAAAABTXqbECJDNH3Oi_BZP3PV14
	    
	    //System.out.println(url);
	    
//	    Scanner sc = new Scanner(System.in);
//	    System.out.println("Veuillez saisir un mot :");
//	    String code = sc.nextLine();

	    
	    //dropbox.acces(code);
	    
	   // dropbox.upload("Z:/POO/ProjetFramework/test.txt", "/test.txt");
	}

}
