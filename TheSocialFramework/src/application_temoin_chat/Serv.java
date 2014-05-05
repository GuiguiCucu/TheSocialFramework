package application_temoin_chat;

import java.util.HashMap;

import application_temoin_chat.commandeServeur.DemandeConnexion;
import application_temoin_chat.commandeServeur.DemandeListUser;
import application_temoin_chat.commandeServeur.SendMessage;
import core.models.core_modele.Serveur;
import core.models.core_modele.commandes.commandesServeur.CommandeServeur;
import core.models.modules.module_clientserveur.LanceurServeur;

/**
 * Serveur de l'application chat
 * Noter l'IP et le port de la machine pour la connexion des clients
 * @author cutroneg
 *
 */
public class Serv {

	/**
	 * main - lancement du serveur
	 * @param args
	 */
	public static void main(String[] args) {
		HashMap<String, CommandeServeur> listeCommandes = new HashMap<String, CommandeServeur>();
		listeCommandes.put("@demandeconnexion", new DemandeConnexion());
		listeCommandes.put("@demande_deconnexion", new application_temoin_chat.commandeServeur.DemandeDeconnexion());
		listeCommandes.put("@demandeliste", new DemandeListUser());
		listeCommandes.put("@sendmessage", new SendMessage());
		Serveur serv = LanceurServeur.run(2048, listeCommandes);
	}

}
