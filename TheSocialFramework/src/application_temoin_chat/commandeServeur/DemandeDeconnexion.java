package application_temoin_chat.commandeServeur;

import java.io.IOException;

import application_temoin_chat.Controller;
import core.models.core_modele.Message;
import core.models.core_modele.TraitementClient;
import core.models.core_modele.commandes.commandesServeur.CommandeServeur;

/**
 * Prend en compte la demande de déconnexion d'un client
 * Fermeture des sockets
 * Suppression du client
 * @author cutroneg
 *
 */
public class DemandeDeconnexion implements CommandeServeur {
	public void execute(TraitementClient tc) {
		try {
			Message message = tc.getMessage();
			message.envoiMessage("@confirm_demande_deconnexion");
			tc.getMessage().getEntree().close();
            tc.getMessage().getSortie().close();
            tc.getSocketDeTransfert().close();
			tc.getServ().delClient(tc);
			for (TraitementClient tcMaj : tc.getServ().getTraiteClients()) {
				tcMaj.getServ().getListeCommandes().get("@demandeliste")
						.execute(tcMaj);
			}
			tc.setConnect(false);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
