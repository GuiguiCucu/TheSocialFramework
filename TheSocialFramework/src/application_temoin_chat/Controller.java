package application_temoin_chat;

import java.io.IOException;
import java.util.ArrayList;

import application_temoin_chat.commandeClient.ConfirmConnexion;
import application_temoin_chat.commandeClient.ConfirmListUsers;
import application_temoin_chat.commandeClient.ConfirmSendMessage;
import application_temoin_cloud.commandeClient.ConfirmDeconnexion;
import core.controleur.SuperControleur;
import core.models.core_modele.Client;

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
	 * main
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
		try {
			setClient(new Client(ip, port, this));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	public void deconnexion() {
		this.getClient().getListeCommandes()
				.put("@confirm_demande_deconnexion", new application_temoin_chat.commandeClient.ConfirmDeconnexion());
		this.getClient().getMessage().envoiMessage("@demande_deconnexion");
	}
	
	public void send(String msg){
		this.getClient().getMessage().envoiMessage("@sendmessage");
		this.getClient().getMessage().envoiMessage(getPseudo());
		this.getClient().getMessage().envoiMessage(msg);
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public VuePseudo getVuePseudo() {
		return vuePseudo;
	}

	public void setVuePseudo(VuePseudo vuePseudo) {
		this.vuePseudo = vuePseudo;
	}

	public VueDiscussion getVueDiscussion() {
		return vueDiscussion;
	}

	public void setVueDiscussion(VueDiscussion vueDiscussion) {
		this.vueDiscussion = vueDiscussion;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public void alimenteDiscussion(String origine, String msg) {
		this.getVueDiscussion().alimenteFilDiscussion(origine, msg);
		
	}

	public void alimenteListeUtilisateurs(ArrayList<String> utilisateurs) {
		this.getVueDiscussion().alimenteUtilisateurs(utilisateurs);
		
	}
}
