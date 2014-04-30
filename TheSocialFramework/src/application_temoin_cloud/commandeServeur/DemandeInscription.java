package application_temoin_cloud.commandeServeur;

import java.io.IOException;

import application_temoin_cloud.User;
import core.models.core_modele.Message;
import core.models.core_modele.TraitementClient;
import core.models.core_modele.commandes.commandesServeur.CommandeServeur;

public class DemandeInscription implements CommandeServeur {

	@Override
	public void execute(Message message, TraitementClient tc) {
		try {
			message.envoiMessage("@confirm_inscription");
			String nicknameWait = message.receptionMessage();
			String password = message.receptionMessage();
			if(tc.getServ().getUsers().rechercheContact(nicknameWait)){
				message.envoiMessage("@alreadyexisting");
			}else{
				message.envoiMessage("@okinscription");
				tc.getServ().getUsers().ajouterPersonne(new User(nicknameWait, password));
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
