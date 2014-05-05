package core.models.modules.module_clientserveur;

import java.io.IOException;
import java.util.HashMap;
import core.models.core_modele.commandes.commandesServeur.CommandeServeur;

/**
 * Classe de test d'un serveur TCP
 * @author forestip
 *
 */
public class MainTestServeur {

	public static void main(String[] args) throws IOException {
		LanceurServeur.run(7846, new HashMap<String, CommandeServeur>());
	}

}
