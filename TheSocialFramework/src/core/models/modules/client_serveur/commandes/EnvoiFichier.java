package core.models.modules.client_serveur.commandes;

import java.io.IOException;

import core.models.modules.client_serveur.Message;

public class EnvoiFichier implements Commande {
	@Override
	public void execute(Message message) {
		System.out.println("Demande d'envoi de fichier");
		System.out.println("Envoi de la confirmation de la demande pour débuter le transfert");
		message.envoiMessage("@oksendfile");
		try {
			message.receptionFichier();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
