package application_temoin_cloud;

import java.io.IOException;

import core.models.core_modele.Message;
import core.models.core_modele.commandes.commandesClient.CommandeClient;

public class ConfirmReceptionContenuDossier implements CommandeClient {

	@Override
	public void execute(Message message) {
		String envoi;
		try {
			envoi = message.receptionMessage();
			while(!(envoi.equals("@fin_envoi_contenu"))){
				envoi = message.receptionMessage();
				System.out.println("Element du cloud : "+envoi);
			}
			System.out.println("OUT");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
