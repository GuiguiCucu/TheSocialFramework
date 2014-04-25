package core.models.core_modele.commandes.commandesServeur;

import java.io.IOException;

import core.models.core_modele.Message;
import core.models.core_modele.TraitementClient;


/*@demandedeco*/
public class DemandeDeconnexion implements CommandeServeur {

	@Override
	public void execute(Message message, TraitementClient tc) {
		System.out.println("Demande déconnexion");
		message.envoiMessage("@okdeconnexion");
				
		//Fermeture message
		try {
			message.fermeture();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//femeture Traitement client
		//TODO : passer Runnable?
		//TODO : passe TraitementClient? (=> Deux interface? Une client, une serveur)
	} 
}
