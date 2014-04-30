package application_temoin_cloud.commandeClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import application_temoin_cloud.Controller;
import core.controleur.SuperControleur;
import core.models.core_modele.Message;
import core.models.core_modele.commandes.commandesClient.CommandeClient;

public class ConfirmReceptionContenuDossier implements CommandeClient {

	@Override
	public void execute(Message message, SuperControleur controleur) {
		String envoi;
		HashMap<String, Long> listeFichier = new HashMap<String,Long>();	
		ArrayList<String> listeDossiers = new ArrayList<String>();
		try {
			envoi = message.receptionMessage();
			while (!(envoi.equals("@fin_envoi_contenu"))) {
				while (!(envoi.equals("@fin_envoi_fichier"))) {
					Long taille = Long.valueOf(message.receptionMessage());
					listeFichier.put(envoi, taille);
					envoi = message.receptionMessage();
				}
				envoi = message.receptionMessage();
				while (!(envoi.equals("@fin_envoi_dossier"))) {
					listeDossiers.add(envoi);
					envoi = message.receptionMessage();
				}
				envoi = message.receptionMessage();
			}
			((Controller)controleur).alimenteVueCloud(listeDossiers, listeFichier);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
