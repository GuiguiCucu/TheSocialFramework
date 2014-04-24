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
import core.models.modules.module_contacts.Contacts;

public class Controller {

	private Contacts<User> users;
	private VueCloud vueCloud;
	private VueConnexion vueConnexion;
	private Client client;

	public Controller() {
		users = new Contacts<User>();
		users.ajouterPersonne(new User("Adele", "pwd"));
		users.ajouterPersonne(new User("Guillaume", "pwd"));
	}

	public static void main(String[] args) {
		Controller c = new Controller();
		c.runVueConnexion();
	}

	public void runVueConnexion() {
		try {
			setVueConnexion(new VueConnexion(this));
			getVueConnexion().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void runVueCloud() {
		try {
			setVueCloud(new VueCloud(this));
			getVueCloud().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fileChooser() throws Exception {
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File f = chooser.getSelectedFile();
			upload(f.getAbsolutePath());
		}
	}

	public void setCurrentUser(String login, String pwd) throws IOException {
		if (User.verification(login, pwd, users)) {
			setClient(new Client("0.0.0.0", 2048));
			runVueCloud();
			getVueConnexion().dispose();
		} else
			JOptionPane.showMessageDialog(null,
					"Login ou mot de passe incorrect", "Erreur",
					JOptionPane.ERROR_MESSAGE);
	}
	
	public void upload(String fileName){
		//getClient().getMessage().envoiMessage("@sendfile");
		try {
			getClient().getMessage().envoiFichier2();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
}
