package core.models.modules.client_serveur;

import java.io.IOException;

import core.models.modules.client_serveur.commandes.ConfirmReceptionFichier;

public class RunClient {
	public static void main(String[] args) throws IOException {
		String nameServer = new String("0.0.0.0");
		int numPort = 7846;
		Client client = new Client(nameServer, numPort);
		/*Exemple de création de commande */
		client.getListeCommandes().put("@oksendfile", new ConfirmReceptionFichier());	
		//Début du commencement de la fin
        client.getMessage().envoiMessage("@sendfile");
	}
}
