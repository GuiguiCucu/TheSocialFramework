package application_temoin_cloud;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import application_temoin_cloud.commandeClient.ConfirmConnexion;
import application_temoin_cloud.commandeClient.ConfirmDeconnexion;
import application_temoin_cloud.commandeClient.ConfirmReceptionContenuDossier;
import core.controleur.SuperControleur;
import core.models.core_modele.Client;
import core.models.core_modele.Message;
import core.models.core_modele.commandes.commandesClient.ConfirmReceptionFichier;
import core.models.modules.module_contacts.Contacts;

/**
 * Contrôleur permettant de gérer les interfaces graphiques
 * 
 * @author cutroneg
 * 
 */
public class Controller extends SuperControleur {

	private Contacts<User> users;
	private VueCloud vueCloud;
	private VueConnexion vueConnexion;
	private Client client;
	private String userName;

	/**
	 * Constructeur
	 */
	public Controller() {
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Controller c = new Controller();
		c.runVueConnexion();
	}

	/**
	 * Lancement de l'interface qui permet de s'identifier et accéder à
	 * l'interface des fonctionnalités
	 */
	public void runVueConnexion() {
		try {
			setVueConnexion(new VueConnexion(this));
			getVueConnexion().setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lancement de l'interface des fonctionnalités Cloud
	 */
	public void runVueCloud() {
		try {
			setVueCloud(new VueCloud(this));
			getVueCloud().setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initialisationClient() {
		try {
			setClient(new Client("0.0.0.0", 2048, this));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * FileChooser permettant de choisir le fichier à uploader sur le serveur
	 * 
	 * @throws Exception
	 */
	public void fileChooser() throws Exception {
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File f = chooser.getSelectedFile();
			// upload(f.getAbsolutePath());
		}
	}
	
	

	public void connexion(String login, String pwd) {
		initialisationClient();
		setUserName(login);
		this.getClient().getListeCommandes()
				.put("@confirmconnexion", new ConfirmConnexion());
		this.getClient().getMessage().envoiMessage("@demandeconnexion");
		this.getClient().getMessage().envoiMessage(login);
		this.getClient().getMessage().envoiMessage(pwd);
	}

	public void deconnexion() {
		this.getClient().getListeCommandes()
				.put("@confirm_demande_deconnexion", new ConfirmDeconnexion());
		this.getClient().getMessage().envoiMessage("@demande_deconnexion");
	}

	/**
	 * Fonction d'upload du fichier choisi sur le serveur
	 * 
	 * @param fileName
	 */
	public void upload(String fileName) {
		System.out.println("IN upload");
		getClient().getListeCommandes().put("@oksendfile",
				new ConfirmReceptionFichier());
		System.out.println("commande existante : "
				+ client.getListeCommandes().get("@oksendfile"));

		client.getMessage().envoiMessage("@sendfile");
		System.out.println("OUT");
	}

	public void initialiserContenuRepertoire() {
		System.out.println("IN init");
		this.getClient()
				.getListeCommandes()
				.put("@okafficherrepertoire",
						new ConfirmReceptionContenuDossier());
		this.getClient().getMessage().envoiMessage("@afficherrepertoire");
	}
	
	public void actualiserContenuRepertoire(String dossier) {
		System.out.println("IN actualisation");
		this.getClient()
				.getListeCommandes()
				.put("@okafficherrepertoire",
						new ConfirmReceptionContenuDossier());
		this.getClient().getMessage().envoiMessage("@afficherrepertoire");
	}	

	public void alimenteVueCloud(ArrayList<String> dossiers,
			HashMap<String, Long> fichiers) {
		this.getVueCloud().alimenteDocuments(dossiers, fichiers);
	}
	
	public void telechargement(String filename) {
		System.out.println("IN telechargement");
		System.out.println("commande existante : "
				+ client.getListeCommandes().get("@oktelechargement"));
		client.getMessage().envoiMessage("@demandetelechargement");
		System.out.println("OUT");
	}

	public VueConnexion getVueConnexion() {
		return vueConnexion;
	}

	public void setVueConnexion(VueConnexion vueConnexion) {
		this.vueConnexion = vueConnexion;
	}

	public VueCloud getVueCloud() {
		return vueCloud;
	}

	public void setVueCloud(VueCloud vueCloud) {
		this.vueCloud = vueCloud;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
