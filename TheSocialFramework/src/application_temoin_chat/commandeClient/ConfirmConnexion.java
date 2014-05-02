package application_temoin_chat.commandeClient;

import java.io.IOException;


import application_temoin_chat.Controller;
import core.controleur.SuperControleur;
import core.models.core_modele.Message;
import core.models.core_modele.commandes.commandesClient.CommandeClient;

public class ConfirmConnexion implements CommandeClient {

	@Override
	public void execute(/*Message message,*/ SuperControleur controleur) {
		try {
			Message message = ((Controller)controleur).getClient().getMessage();
			String reponse = message.receptionMessage();
			if (reponse.equals("@okconnexion")) {
				((Controller) controleur).runVueDiscussion();
				((Controller) controleur).getVuePseudo().dispose();
			}
			//message.envoiMessage("@demandeliste");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
