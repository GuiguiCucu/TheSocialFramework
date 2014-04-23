package core.models.core_modele.commandes.commandesServeur;

import java.io.IOException;

import core.models.core_modele.Message;
import core.models.core_modele.TraitementClient;

public class EnvoiFichier2 implements CommandeServeur {
	@Override
	public void execute(Message message, TraitementClient tc) {
		System.out.println("Demande d'envoi de fichier");
		System.out.println("Envoi de la confirmation de la demande pour débuter le transfert");
		message.envoiMessage("@oksendfileBis");
		try {
			message.receptionFichier();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
