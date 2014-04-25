package application_temoin_chat;

import java.util.HashMap;

import application_temoin_chat.commandeServeur.DemandeConnexion;
import application_temoin_chat.commandeServeur.SendMessage;
import core.models.core_modele.Serveur;
import core.models.core_modele.commandes.commandesServeur.CommandeServeur;
import core.models.modules.module_clientserveur.LanceurServeur;

public class Serv {

	public static void main(String[] args) {
		HashMap<String, CommandeServeur> listeCommandes = new HashMap<String, CommandeServeur>();
		listeCommandes.put("@demandeconnexion", new DemandeConnexion());
		listeCommandes.put("@sendmessage", new SendMessage());
		Serveur serv = LanceurServeur.run(2048, listeCommandes);
	}

}
