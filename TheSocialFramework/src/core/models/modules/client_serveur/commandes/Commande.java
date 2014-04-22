package core.models.modules.client_serveur.commandes;

import core.models.modules.client_serveur.Message;

public interface  Commande {
	public void execute(Message message);
}
