package core.models.core_modele.commandes.commandesServeur;

import core.models.core_modele.Message;
import core.models.core_modele.TraitementClient;

/**
 * Interface de commandes définissant les actions engendrées par les communications client-serveur
 * @author forestip
 *
 */
public interface CommandeServeur {
	
	/**
	 * Méthode appelée dans les boucles d'écoutes du programme serveur
	 * Si à une chaine de caractère correspond une commande, la méthode execute() de celle-ci est appelée
	 * Permettant d'accéder aux traitements et données de l'application
	 * @param tc Le processus client côté serveur
	 */
	public void execute(TraitementClient tc);
}
