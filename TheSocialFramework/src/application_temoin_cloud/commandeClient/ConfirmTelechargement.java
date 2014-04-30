package application_temoin_cloud.commandeClient;

import java.io.IOException;

import core.controleur.SuperControleur;
import core.models.core_modele.Message;
import core.models.core_modele.TraitementClient;
import core.models.core_modele.commandes.commandesClient.CommandeClient;
import core.models.core_modele.commandes.commandesServeur.CommandeServeur;

public class ConfirmTelechargement implements CommandeClient{

	@Override
	public void execute(Message message, SuperControleur controleur) {		
		try {
			String path = System.getProperty("user.home")+"\\Downloads";
			message.receptionFichier(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


		
}


