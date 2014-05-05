package core.models.core_modele.commandes.commandesClient;

import core.controleur.SuperControleur;
import core.models.core_modele.Message;
import core.models.core_modele.TraitementClient;

/**
 * Interface de commandes définissant les actions engendrées par les communications client-serveur
 * @author forestip
 *
 */
public interface CommandeClient {
	/**
	 * Méthode appelée dans les boucles d'écoutes des programmes clients
	 * Si à une chaine de caractère correspond une commande, la méthode execute() de celle-ci est appelée
	 * Permettant d'accéder aux traitements et données de l'application
	 * @param controleur L'objet controleur côté client 
	 */
	public void execute(SuperControleur controleur);

}
