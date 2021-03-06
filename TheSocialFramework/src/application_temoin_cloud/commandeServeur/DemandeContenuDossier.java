package application_temoin_cloud.commandeServeur;

import java.io.File;
import java.util.ArrayList;

import core.models.core_modele.Message;
import core.models.core_modele.TraitementClient;
import core.models.core_modele.commandes.commandesServeur.CommandeServeur;

/**
 * demande d'envoi du contenu d'un dossier a un client
 * @author cutroneg
 *
 */
public class DemandeContenuDossier implements CommandeServeur {

	@Override
	public void execute(TraitementClient tc) {
		Message message = tc.getMessage();
		message.envoiMessage("@okafficherrepertoire");

		File[] listeDocuments;
		
		String repertoireCourant = System.getProperty("user.dir");
		File repertoire = new File(repertoireCourant+"/"+tc.getNomClient());
		listeDocuments = repertoire.listFiles();

		ArrayList<String> listeFichiers = new ArrayList<String>();
		ArrayList<String> listeDossiers = new ArrayList<String>();
		ArrayList<String> tailleFichiers = new ArrayList<String>();

		for (int i = 0; i < listeDocuments.length; i++) {
			if ((listeDocuments[i]).isFile()) {
				listeFichiers.add(listeDocuments[i].getName());
				tailleFichiers.add(String.valueOf(listeDocuments[i].length()));
			} else if (listeDocuments[i].isDirectory()) {
				listeDossiers.add(listeDocuments[i].getName());
			}
		}

		// Envoi fichiers
		for (int i = 0; i < listeFichiers.size(); i++) {
			message.envoiMessage(listeFichiers.get(i));
			message.envoiMessage(tailleFichiers.get(i));
		}
		message.envoiMessage("@fin_envoi_fichier");

		// Envoi dossiers
		for (int i = 0; i < listeDossiers.size(); i++) {
			message.envoiMessage(listeDossiers.get(i));
		}
		message.envoiMessage("@fin_envoi_dossier");

		// Fin envoi
		message.envoiMessage("@fin_envoi_contenu");
	}

}
