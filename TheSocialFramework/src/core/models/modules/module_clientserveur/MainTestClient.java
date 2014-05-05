package core.models.modules.module_clientserveur;

/**
 * Classe de test d'un client TCP
 * @author forestip
 *
 */
public class MainTestClient {
	public static void main(String[] args) {
		LanceurClient.run(new String("0.0.0.0"), 7846);
	}

}
