package core.models.modules.mail.tests;

import core.models.modules.mail.EnvoyerMail;

public class MailTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		EnvoyerMail.mail("Guillaume.Charlety@e.ujf-grenoble.fr",
				"gcharlety@laposte.net", "charletg", "password",
				"SujetTest", "Corps  Tests éà");
	}
}
