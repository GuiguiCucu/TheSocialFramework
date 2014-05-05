package application_temoin_cloud.commandeClient;

import java.io.IOException;

import application_temoin_chat.Controller;
import core.controleur.SuperControleur;
import core.models.core_modele.Message;
import core.models.core_modele.TraitementClient;
import core.models.core_modele.commandes.commandesClient.CommandeClient;
import core.models.core_modele.commandes.commandesServeur.CommandeServeur;

/**
 * Confirmation d'un telechargement depuis le serveur
 * @author cutroneg
 *
 */
public class ConfirmTelechargement implements CommandeClient{

	@Override
	public void execute(SuperControleur controleur) {		
			Message message = ((application_temoin_cloud.Controller)controleur).getClient().getMessage();
			String path = System.getProperty("user.home")+"\\Downloads";
			message.receptionFichier(path);
	}


		
}


