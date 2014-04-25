package application_temoin_cloud;

import java.io.IOException;

import core.models.core_modele.Message;
import core.models.core_modele.TraitementClient;
import core.models.core_modele.commandes.commandesServeur.CommandeServeur;

public class DemandeConnexion implements CommandeServeur {

	@Override
	public void execute(Message message, TraitementClient tc) {
		String nickname;
		try {
			nickname = message.receptionMessage();
			String password = message.receptionMessage();
			if(User.verification(nickname, password, tc.getServ().getUsers())){
				message.envoiMessage("@okconnexion");
				tc.setNomClient(nickname);
			}else{
				message.envoiMessage("@invalideconnexion");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		//tc.getServ().g
	}

}
