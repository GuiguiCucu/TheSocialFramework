package core.models.core_modele.exceptions;

/**
 * Classe d'exceptions personnalisées permettant au développeur de personnaliser
 * les exceptions Permet aussi au framework de fournir des exceptions plus
 * spécifiques et précises
 * 
 * @author forestip
 * 
 */
public class ClientException extends Exception {

	/*
	 * Constructeur
	 */
	public ClientException() {
		super();
	}

	/**
	 * Constructeur
	 * 
	 * @param s
	 *            Chaine de caractère identifiant l'exception
	 */
	public ClientException(String s) {
		super(s);
	}
}
