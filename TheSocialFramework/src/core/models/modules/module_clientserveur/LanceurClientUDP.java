package core.models.modules.module_clientserveur;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.controleur.SuperControleur;
import core.models.core_modele.ClientUDP;
import core.models.core_modele.exceptions.ClientException;

/**
 * Classe permettant à l'utilisateur de lancer un client UDP de manière statique
 * @author forestip
 *
 */
public class LanceurClientUDP {

	/**
	 * Crée un client UDP écoutant en boucle les émissions du serveur 
	 * @param nameServer l'ip du serveur
	 * @param numPort le port d'écoute du serveur
	 * @return le serveur actif crée
	 */
	public static ClientUDP run(String nameServer, int numPort){
		if(validate(nameServer) && numPort >0 && numPort <= 65535){
			System.out.println("PORT");
			ClientUDP client = new ClientUDP(nameServer, numPort, new SuperControleur());
			return client;
		}
		else{
			try {
				throw new ClientException("Les paramètres passés sont incorrects");
			} catch (ClientException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	/**
	 * Pattern d'un string
	 */
	private static final String PATTERN = 
	        "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	/**
	 * Méthode permettant d'appliquer un masque sur une String afin de vérifier sa compatibilité avec la notation des IP
	 * @param ip l'ip à vérifier
	 * @return la validité de l'ip fournie
	 */
	private static boolean validate(final String ip){          
	      Pattern pattern = Pattern.compile(PATTERN);
	      Matcher matcher = pattern.matcher(ip);
	      return matcher.matches();             
	}

}
