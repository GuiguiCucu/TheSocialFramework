package core.models.core_modele.commandes.commandesServeur;

import core.models.core_modele.TraitementClientUDP;

public interface CommandeServeurUDP {
	public void execute(TraitementClientUDP tc);
}
