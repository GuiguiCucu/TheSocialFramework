package core.models.modules.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnvoyerMail {

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

	/*
	 * public static void envoyerMail(String serveur, final String user, final
	 * String pwd, String adMail, String destinataire, String sujet, String
	 * corps) {
	 * 
	 * try { Properties prop = new Properties();
	 * prop.setProperty("mail.transport.protocol", "smtp");
	 * prop.setProperty("mail.smtp.host", serveur);
	 * prop.setProperty("mail.smtp.user", user); prop.setProperty("mail.from",
	 * adMail); prop.setProperty("mail.smtp.auth", "true");
	 * prop.setProperty("mail.smtp.port", "587"); prop.put("mail.smtp.password",
	 * pwd);
	 * 
	 * Session session = Session.getInstance(prop, new
	 * javax.mail.Authenticator() { protected PasswordAuthentication
	 * getPasswordAuthentication() { return new PasswordAuthentication(user,
	 * pwd); } }); MimeMessage message = new MimeMessage(session);
	 * message.setFrom(new InternetAddress(adMail));
	 * message.setRecipients(Message.RecipientType.TO, destinataire);
	 * message.setSubject(sujet); message.setText(corps);
	 * message.setSentDate(new Date());
	 * 
	 * Transport.send(message); } catch (AddressException e) {
	 * e.printStackTrace(); } catch (MessagingException e) {
	 * e.printStackTrace(); }
	 * 
	 * }
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
