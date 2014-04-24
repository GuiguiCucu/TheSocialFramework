package application_temoin_cloud;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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
public class Controller {

	private Contacts<User> users;
	private VueCloud vueCloud;
	private VueConnexion vueConnexion;
	private Client client;
	private String userName;
	private File currentDir;
	private File[] liste;

	/**
	 * Constructeur
	 */
	public Controller() {
		users = new Contacts<User>();
		users.ajouterPersonne(new User("Adele", "pwd"));
		users.ajouterPersonne(new User("Guillaume", "pwd"));
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
	 * Vérification du login et du mot de passe lors de l'iddentification d'un
	 * utilisateur
	 * 
	 * @param login
	 * @param pwd
	 * @throws IOException
	 */
	public void setCurrentUser(String login, String pwd) throws IOException {
		if (User.verification(login, pwd, users)) {
			setUserName(login);
			setClient(new Client("0.0.0.0", 2048));
			getClient().getListeCommandes().put("@oksendfile",
					new ConfirmReceptionFichier());
			runVueCloud();
			getVueConnexion().dispose();
			
			folder();
			list();
		} else
			JOptionPane.showMessageDialog(null,
					"Login ou mot de passe incorrect", "Erreur",
					JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Fonction d'upload du fichier choisi sur le serveur
	 * 
	 * @param fileName
	 */
	public void upload(String fileName) {
		System.out.println("IN");
		System.out.println("commande existante : "
				+ client.getListeCommandes().get("@oksendfile"));
		client.getMessage().envoiMessage("@sendfile");
		System.out.println("OUT");
	}

	/**
	 * crée un dossier pour l'utilisateur courant si il n'exite pas
	 */
	public void folder() {
		String repertoireCourant = System.getProperty("user.dir");
		
		setCurrentDir(new File(repertoireCourant+"/"+getUserName()));
		
		if(!getCurrentDir().exists()){
			getCurrentDir().mkdir();
		}
	}
	
	/**
	 * liste tous les fichiers dans le répertoire courant
	 */
	public void list(){

		setListe(getCurrentDir().listFiles());
		for (int i = 0; i < getListe().length; i++) {
//			if (listefichiers[i].isDirectory()) {
//				System.out.println("Dossier : " + listefichiers[i].getName());
//			} else if (listefichiers[i].isFile()) {
//				
				System.out.println("Fichier : " + getListe()[i].getName());
			//}
		}
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

	public File getCurrentDir() {
		return currentDir;
	}

	public void setCurrentDir(File currentDir) {
		this.currentDir = currentDir;
	}

	public File[] getListe() {
		return liste;
	}

	public void setListe(File[] liste) {
		this.liste = liste;
	}

}
