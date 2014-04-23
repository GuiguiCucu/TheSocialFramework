package application_temoin_cloud;

import java.io.IOException;

import javax.swing.JOptionPane;

import core.controleur.SuperControleur;
import core.models.core_modele.Client;
import core.models.modules.module_contacts.Contacts;

public class Controller {

	private Contacts<User> users;
	private VueCloud vueCloud;
	private VueConnexion vueConnexion;

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
	
	public void setCurrentUser(String login, String pwd){
		if(User.verification(login, pwd, users)){
			runVueCloud();
			getVueConnexion().dispose();
		}
		else
			JOptionPane.showMessageDialog(null, "Login ou mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE); 
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
}
