package application_temoin_chat.commandeClient;

import java.io.IOException;


import application_temoin_chat.Controller;
import core.controleur.SuperControleur;
import core.models.core_modele.Message;
import core.models.core_modele.commandes.commandesClient.CommandeClient;

/**
 * Permet la récupération du message de confirmation de connexion
 * Ferme la vue de choix du pseudo et lance la fenêtre de discussion 
 * @author cutroneg
 *
 */
public class ConfirmConnexion implements CommandeClient {

	@Override
	public void execute(SuperControleur controleur) {
			Message message = ((Controller)controleur).getClient().getMessage();
			String reponse = message.receptionMessage();
			if (reponse.equals("@okconnexion")) {
				((Controller) controleur).runVueDiscussion();
				((Controller) controleur).getVuePseudo().dispose();
			}

	}

}
