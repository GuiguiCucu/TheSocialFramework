package application_temoin_streamaudio;

import java.util.HashMap;

import core.models.core_modele.ServeurUDP;
import core.models.core_modele.commandes.commandesServeur.CommandeServeurUDP;
import core.models.modules.module_clientserveur.LanceurServeurUDP;

public class Serv {
	
	public static void main(String[] args) {
		HashMap<String, CommandeServeurUDP> listeCommandes = new HashMap<String, CommandeServeurUDP>();
		listeCommandes.put("@demandeenvoiflux", new DemandeEnvoiFlux());
		ServeurUDP serv = LanceurServeurUDP.run(2048, listeCommandes);
	}

}
