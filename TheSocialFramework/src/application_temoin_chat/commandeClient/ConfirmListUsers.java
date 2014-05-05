package application_temoin_chat.commandeClient;

import java.io.IOException;
import java.util.ArrayList;

import application_temoin_chat.Controller;
import core.controleur.SuperControleur;
import core.models.core_modele.Message;
import core.models.core_modele.commandes.commandesClient.CommandeClient;

/**
 * Recpetion de la liste d'utilisateurs connectés au chat
 * @author cutroneg
 *
 */
public class ConfirmListUsers implements CommandeClient {

	@Override
	public void execute(SuperControleur controleur) {
		Message message = ((Controller)controleur).getClient().getMessage();
		String reponse;
			reponse = message.receptionMessage();
			ArrayList<String> utilisateurs = new ArrayList<String>();
			while (!(reponse.equals("@finliste"))) {
				utilisateurs.add(reponse);
				reponse = message.receptionMessage();
			}
			
			((Controller)controleur).alimenteListeUtilisateurs(utilisateurs);
	}

}
