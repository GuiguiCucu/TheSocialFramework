package contacts;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class Personne {

	private String nom;
	private String prenom;
	private String adresse;
	private final static String MAILER_VERSION = "Java";

	public Personne(String nom, String prenom, String adresse) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public void envoyerMail(String destinataire, String sujet, String corps) {
		final String user = "theSocialFramework";
		final String pwd = "Miage2014";
		final String adMail = "theSocialFramework@laposte.net";
		final String MAILER_VERSION = "Java";
		final String serveur = "smtp.laposte.net";
		final int port = 465;
		try {
			Properties prop = System.getProperties();
			prop.put("smtp.laposte.host", serveur);
			prop.put("smtp.laposte.auth", "true");
			prop.put("smtp.laposte.port", port);
			Session session = Session.getInstance(prop);
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(adMail));
			InternetAddress[] internetAddresses = new InternetAddress[1];
			internetAddresses[0] = new InternetAddress(destinataire);
			message.setRecipients(Message.RecipientType.TO, internetAddresses);
			message.setSubject(sujet);
			message.setText(corps);
			message.setHeader("X-Mailer", MAILER_VERSION);
			message.setSentDate(new Date());
			System.out
					.println(("Essai de Mail a " + destinataire + " de : " + adMail));
			Transport transport = session.getTransport("smtp");
			transport.connect(serveur, port, user, pwd);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}
