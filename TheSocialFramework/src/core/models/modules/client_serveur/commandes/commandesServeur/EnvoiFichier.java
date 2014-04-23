package core.models.modules.client_serveur.commandes.commandesServeur;

import java.io.IOException;

import core.models.modules.client_serveur.Message;
import core.models.modules.client_serveur.TraitementClient;

public class EnvoiFichier implements CommandeServeur {
	@Override
	public void execute(Message message, TraitementClient tc) {
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
