package core.models.modules.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * EnvoyerMail est la classe permettant d'envoyer un mail Pour l'envoi du mail
 * il y a besoin des informations suivantes :
 * <ul>
 * <li>Une adresse mail avec laquelle on envoie le mail</li>
 * <li>Une adresse mail de destination</li>
 * <li>Un nom d'utilisateur</li>
 * <li>Le mot de passe de l'utilisateur</li>
 * <li>Le sujet du mail</li>
 * <li>Le corps du mail</li>
 * </ul>
 * 
 * @author imag-soft
 * @version 1.0
 */
public class EnvoyerMail {

	/**
	 * Appel de Envoyer mailTLS avec les bonnes informations de connexions
	 * 
	 * @param adresseEnvoi
	 *            L'adresse mail de l'emetteur
	 * @param adresseDestinataire
	 *            L'adresse mail du destinataire
	 * @param user
	 *            L'utilisateur rattach&eacute; &agrave; l'adresse mail destinataire
	 * @param pwd
	 *            Le mot de passe rattach&eacute; &agrave; l'utilisateur
	 * @param sujet
	 *            Le sujet du mail
	 * @param corps
	 *            Le corps du mail
	 */
	public static void mail(String adresseEnvoi, String adresseDestinataire,
			String user, String pwd, String sujet, String corps) {
		int index = 0;
		String service;
		adresseEnvoi.toLowerCase();
		index = adresseEnvoi.indexOf("@");
		service = adresseEnvoi.substring(index + 1);
		switch (service) {
		case "laposte.net":
			envoyerMailTls("smtp.laposte.net", user, pwd, adresseEnvoi,
					adresseDestinataire, sujet, corps);
			break;
		case "gmail.com":
			envoyerMailTls("smtp.gmail.com", user, pwd, adresseEnvoi,
					adresseDestinataire, sujet, corps);
			break;
		case "free.fr":
			envoyerMailTls("smtp.free.fr", user, pwd, adresseEnvoi,
					adresseDestinataire, sujet, corps);
			break;
		case "live.com":
			envoyerMailTls("smtp.live.com", user, pwd, adresseEnvoi,
					adresseDestinataire, sujet, corps);
			break;
		case "hotmail.com":
			envoyerMailTls("smtp.hotmail.com", user, pwd, adresseEnvoi,
					adresseDestinataire, sujet, corps);
			break;
		case "orange.fr":
			envoyerMailTls("smtp.orange.fr", user, pwd, adresseEnvoi,
					adresseDestinataire, sujet, corps);
			break;
		case "yahoo.fr":
			envoyerMailTls("smtp.yahoo.fr", user, pwd, adresseEnvoi,
					adresseDestinataire, sujet, corps);
			break;
		case "e.ujf-grenoble.fr":
			envoyerMailTls("smtps.ujf-grenoble.fr", user, pwd, adresseEnvoi,
					adresseDestinataire, sujet, corps);
		default:
			envoyerMailTls("smtp.laposte.net", "theSocialFramework",
					"Miage2014", "theSocialFramework@laposte.net",
					adresseDestinataire, sujet, corps);
		}

	}

	/**
	 * envoyerMailTls envoie le mail d'apr&egrave;s les informations de la
	 * m&eacute;thode mail
	 * 
	 * @param serveur
	 *            Adresse smtp du serveur
	 * @param user
	 *            Nom de l'utilisateur rattach&eacute; au client de messagerie
	 * @param pwd
	 *            Mot de passe rattach&eacute; au client de messagerie
	 * @param adMail
	 *            Adresse mail de l'&eacute;metteur
	 * @param destinataire
	 *            Adresse mail du destinataire
	 * @param sujet
	 *            Sujet du mail
	 * @param corps
	 *            Corps du mail
	 */
	public static void envoyerMailTls(String serveur, final String user,
			final String pwd, String adMail, String destinataire, String sujet,
			String corps) {

		Properties prop = new Properties();

		prop.put("mail.smtp.starttls.enable", "true");

		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.host", serveur);
		prop.setProperty("mail.smtp.user", user);
		prop.setProperty("mail.from", adMail);
		prop.setProperty("mail.smtp.auth", "true");
		prop.setProperty("mail.smtp.port", "587");
		prop.put("mail.smtp.password", pwd);

		Session session = Session.getInstance(prop,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(user, pwd);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(adMail));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(destinataire));
			message.setSubject(sujet);
			message.setText(corps);

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
