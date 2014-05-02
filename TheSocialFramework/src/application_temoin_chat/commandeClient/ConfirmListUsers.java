package application_temoin_chat.commandeClient;

import java.io.IOException;
import java.util.ArrayList;

import application_temoin_chat.Controller;
import core.controleur.SuperControleur;
import core.models.core_modele.Message;
import core.models.core_modele.commandes.commandesClient.CommandeClient;

public class ConfirmListUsers implements CommandeClient {

	@Override
	public void execute(/*Message message, */SuperControleur controleur) {
		Message message = ((Controller)controleur).getClient().getMessage();
		String reponse;
		try {
			reponse = message.receptionMessage();
			ArrayList<String> utilisateurs = new ArrayList<String>();
			while (!(reponse.equals("@finliste"))) {
				System.out.println("OUAT : "+reponse);
				utilisateurs.add(reponse);
				reponse = message.receptionMessage();
			}
			
			((Controller)controleur).alimenteListeUtilisateurs(utilisateurs);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
