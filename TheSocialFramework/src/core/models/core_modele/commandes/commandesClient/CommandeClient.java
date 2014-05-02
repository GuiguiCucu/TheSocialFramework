package core.models.core_modele.commandes.commandesClient;

import core.controleur.SuperControleur;
import core.models.core_modele.Message;
import core.models.core_modele.TraitementClient;

public interface  CommandeClient {
	public void execute(/*Message message,*/ SuperControleur controleur);

}
