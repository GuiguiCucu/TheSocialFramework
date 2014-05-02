package application_temoin_chat.commandeServeur;

import application_temoin_chat.Controller;
import core.models.core_modele.Message;
import core.models.core_modele.TraitementClient;
import core.models.core_modele.commandes.commandesServeur.CommandeServeur;

public class DemandeListUser implements CommandeServeur {

	@Override
	public void execute(/*Message message, */TraitementClient tc) {
		Message message = tc.getMessage();
		message.envoiMessage("@confirmlisteusers");
		for (TraitementClient client : tc.getServ().getTraiteClients()) {
			System.out.println(client.getNomClient());
			message.envoiMessage(client.getNomClient());
		}
		message.envoiMessage("@finliste");
	}

}
