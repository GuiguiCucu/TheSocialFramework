package core.models.modules.module_sessions;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import core.models.modules.module_contacts.Contacts;

/**
 * Classe abstraite qui permet la gestion des utilisateurs avec encodage
 * de mot de passe en MD5
 * La classe est comptatible avec la classe Contacts
 * @author cutroneg
 *
 */
public abstract class Utilisateur {

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
	protected static String encode(String password) {
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

}
