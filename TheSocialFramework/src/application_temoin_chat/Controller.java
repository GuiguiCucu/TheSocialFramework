package application_temoin_chat;

import java.io.IOException;

import core.controleur.SuperControleur;
import core.models.core_modele.Client;

public class Controller extends SuperControleur{
	
	private Client client;
	private VuePseudo vuePseudo;
	private VueDiscussion vueDiscussion;

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
			setVueDiscussion(new VueDiscussion());
			getVueDiscussion().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialisation du client
	 */
	public void initialisationClient() {
		try {
			setClient(new Client("0.0.0.0", 2048, this));
		} catch (IOException e) {
			e.printStackTrace();
		}
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

}
