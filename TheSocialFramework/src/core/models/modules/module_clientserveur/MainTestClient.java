package core.models.modules.module_clientserveur;

import core.controleur.SuperControleur;
import core.models.core_modele.exceptions.ClientException;

/**
 * Classe de test d'un client TCP
 * @author forestip
 *
 */
public class MainTestClient {
	public static void main(String[] args) {
		try {
			LanceurClient.run(new String("0.0.0.0"), 7846, new SuperControleur());
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
