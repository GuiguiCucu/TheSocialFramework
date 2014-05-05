package core.models.modules.module_clientserveur;

import java.util.HashMap;

import core.models.core_modele.commandes.commandesServeur.CommandeServeurUDP;
/**
 * Classe de test d'un serveur UDP
 * @author forestip
 *
 */
public class MainTestServeurUDP {

	public static void main(String[] args) {
		LanceurServeurUDP.run(7846, new HashMap<String, CommandeServeurUDP>());

	}

}
