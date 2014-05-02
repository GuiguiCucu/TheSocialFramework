package application_temoin_cloud.commandeServeur;

import java.io.IOException;

import core.models.core_modele.Message;
import core.models.core_modele.TraitementClient;
import core.models.core_modele.commandes.commandesServeur.CommandeServeur;

public class DemandeUpload implements CommandeServeur {

	@Override
	public void execute(TraitementClient tc) {
		Message message = tc.getMessage();
		try {
			String fileName = message.receptionMessage();
			message.envoiMessage("@oksendfile");
			message.envoiMessage(fileName);
			message.receptionFichier(tc.getCurrentDir().getPath());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
