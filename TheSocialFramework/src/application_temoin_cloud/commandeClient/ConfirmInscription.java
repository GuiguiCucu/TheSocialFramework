package application_temoin_cloud.commandeClient;

import java.io.IOException;

import javax.swing.JOptionPane;

import application_temoin_cloud.Controller;
import core.controleur.SuperControleur;
import core.models.core_modele.Message;
import core.models.core_modele.commandes.commandesClient.CommandeClient;

public class ConfirmInscription implements CommandeClient {

	@Override
	public void execute(SuperControleur controleur) {
		String response;
		try {
			Message message = ((Controller)controleur).getClient().getMessage();
			response = message.receptionMessage();
			if (response.equals("@alreadyexisting")) {
				JOptionPane.showMessageDialog(null, "Login déjà utilisé",
						"Erreur", JOptionPane.ERROR_MESSAGE);
			} else if (response.equals("@okinscription")) {
				((Controller) controleur).getVueInscription().dispose();
				JOptionPane.showMessageDialog(null,
						"Inscription réussie. Veuillez vous connecter.",
						"Succes", JOptionPane.INFORMATION_MESSAGE);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
