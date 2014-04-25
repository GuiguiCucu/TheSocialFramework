package core.models.core_modele.commandes.commandesClient;

import core.controleur.SuperControleur;
import core.models.core_modele.Message;

public interface  CommandeClient {
	public void execute(Message message, SuperControleur controleur);
}
