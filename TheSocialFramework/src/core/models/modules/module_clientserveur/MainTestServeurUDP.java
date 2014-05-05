package core.models.modules.module_clientserveur;

import java.util.HashMap;

import core.models.core_modele.commandes.commandesServeur.CommandeServeurUDP;

public class MainTestServeurUDP {

	public static void main(String[] args) {
		LanceurServeurUDP.run(7846, new HashMap<String, CommandeServeurUDP>());

	}

}
