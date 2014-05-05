package application_temoin_cloud.commandeClient;

import java.io.IOException;

import application_temoin_cloud.Controller;
import core.controleur.SuperControleur;
import core.models.core_modele.Message;
import core.models.core_modele.commandes.commandesClient.CommandeClient;

/**
 * Confirmation d'un upload de fichier depuis le client vers le serveur
 * @author cutroneg
 *
 */
public class ConfirmUpload implements CommandeClient {

	@Override
	public void execute( SuperControleur controleur) {
			Message message = ((Controller)controleur).getClient().getMessage();
			String pathFile = message.receptionMessage();
			message.envoiFichier(pathFile);
	}

}
