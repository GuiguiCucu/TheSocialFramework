package application_temoin_chat.commandeClient;

import application_temoin_chat.Controller;
import core.controleur.SuperControleur;
import core.models.core_modele.Message;
import core.models.core_modele.commandes.commandesClient.CommandeClient;

public class ConfirmDeconnexion implements CommandeClient {

	@Override
	public void execute(/* Message message, */SuperControleur controleur) {
		//Message message = ((Controller) controleur).getClient().getMessage();
		((Controller) controleur).getClient().deconnexionServeur();
		((Controller) controleur).getClient().setConnect(false);
		System.exit(0);
	}

}
