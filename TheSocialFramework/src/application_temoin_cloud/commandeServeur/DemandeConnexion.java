package application_temoin_cloud.commandeServeur;

import java.io.IOException;

import application_temoin_cloud.User;
import core.models.core_modele.Message;
import core.models.core_modele.TraitementClient;
import core.models.core_modele.commandes.commandesServeur.CommandeServeur;

/**
 * Demande de connexion d'un client
 * Reception des infos du client et envoi de la confirmation de connexion
 * @author cutroneg
 *
 */
public class DemandeConnexion implements CommandeServeur {

	@Override
	public void execute(TraitementClient tc) {
		Message message = tc.getMessage();
		message.envoiMessage("@confirmconnexion");
		String nickname;
		String password;
			nickname = message.receptionMessage();
			password = message.receptionMessage();
			if(User.verification(nickname, password, tc.getServ().getUsers())){
				message.envoiMessage("@okconnexion");
				tc.setNomClient(nickname);
				tc.folder();
			}else{
				message.envoiMessage("@invalideconnexion");
			}
	}

}
