package core.models.modules.module_clientserveur;

import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import core.models.core_modele.Serveur;
import core.models.core_modele.TraitementClient;
import core.models.core_modele.commandes.commandesServeur.CommandeServeur;
import core.models.core_modele.exceptions.ClientException;
import core.models.core_modele.exceptions.ServeurException;

/**
 * Classe permettant à l'utilisateur de lancer un serveur de manière statique
 * @author forestip
 *
 */
public class LanceurServeur {

	/**
	 * Crée un serveur TCP écoutant en boucle les connexions et créant un processus client lors de la connexion
	 * @param numPort le port d'écoute
	 * @param listeCommandes l'ensembles des commandes du seveur( ne peut être modifié après l'appel à run)
	 * @return le serveur TCP actif crée
	 */
	public static Serveur run(int numPort,HashMap<String, CommandeServeur> listeCommandes) {
		Serveur serv = null;
		if(numPort >0 && numPort <= 65535){
			try {
				serv = new Serveur(numPort);
				serv.getListeCommandes().putAll(listeCommandes);
				System.out.println("Serveur lancé!");
				while (true) {
					new TraitementClient(serv.getSocketEcoute().accept(), serv);
				}
			} catch (IOException ex) {
				//TODO Changer pour lever une exception
				JOptionPane.showMessageDialog(new JFrame(),
						"Ce port à cette adresse est occupé");
				System.exit(0);
			}
		}
		else{
			try {
				throw new ServeurException("Les paramètres passés sont incorrects");
			} catch (ServeurException e) {
				e.printStackTrace();
			}
		}
		return serv;
	}
}
