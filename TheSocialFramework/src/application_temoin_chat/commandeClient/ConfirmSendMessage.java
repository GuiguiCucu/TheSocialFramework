package application_temoin_chat.commandeClient;

import java.io.IOException;

import application_temoin_chat.Controller;
import core.controleur.SuperControleur;
import core.models.core_modele.Message;
import core.models.core_modele.commandes.commandesClient.CommandeClient;

/**
 * Permet la reception de message envoyé depuis un autre client
 * Alimente la fenetre de discussion avec les nouveaux messages
 * @author cutroneg
 *
 */
public class ConfirmSendMessage implements CommandeClient {

	@Override
	public void execute(SuperControleur controleur) {
			Message message = ((Controller)controleur).getClient().getMessage();
			String origine =  message.receptionMessage();
			String msg = message.receptionMessage();
			((Controller)controleur).alimenteDiscussion(origine, msg);
	}

}
