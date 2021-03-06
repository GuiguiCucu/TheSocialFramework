package core.models.modules.module_sessions.tests;

import core.models.modules.module_contacts.Contacts;
import core.models.modules.module_sessions.Utilisateur;

public class User1 extends Utilisateur {

	public User1(String login, String password) {
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
	public static boolean verification(String login, String password, Contacts<Utilisateur> contacts){

		boolean connected = false;
		
		for (int i = 0; i < contacts.getContacts().size(); i++) {
			if (contacts.getContacts().get(i).getLogin().equals(login)
					&& contacts.getContacts().get(i).getPassword()
							.equals(Utilisateur.encode(password))) {
				connected = true;
			}
		}
		
		return connected;
	}

}
