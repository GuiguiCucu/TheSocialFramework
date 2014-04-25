package core.models.core_modele.commandes.commandesClient;

import java.io.IOException;

import core.controleur.SuperControleur;
import core.models.core_modele.Message;

public class ConfirmReceptionFichier2 implements CommandeClient {

	@Override
	public void execute(Message message, SuperControleur controleur) {
        System.out.println("Envoi fichier...");
        try {
			message.envoiFichier2();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
