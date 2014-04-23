package core.models.core_modele.commandes.commandesServeur;

import core.models.core_modele.Message;
import core.models.core_modele.TraitementClient;

public interface CommandeServeur {
	public void execute(Message message, TraitementClient tc);
}
