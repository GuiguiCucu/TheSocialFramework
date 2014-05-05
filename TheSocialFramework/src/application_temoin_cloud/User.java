package application_temoin_cloud;

import core.models.modules.module_contacts.Contacts;
import core.models.modules.module_sessions.Utilisateur;

/**
 * Utilisateur - hérite de la classe Utilisateur du framework
 * @author cutroneg
 */
public class User extends Utilisateur {

	/**
	 * Constructeur
	 * @param login
	 * @param password
	 */
	public User(String login, String password) {
		super(login, password);
	}
	
	/**
	 * Permet de verifier la presence d'un utilisateur dans l'ArrayList listUsers
	 * Fonction de verification pour la gestion des utilisateurs
	 * @param login
	 * @param password
	 * @param listUsers ArrayList<Utilisateur>
	 * @return l'utilisateur courant si présent dans listUsers, null sinon
	 */
	public static boolean verification(String login, String password, Contacts<User> contacts){

		boolean connected = false;
		
		for (int i = 0; i < contacts.getContacts().size(); i++) {
			if (contacts.getContacts().get(i).getLogin().equals(login)
					&& contacts.getContacts().get(i).getPassword()
							.equals(encode(password))) {
				connected = true;
			}
		}
		
		return connected;
	}

}
