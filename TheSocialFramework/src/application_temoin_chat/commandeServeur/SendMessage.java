package application_temoin_chat.commandeServeur;

import java.io.IOException;

import application_temoin_chat.Controller;
import core.models.core_modele.Message;
import core.models.core_modele.TraitementClient;
import core.models.core_modele.commandes.commandesServeur.CommandeServeur;

public class SendMessage implements CommandeServeur {

	@Override
	public void execute(TraitementClient tc) {
			Message message = tc.getMessage();
			String provider = message.receptionMessage();
			String msg = message.receptionMessage();
			for(TraitementClient client : tc.getServ().getTraiteClients()){
				if(!(client.getNomClient().equals(provider))){
					client.getMessage().envoiMessage("@confirmsendmessage");
					client.getMessage().envoiMessage(provider);
					client.getMessage().envoiMessage(msg);
				}
			}
	}

}
