package application_temoin_chat.commandeServeur;

import application_temoin_chat.Controller;
import core.models.core_modele.Message;
import core.models.core_modele.TraitementClient;
import core.models.core_modele.commandes.commandesServeur.CommandeServeur;

/**
 * Envoi les noms de tous les clients connectés + indicateur de fin de liste
 * @author cutroneg
 *
 */
public class DemandeListUser implements CommandeServeur {

	@Override
	public void execute(TraitementClient tc) {
		Message message = tc.getMessage();
		message.envoiMessage("@confirmlisteusers");
		for (TraitementClient client : tc.getServ().getTraiteClients()) {
			message.envoiMessage(client.getNomClient());
		}
		message.envoiMessage("@finliste");
	}

}
