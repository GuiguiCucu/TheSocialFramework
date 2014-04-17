package mail.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnvoyerMail {
	public static void envoyerMail(String destinataire, String sujet,
			String corps) {
		final String user = "theSocialFramework";
		final String pwd = "Miage2014";
		final String adMail = "theSocialFramework@laposte.net";
		final String serveur = "smtp.laposte.net";
		final int port = 587;
		try {
			Properties prop = new Properties();
			prop.setProperty("mail.transport.protocol", "smtp");
			prop.setProperty("mail.smtp.host", serveur);
			prop.setProperty("mail.smtp.user", user);
			prop.setProperty("mail.from", adMail);
			prop.setProperty("mail.smtp.auth", "true");
			prop.put("mail.smtp.password", pwd);

			Session session = Session.getInstance(prop,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(user, pwd);
						}
					});
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(adMail));
			message.setRecipients(Message.RecipientType.TO, destinataire);
			message.setSubject(sujet);
			message.setText(corps);
			message.setSentDate(new Date());

			Transport transport = session.getTransport("smtp");
			transport.connect(serveur, port, user, pwd);
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}
