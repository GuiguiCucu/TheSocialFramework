package application_temoin_chat.commandeClient;

import java.io.IOException;

import application_temoin_chat.Controller;
import core.controleur.SuperControleur;
import core.models.core_modele.Message;
import core.models.core_modele.commandes.commandesClient.CommandeClient;

public class ConfirmSendMessage implements CommandeClient {

	@Override
	public void execute(Message message, SuperControleur controleur) {
		try {
			String reponse = message.receptionMessage();
			((Controller)controleur).alimenteDiscussion(reponse);
			System.out.println("message : "+reponse);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
