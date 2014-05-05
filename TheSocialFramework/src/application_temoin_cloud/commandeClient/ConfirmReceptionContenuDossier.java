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
	public void execute(SuperControleur controleur) {
		String envoi;
		HashMap<String, Long> listeFichier = new HashMap<String,Long>();	
		ArrayList<String> listeDossiers = new ArrayList<String>();
		Message message = ((application_temoin_cloud.Controller)controleur).getClient().getMessage();
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
	}
}
