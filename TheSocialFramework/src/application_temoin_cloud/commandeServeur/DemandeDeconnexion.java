package application_temoin_cloud.commandeServeur;

import core.models.core_modele.Message;
import core.models.core_modele.TraitementClient;
import core.models.core_modele.commandes.commandesServeur.CommandeServeur;

public class DemandeDeconnexion implements CommandeServeur {
	@Override
	public void execute(Message message, TraitementClient tc) {
		message.envoiMessage("@confirm_demande_deconnexion");
		

	}

}
