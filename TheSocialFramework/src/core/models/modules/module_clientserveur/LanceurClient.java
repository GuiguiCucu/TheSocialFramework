package core.models.modules.module_clientserveur;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.controleur.SuperControleur;
import core.models.core_modele.Client;

public class LanceurClient {
	
	public static void run(String nameServer, int numPort) throws IOException {
		/*String nameServer = new String("0.0.0.0");
		int numPort = 7846;*/
		if(validate(nameServer) && numPort >0 && numPort <= 65535){
			Client client = new Client(nameServer, numPort, new SuperControleur());
			
			/*Exemple de création de commande */
			
			//Début du commencement de la fin
	       client.getMessage().envoiMessage("@sendfile");
		//	client.getMessage().envoiMessage("testmajucscule");
	       // client.getMessage().envoiMessage("@sendfileBis");
		}
		else{
			
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
