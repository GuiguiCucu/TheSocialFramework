package core.models.core_modele.commandes.commandesClient;

import java.io.IOException;

import core.models.core_modele.Message;

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
