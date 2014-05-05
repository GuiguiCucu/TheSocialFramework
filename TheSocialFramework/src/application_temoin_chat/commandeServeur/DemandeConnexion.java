package application_temoin_chat.commandeServeur;

import java.io.IOException;

import application_temoin_chat.Controller;
import core.models.core_modele.Message;
import core.models.core_modele.TraitementClient;
import core.models.core_modele.commandes.commandesServeur.CommandeServeur;

public class DemandeConnexion implements CommandeServeur {

	@Override
	public void execute(/*Message message,*/ TraitementClient tc) {
		Message message = tc.getMessage();
		message.envoiMessage("@confirmconnexion");
		try {
			String pseudo = message.receptionMessage();
			message.envoiMessage("@okconnexion");
			tc.setNomClient(pseudo);

			for (TraitementClient tcMaj : tc.getServ().getTraiteClients()) {
				System.out.println("MAJ");
				tcMaj.getServ().getListeCommandes().get("@demandeliste")
						.execute(/*tcMaj.getMessage(),*/ tcMaj);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}