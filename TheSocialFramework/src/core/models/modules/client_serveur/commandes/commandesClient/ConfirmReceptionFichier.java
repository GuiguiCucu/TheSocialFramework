package core.models.modules.client_serveur.commandes.commandesClient;

import java.io.IOException;

import core.models.modules.client_serveur.Message;

public class ConfirmReceptionFichier implements CommandeClient {

	@Override
	public void execute(Message message) {
        System.out.println("Envoi fichier...");
        try {
			message.envoiFichier();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
