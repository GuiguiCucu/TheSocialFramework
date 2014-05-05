package core.models.modules.module_clientserveur;

/**
 * Classe de test d'un client UDP
 * @author forestip
 *
 */
public class MainTestClientUDP {

	public static void main(String[] args) {
			System.out.println("IN");
			LanceurClientUDP.run(new String("152.77.116.163"), 7846);

	}

}
