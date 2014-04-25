package application_temoin_cloud.commandeClient;
import application_temoin_cloud.Controller;
import core.controleur.SuperControleur;
import core.models.core_modele.Message;
import core.models.core_modele.commandes.commandesClient.CommandeClient;

public class ConfirmDeconnexion implements CommandeClient {

	@Override
	public void execute(Message message, SuperControleur controleur) {
		((Controller)controleur).runVueConnexion();
		((Controller)controleur).getVueCloud().dispose();
		((Controller)controleur).getClient().deconnexionServeur();
	}

}
