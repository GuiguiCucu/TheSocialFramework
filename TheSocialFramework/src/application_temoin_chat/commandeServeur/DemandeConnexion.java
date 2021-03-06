package application_temoin_chat.commandeServeur;

import java.io.IOException;

import application_temoin_chat.Controller;
import core.models.core_modele.Message;
import core.models.core_modele.TraitementClient;
import core.models.core_modele.commandes.commandesServeur.CommandeServeur;

/**
 * Permet au serveur de prendre en compte la demande de connexion d'un nouveau client
 * Reception du pseudo du client pour l'ajouter à la liste de clients connectés
 * Envoi de la confirmation de connextion au client
 * @author cutroneg
 *
 */
public class DemandeConnexion implements CommandeServeur {

	@Override
	public void execute( TraitementClient tc) {
		Message message = tc.getMessage();
		message.envoiMessage("@confirmconnexion");
			String pseudo = message.receptionMessage();
			message.envoiMessage("@okconnexion");
			tc.setNomClient(pseudo);

			for (TraitementClient tcMaj : tc.getServ().getTraiteClients()) {
				tcMaj.getServ().getListeCommandes().get("@demandeliste")
						.execute( tcMaj);
			}
	}

}
