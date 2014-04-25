package application_temoin_chat.commandeServeur;

import java.io.IOException;

import core.models.core_modele.Message;
import core.models.core_modele.TraitementClient;
import core.models.core_modele.commandes.commandesServeur.CommandeServeur;

public class DemandeConnexion implements CommandeServeur {

	@Override
	public void execute(Message message, TraitementClient tc) {
		message.envoiMessage("@okconnexion");
	}

}
