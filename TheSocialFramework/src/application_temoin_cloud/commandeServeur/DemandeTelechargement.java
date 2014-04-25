package application_temoin_cloud.commandeServeur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import core.controleur.SuperControleur;
import core.models.core_modele.Message;
import core.models.core_modele.TraitementClient;
import core.models.core_modele.commandes.commandesClient.CommandeClient;
import core.models.core_modele.commandes.commandesServeur.CommandeServeur;

public class DemandeTelechargement implements CommandeServeur{

	@Override
	public void execute(Message message, TraitementClient tc) {

		try {
			System.out.println(this.getClass().getCanonicalName());
			message.envoiMessage("@oktelechargement");
			String nomFichier = message.receptionMessage();
			message.envoiFichier();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
