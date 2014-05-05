package application_temoin_cloud.commandeClient;
import application_temoin_cloud.Controller;
import core.controleur.SuperControleur;
import core.models.core_modele.Message;
import core.models.core_modele.commandes.commandesClient.CommandeClient;

/**
 * Confiramtion de déconnexion d'un client
 * Fermeture des vues correspondantes
 * @author cutroneg
 *
 */
public class ConfirmDeconnexion implements CommandeClient {

	@Override
	public void execute(SuperControleur controleur) {
		
		((Controller)controleur).runVueConnexion();
		((Controller)controleur).getVueCloud().dispose();
		((Controller)controleur).getClient().deconnexionServeur();
		((Controller)controleur).getClient().setConnect(false);
	}

}
