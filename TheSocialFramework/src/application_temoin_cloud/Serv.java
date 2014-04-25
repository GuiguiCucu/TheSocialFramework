package application_temoin_cloud;


import java.util.HashMap;

import core.models.core_modele.Serveur;
import core.models.core_modele.commandes.commandesServeur.EnvoiFichier;
import core.models.modules.module_clientserveur.LanceurServeur;
import core.models.core_modele.commandes.commandesServeur.*;

public class Serv {
	
	public static void main(String[] args) {
		HashMap<String, CommandeServeur> listeCommandes = new HashMap<String, CommandeServeur>();
	//	listeCommandes.put("@sendfile", new EnvoiFichier());
		listeCommandes.put("@afficherrepertoire", new DemandeContenuDossier());
		Serveur serv = LanceurServeur.run(2048, listeCommandes);
	}
	
}
