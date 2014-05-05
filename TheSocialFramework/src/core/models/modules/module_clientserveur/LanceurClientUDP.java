package core.models.modules.module_clientserveur;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.controleur.SuperControleur;
import core.models.core_modele.Client;
import core.models.core_modele.ClientUDP;

public class LanceurClientUDP {

	public static ClientUDP run(String nameServer, int numPort) throws IOException {
		if(validate(nameServer) && numPort >0 && numPort <= 65535){
			System.out.println("PORT");
			ClientUDP client = new ClientUDP(nameServer, numPort, new SuperControleur());
			return client;
		}
		else{
			System.out.println("BAD IP / BAD port");
			return null;
			//TODO : throw nos propres exceptions
		}
	}
	
	private static final String PATTERN = 
	        "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	private static boolean validate(final String ip){          
	      Pattern pattern = Pattern.compile(PATTERN);
	      Matcher matcher = pattern.matcher(ip);
	      return matcher.matches();             
	}

}