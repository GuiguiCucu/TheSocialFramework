package application_temoin_chat;

import java.util.HashMap;

import application_temoin_chat.commandeServeur.DemandeConnexion;
import core.models.core_modele.commandes.commandesServeur.CommandeServeur;

public class Serv {

	public static void main(String[] args) {
		HashMap<String, CommandeServeur> listeCommandes = new HashMap<String, CommandeServeur>();
		listeCommandes.put("@connexionChat", new DemandeConnexion());
	}

}
