package application_temoin_cloud.commandeClient;

import java.io.IOException;

import javax.swing.JOptionPane;

import application_temoin_cloud.Controller;
import core.controleur.SuperControleur;
import core.models.core_modele.Message;
import core.models.core_modele.commandes.commandesClient.CommandeClient;

public class ConfirmConnexion implements CommandeClient {

	@Override
	public void execute(Message message, SuperControleur controleur) {
		try {
			String reponse = message.receptionMessage();
			if (reponse.equals("@okconnexion")) {
				((Controller) controleur).runVueCloud();
				((Controller) controleur).getVueConnexion().dispose();
			} else if (reponse.equals("@invalideconnexion")) {
				((Controller) controleur).setUserName("");
				JOptionPane.showMessageDialog(null,
						"Login ou mot de passe incorrect", "Erreur",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
