package application_temoin_cloud.commandeServeur;

import java.io.IOException;

import core.models.core_modele.Message;
import core.models.core_modele.TraitementClient;
import core.models.core_modele.commandes.commandesServeur.CommandeServeur;

public class DemandeDeconnexion implements CommandeServeur {
	public void execute(TraitementClient tc) {
		try {
			Message message = tc.getMessage();
			message.envoiMessage("@confirm_demande_deconnexion");
			tc.getMessage().getEntree().close();
            tc.getMessage().getSortie().close();
            tc.getSocketDeTransfert().close();
			tc.getServ().delClient(tc);
			tc.setConnect(false);
			System.out.println("AND");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
