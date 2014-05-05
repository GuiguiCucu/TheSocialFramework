package application_temoin_chat;

import java.io.IOException;
import java.util.ArrayList;

import application_temoin_chat.commandeClient.ConfirmConnexion;
import application_temoin_chat.commandeClient.ConfirmListUsers;
import application_temoin_chat.commandeClient.ConfirmSendMessage;
import application_temoin_cloud.commandeClient.ConfirmDeconnexion;
import core.controleur.SuperControleur;
import core.models.core_modele.Client;

/**
 * Controller de l'application de chat
 * @author cutroneg
 *
 */
public class Controller extends SuperControleur{
	
	private Client client;
	private VuePseudo vuePseudo;
	private VueDiscussion vueDiscussion;
	private String pseudo;

	/**
	 * Constructeur
	 */
	public Controller() {
	}

	/**
	 * main - lancement d'un client
	 * @param args
	 */
	public static void main(String[] args) {
		Controller c = new Controller();
		c.runVuePseudo();
	}
	
	/**
	 * Lancement vue pour saisir le pseudo
	 */
	public void runVuePseudo() {
		try {
			setVuePseudo(new VuePseudo(this));
			getVuePseudo().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Lancement vue de discussion
	 */
	public void runVueDiscussion() {
		try {
			setVueDiscussion(new VueDiscussion(this));
			getVueDiscussion().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialisation du client
	 */
	public void initialisationClient(String ip, int port) {
			setClient(new Client(ip, port, this));
	}
	
	/**
	 * Connexion du client au serveur
	 * @param pseudo
	 */
	public void connexion(String pseudo, String ip, String port) {
		initialisationClient(ip, Integer.valueOf(port));
		setPseudo(pseudo);
		System.out.println("in connexion");
		this.getClient().getListeCommandes()
				.put("@confirmconnexion", new ConfirmConnexion());
		this.getClient().getListeCommandes().put("@confirmsendmessage", new ConfirmSendMessage());
		this.getClient().getListeCommandes().put("@confirmlisteusers", new ConfirmListUsers());
		this.getClient().getMessage().envoiMessage("@demandeconnexion");
		this.getClient().getMessage().envoiMessage(pseudo);
	}
	
	/**
	 * Déconnexion du client
	 */
	public void deconnexion() {
		this.getClient().getListeCommandes()
				.put("@confirm_demande_deconnexion", new application_temoin_chat.commandeClient.ConfirmDeconnexion());
		this.getClient().getMessage().envoiMessage("@demande_deconnexion");
	}
	
	/**
	 * Envoi d'un message texte depuis le client
	 * @param msg
	 */
	public void send(String msg){
		this.getClient().getMessage().envoiMessage("@sendmessage");
		this.getClient().getMessage().envoiMessage(getPseudo());
		this.getClient().getMessage().envoiMessage(msg);
	}

	/**
	 * Accesseur du client
	 * @return
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * Mutateur du client
	 * @param client
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * Accesseur de la vuePseudo
	 * @return vuePseudo
	 */
	public VuePseudo getVuePseudo() {
		return vuePseudo;
	}

	/**
	 * Mutateur de la vuePseudo
	 * @param vuePseudo
	 */
	public void setVuePseudo(VuePseudo vuePseudo) {
		this.vuePseudo = vuePseudo;
	}

	/**
	 * Accesseur de la vue de discussion
	 * @return vueDiscussion
	 */
	public VueDiscussion getVueDiscussion() {
		return vueDiscussion;
	}

	/**
	 * Mutateur de la vue de discussion
	 * @param vueDiscussion
	 */
	public void setVueDiscussion(VueDiscussion vueDiscussion) {
		this.vueDiscussion = vueDiscussion;
	}

	/**
	 * Accesseur du pseudo du client
	 * @return
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * Mutateur du pseudo du client
	 * @param pseudo
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	/**
	 * Alimente la fenetre de discussion avec le messages envoyé un client
	 * Prend en paramètres le nom du client à l'origine du message et le corps du message
	 * @param origine
	 * @param msg
	 */
	public void alimenteDiscussion(String origine, String msg) {
		this.getVueDiscussion().alimenteFilDiscussion(origine, msg);
		
	}

	/**
	 * Met à jour la liste des utilisateurs connectés au serveur
	 * @param utilisateurs
	 */
	public void alimenteListeUtilisateurs(ArrayList<String> utilisateurs) {
		this.getVueDiscussion().alimenteUtilisateurs(utilisateurs);
		
	}
}
