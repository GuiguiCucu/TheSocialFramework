package application_temoin_cloud.commandeServeur;

import java.io.IOException;

import core.models.core_modele.Message;
import core.models.core_modele.TraitementClient;
import core.models.core_modele.commandes.commandesServeur.CommandeServeur;

public class DemandeDeconnexion implements CommandeServeur {
	public void execute(Message message, TraitementClient tc) {
		try {
			System.out.println("IN DEMANDE DECO");
			message.envoiMessage("@confirm_demande_deconnexion");
			System.out.println("11111111111");
			tc.getMessage().getEntree().close();
            tc.getMessage().getSortie().close();
            tc.getSocketDeTransfert().close();
			System.out.println("2222");
			tc.getServ().delClient(tc);
			System.out.println("3333");
			tc.setConnect(false);
			System.out.println("END demande Deconnexion");	
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
