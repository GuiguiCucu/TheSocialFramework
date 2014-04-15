package sessions;

public class Utilisateur {
	
	private String login;
	private String password;

	/**
	 * Construction d'utilisateurs
	 * @param login
	 * @param password
	 */
	public Utilisateur(String login, String password) {
		this.login = login;
		// TODO conversion en MD5
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
