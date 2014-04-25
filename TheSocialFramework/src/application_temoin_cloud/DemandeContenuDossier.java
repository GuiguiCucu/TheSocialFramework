package application_temoin_cloud;

import java.io.File;

import core.models.core_modele.Message;
import core.models.core_modele.TraitementClient;
import core.models.core_modele.commandes.commandesServeur.CommandeServeur;

public class DemandeContenuDossier implements CommandeServeur {

	@Override
	public void execute(Message message, TraitementClient tc) {
		System.out.println("in "+this.getClass().getCanonicalName());
		message.envoiMessage("@okafficherrepertoire");
		
		String[] listeFichiers;
		File repertoire = new File("./" );
		listeFichiers = repertoire.list();
		for(int i =0; i <listeFichiers.length;i++){
			message.envoiMessage(listeFichiers[i]);
		}
		
		message.envoiMessage("@fin_envoi_contenu");
	}

}
