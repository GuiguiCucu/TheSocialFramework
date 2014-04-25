package application_temoin_chat.commandeServeur;

import java.io.IOException;

import core.models.core_modele.Message;
import core.models.core_modele.TraitementClient;
import core.models.core_modele.commandes.commandesServeur.CommandeServeur;

public class SendMessage implements CommandeServeur  {

	@Override
	public void execute(Message message, TraitementClient tc) {
		try {
				String msg = message.receptionMessage();
				System.out.println("MSG : "+msg);
				tc.getServ().sendAll(tc, "@confirmsendmessage");
				tc.getServ().sendAll(tc, msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
