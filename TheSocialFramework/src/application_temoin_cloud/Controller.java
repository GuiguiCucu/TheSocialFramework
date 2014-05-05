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
	 * main - lancement d'un client
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

	/**
	 * Lancement de la vue d'inscription
	 */
	public void runVueInscription() {
		try {
			setVueInscription(new VueInscription(this));
			getVueInscription().setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialisation d'un nouveau client
	 */
	public void initialisationClient() {
		setClient(new Client("0.0.0.0", 2048, this));

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

	/**
	 * Inscription d'un nouveau client
	 * @param login
	 * @param pwd
	 * @param pwdBis
	 */
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

	/**
	 * Connexion d'un client au serveur
	 * @param login
	 * @param pwd
	 */
	public void connexion(String login, String pwd) {
		initialisationClient();
		setUserName(login);
		this.getClient().getListeCommandes()
				.put("@confirmconnexion", new ConfirmConnexion());
		this.getClient().getMessage().envoiMessage("@demandeconnexion");
		this.getClient().getMessage().envoiMessage(login);
		this.getClient().getMessage().envoiMessage(pwd);
	}

	/**
	 * Déconnexion d'un client du serveur
	 */
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
		getClient().getListeCommandes().put("@oksendfile", new ConfirmUpload());
		System.out.println("commande existante : "
				+ client.getListeCommandes().get("@oksendfile"));
		this.getClient().getMessage().envoiMessage("@sendfile");
		this.getClient().getMessage().envoiMessage(fileName);
		this.getVueCloud().majUpload(fileName);
	}

	/**
	 * Permet d'afficher une permière fois le contenu du repertoire de l'utilisateur sur le serveur
	 */
	public void initialiserContenuRepertoire() {
		this.getClient()
				.getListeCommandes()
				.put("@okafficherrepertoire",
						new ConfirmReceptionContenuDossier());
		this.getClient().getMessage().envoiMessage("@afficherrepertoire");
	}

	/**
	 * Mise a jour de l'affichage du contenu de repertoire de l'utilisateur
	 */
	public void actualiserContenuRepertoire() {
		System.out.println("IN actualisation");
		this.getClient().getMessage().envoiMessage("@afficherrepertoire");
	}

	/**
	 * Affichage du contenu dans la vue
	 * @param dossiers
	 * @param fichiers
	 */
	public void alimenteVueCloud(ArrayList<String> dossiers,
			HashMap<String, Long> fichiers) {
		this.getVueCloud().alimenteDocuments(dossiers, fichiers);
	}

	/**
	 * Telechargement d'un fichier depuis le serveur
	 * @param filename
	 */
	public void telechargement(String filename) {
		this.getClient().getListeCommandes()
				.put("@oktelechargement", new ConfirmTelechargement());
		client.getMessage().envoiMessage("@demandetelechargement");
		this.getClient().getMessage().envoiMessage(this.getUserName());
		client.getMessage().envoiMessage(filename);
	}

	/**
	 * Accesseur de la vueConnexion
	 * @return vueConnexion
	 */
	public VueConnexion getVueConnexion() {
		return vueConnexion;
	}

	/**
	 * Mutateur de la vueConnexion
	 * @param vueConnexion
	 */
	public void setVueConnexion(VueConnexion vueConnexion) {
		this.vueConnexion = vueConnexion;
	}

	/**
	 * Accesseur de la vueCloud
	 * @return vueCloud
	 */
	public VueCloud getVueCloud() {
		return vueCloud;
	}

	/**
	 * Mutateur de la vueCloud
	 * @param vueCloud
	 */
	public void setVueCloud(VueCloud vueCloud) {
		this.vueCloud = vueCloud;
	}

	/**
	 * Accesseur de la vueInscription
	 * @return vueInscription
	 */
	public VueInscription getVueInscription() {
		return vueInscription;
	}

	/**
	 * Mutateur de la vueInscription
	 * @param vueInscription
	 */
	public void setVueInscription(VueInscription vueInscription) {
		this.vueInscription = vueInscription;
	}

	/**
	 * Accesseur du client
	 * @return client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * Mutateur de client
	 * @param client
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * Accesseur d'username
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Mutateur d'userName
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
