package application_temoin_cloud.commandeServeur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import core.controleur.SuperControleur;
import core.models.core_modele.Message;
import core.models.core_modele.TraitementClient;
import core.models.core_modele.commandes.commandesClient.CommandeClient;
import core.models.core_modele.commandes.commandesServeur.CommandeServeur;

/**
 * Demande de telechargement d'un fichier par un client
 * @author cutroneg
 *
 */
public class DemandeTelechargement implements CommandeServeur {

	@Override
	public void execute(TraitementClient tc) {
		Message message = tc.getMessage();
			message.envoiMessage("@oktelechargement");
			String userName = message.receptionMessage();
			String nomFichier = message.receptionMessage();
			message.envoiFichier(tc.getCurrentDir().getPath() + "/"
					+ nomFichier);
	}

}
