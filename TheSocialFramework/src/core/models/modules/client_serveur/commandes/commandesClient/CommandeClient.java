package core.models.modules.client_serveur.commandes.commandesClient;

import core.models.modules.client_serveur.Message;

public interface  CommandeClient {
	public void execute(Message message);
}
