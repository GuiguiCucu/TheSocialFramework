package core.models.modules.client_serveur.commandes.commandesServeur;

import core.models.modules.client_serveur.Message;
import core.models.modules.client_serveur.TraitementClient;

public interface CommandeServeur {
	public void execute(Message message, TraitementClient tc);
}
