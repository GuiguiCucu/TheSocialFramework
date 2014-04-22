package core.models.modules.mail.tests;

import core.models.modules.mail.EnvoyerMail;

public class MailTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		EnvoyerMail.mail("theSocialFramework@laposte.net",
				"gcharlety@laposte.net", "theSocialFramework", "Miage2014",
				"SujetTest", "Corps  Tests éà");
	}
}
