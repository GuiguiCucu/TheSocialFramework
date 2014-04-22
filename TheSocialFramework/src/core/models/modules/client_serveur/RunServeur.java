package core.models.modules.client_serveur;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import core.models.modules.client_serveur.commandes.EnvoiFichier;

public class RunServeur {

	public static void main(String[] args) {
		try {
			Serveur serv = new Serveur(7846);
			serv.getListeCommandes().put("@sendfile", new EnvoiFichier());
			System.out.println("Serveur lancé!");
			while (true) {
				new TraitementClient(serv.getSocketEcoute().accept(), serv);
			}
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Ce port à cette adresse est occupé");
			System.exit(0);
		}

	}

}
