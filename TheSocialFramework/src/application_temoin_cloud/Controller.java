package application_temoin_cloud;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import application_temoin_cloud.commandeClient.ConfirmConnexion;
import application_temoin_cloud.commandeClient.ConfirmDeconnexion;
import application_temoin_cloud.commandeClient.ConfirmInscription;
import application_temoin_cloud.commandeClient.ConfirmReceptionContenuDossier;
import application_temoin_cloud.commandeClient.ConfirmTelechargement;
import application_temoin_cloud.commandeClient.ConfirmUpload;
import core.controleur.SuperControleur;
import core.models.core_modele.Client;

/**
 * Contrôleur permettant de gérer les interfaces graphiques
 * 
 * @author cutroneg
 * 
 */
public class Controller extends SuperControleur {

	private VueCloud vueCloud;
	private VueConnexion vueConnexion;
	private VueInscription vueInscription;
	private Client client = null;
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

	public void runVueInscription() {
		try {
			setVueInscription(new VueInscription(this));
			getVueInscription().setVisible(true);

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
			upload(f.getAbsolutePath());
		}
	}

	public void inscription(String login, String pwd, String pwdBis) {
		if (pwd.equals(pwdBis)) {
			this.getClient().getListeCommandes()
					.put("@confirm_inscription", new ConfirmInscription());
			this.getClient().getMessage().envoiMessage("@inscription");
			this.getClient().getMessage().envoiMessage(login);
			this.getClient().getMessage().envoiMessage(pwd);
		} else {
			JOptionPane.showMessageDialog(null,
					"Les mots de passe doivent être identiques", "Erreur",
					JOptionPane.ERROR_MESSAGE);
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
		System.out.println("AFTER");
	}

	/**
	 * Fonction d'upload du fichier choisi sur le serveur
	 * 
	 * @param fileName
	 */
	public void upload(String fileName) {
		getClient().getListeCommandes().put("@oksendfile", new ConfirmUpload());
		System.out.println("commande existante : "
				+ client.getListeCommandes().get("@oksendfile"));
		this.getClient().getMessage().envoiMessage("@sendfile");
		this.getClient().getMessage().envoiMessage(fileName);
		this.getVueCloud().majUpload(fileName);
	}

	public void initialiserContenuRepertoire() {
		this.getClient()
				.getListeCommandes()
				.put("@okafficherrepertoire",
						new ConfirmReceptionContenuDossier());
		this.getClient().getMessage().envoiMessage("@afficherrepertoire");
	}

	public void actualiserContenuRepertoire() {
		System.out.println("IN actualisation");
		this.getClient().getMessage().envoiMessage("@afficherrepertoire");
	}

	public void alimenteVueCloud(ArrayList<String> dossiers,
			HashMap<String, Long> fichiers) {
		this.getVueCloud().alimenteDocuments(dossiers, fichiers);
	}

	public void telechargement(String filename) {
		this.getClient().getListeCommandes()
				.put("@oktelechargement", new ConfirmTelechargement());
		client.getMessage().envoiMessage("@demandetelechargement");
		this.getClient().getMessage().envoiMessage(this.getUserName());
		client.getMessage().envoiMessage(filename);
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

	public VueInscription getVueInscription() {
		return vueInscription;
	}

	public void setVueInscription(VueInscription vueInscription) {
		this.vueInscription = vueInscription;
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
