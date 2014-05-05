package core.models.core_modele.exceptions;

/**
 * 
 * Classe d'exceptions personnalisées permettant au développeur de personnaliser
 * les exceptions Permet aussi au framework de fournir des exceptions plus
 * spécifiques et précises
 * 
 * @author forestip
 * 
 */
public class ServeurException extends Exception {

	public ServeurException() {
		super();
	}

	/**
	 * Constructeur
	 * 
	 * @param s
	 *            Chaine de caractère identifiant l'exception
	 */
	public ServeurException(String s) {
		super(s);
	}
}
