package sessions;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Utilisateur {

	private String login;
	private String password;

	/**
	 * Construction d'utilisateurs
	 * 
	 * @param login
	 * @param password
	 */
	public Utilisateur(String login, String password) {
		this.login = login;
		this.password = encode(password);
	}

	public String getPassword() {
		return password;
	}

	/**
	 * Accesseur du login
	 * 
	 * @return login
	 */
	public String getLogin() {
		return login;
	}
	
	/**
	 * Encode le String passé en paramètre en MD5
	 * 
	 * @param password
	 * @return
	 */
	private static String encode(String password) {
		byte[] uniqueKey = password.getBytes();
		byte[] hash = null;

		try {
			hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
		} catch (NoSuchAlgorithmException e) {
			throw new Error("No MD5 support in this VM.");
		}

		StringBuilder hashString = new StringBuilder();
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(hash[i]);
			if (hex.length() == 1) {
				hashString.append('0');
				hashString.append(hex.charAt(hex.length() - 1));
			} else
				hashString.append(hex.substring(hex.length() - 2));
		}
		return hashString.toString();
	}
	
	/**
	 * Permet de verifier la presence d'un utilisateur dans l'ArrayList listUsers
	 * Fonction de verification pour la gestion des utilisateurs
	 * @param login
	 * @param password
	 * @param listUsers ArrayList<Utilisateur>
	 * @return l'utilisateur courant si présent dans listUsers, null sinon
	 */
	public static Utilisateur verification(String login, String password, ArrayList<Utilisateur> listUsers){

		boolean connected = false;
		
		for (int i = 0; i < listUsers.size(); i++) {
			if (listUsers.get(i).getLogin().equals(login)
					&& listUsers.get(i).getPassword()
							.equals(encode(password))) {
				connected = true;
			}
		}
		
		if(connected)
			return new Utilisateur(login, password);
		else
			return null;
	}

}
