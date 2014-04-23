package core.models.modules.module_clientserveur;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import core.models.core_modele.Serveur;
import core.models.core_modele.TraitementClient;
import core.models.core_modele.commandes.commandesServeur.EnvoiFichier;

public class LanceurServeur {

	public static void run(int numPort) {
		if(numPort >0 && numPort <= 65535){
			try {
				Serveur serv = new Serveur(7846);
				serv.getListeCommandes().put("@sendfile", new EnvoiFichier());
				System.out.println("Serveur lancé!");
				while (true) {
					new TraitementClient(serv.getSocketEcoute().accept(), serv);
				}
			} catch (IOException ex) {
				//TODO Changer pour lever une exception
				JOptionPane.showMessageDialog(new JFrame(),
						"Ce port à cette adresse est occupé");
				System.exit(0);
			}
		}
		else{
			//TODO Lever Exceptions mauvais port
		}
	}
}
