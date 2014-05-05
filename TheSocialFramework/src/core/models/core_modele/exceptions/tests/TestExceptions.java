package core.models.core_modele.exceptions.tests;

import core.models.core_modele.exceptions.ServeurException;

public class TestExceptions {

	public static void main(String[] args) {
		try{
			serveur();
		}
		catch(ServeurException ex){
			ex.printStackTrace();
		}
	}
	/**
	 * Exemple de levée d'excpetion
	 * @throws ServeurException
	 */
	public static void serveur() throws ServeurException{
		throw new ServeurException("bonjour");
	}

}
