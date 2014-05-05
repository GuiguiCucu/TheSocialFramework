package core.models.core_modele;

import java.io.File;
import java.io.IOException;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import application_temoin_cloud.User;
import core.models.core_modele.commandes.commandesServeur.CommandeServeurUDP;
import core.models.modules.module_contacts.Contacts;

public class ServeurUDP {
	
	private ArrayList<TraitementClientUDP> traiteClients;
	private ArrayList<String> listeClients;
	private int port = 5010;
	private DatagramSocket socket;
	private HashMap<String, CommandeServeurUDP> listeCommandes;
	private File[] liste;
	private File currentDir;
	private Contacts<User> users;

	/**
	 * Constructeur
	 * 
	 * @param p
	 *            numéro de port
	 */
	public ServeurUDP(int p) {
		try {
			this.setPort(p);
			this.setSocket(new DatagramSocket(this.getPort()));
			this.setListeClients(new ArrayList<String>());
			System.out.println("IN");
			System.out.println(this.getListeClients().size());
			this.setTraiteClients(new ArrayList<TraitementClientUDP>());
			this.setListeCommandes(new HashMap<String, CommandeServeurUDP>());
			users = new Contacts<User>();
			users.ajouterPersonne(new User("Adele", "pwd"));
			users.ajouterPersonne(new User("Guillaume", "pwd"));

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Ce port à cette adresse est occupé");
			System.exit(0);
		}
	}

	/**
	 * 
	 * synchronized car partagée par plusieurs threads TraitementClient
	 * 
	 * @param tC
	 *            thread à qui souhaiter la bienvenue
	 */
	synchronized public void printWelcome(TraitementClientUDP tC) {
		System.out.println("Client connecté " + tC.getAdresseClient());
	}

	/**
	 * synchronized car partagée par plusieurs threads TraitementClient
	 * 
	 * @param tC
	 *            thread à supprimer
	 */
	synchronized public void delClient(TraitementClientUDP tC) {
		this.getTraiteClients().remove(tC);
	}

	/**
	 * Ajout d'un thread à la liste des processus s'éxécutant sur le serveur
	 * 
	 * @param newClient
	 *            thread à ajouter
	 * @return la nouvelle taille de la liste de thread éxécutés sur le serveur
	 */
	synchronized public int addClient(TraitementClientUDP newClient) {
		this.getTraiteClients().add(newClient); // on ajoute le nouveau flux de
												// sortie au tableau
		return this.getTraiteClients().size() - 1; // on retourne le numéro du
													// client ajouté (size-1)
	}

	// ////////////////////////////////////////////////////////

	/**
	 * Accesseur de port
	 * 
	 * @return port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * Mutateur de port
	 * 
	 * @param port
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * Accesseur du socket de communication
	 * @return le socket
	 */
	public DatagramSocket getSocket() {
		return socket;
	}

	/**
	 * Mutateur du socket de communication
	 * @param socket
	 */
	public void setSocket(DatagramSocket socket) {
		this.socket = socket;
	}

	/**
	 * Accesseur de traiteClients
	 * 
	 * @return traiteClients
	 */
	public ArrayList<TraitementClientUDP> getTraiteClients() {
		return traiteClients;
	}

	/**
	 * Mutateur de traiteCliens
	 * 
	 * @param traiteClients
	 */
	public void setTraiteClients(ArrayList<TraitementClientUDP> traiteClients) {
		this.traiteClients = traiteClients;
	}

	/**
	 * Accesseur de la liste des commandes
	 * 
	 * @return la liste des commandes
	 */
	public HashMap<String, CommandeServeurUDP> getListeCommandes() {
		return listeCommandes;
	}

	/**
	 * Mutateur de la liste de commande
	 * 
	 * @param listeCommandes
	 *            la liste de commande
	 */

	public void setListeCommandes(
			HashMap<String, CommandeServeurUDP> listeCommandes) {
		this.listeCommandes = listeCommandes;
	}

	/**
	 * liste tous les fichiers dans le répertoire courant
	 */
	public void list() {

		setListe(getCurrentDir().listFiles());
		for (int i = 0; i < getListe().length; i++) {
			// if (listefichiers[i].isDirectory()) {
			// System.out.println("Dossier : " + listefichiers[i].getName());
			// } else if (listefichiers[i].isFile()) {
			System.out.println("Fichier : " + getListe()[i].getName());
			// }
		}
	}

	public File[] getListe() {
		return liste;
	}

	public void setListe(File[] liste) {
		this.liste = liste;
	}

	public File getCurrentDir() {
		return currentDir;
	}

	public void setCurrentDir(File currentDir) {
		this.currentDir = currentDir;
	}

	public ArrayList<String> getListeClients() {
		return listeClients;
	}

	public void setListeClients(ArrayList<String> listeClients) {
		this.listeClients = listeClients;
	}

	public Contacts<User> getUsers() {
		return users;
	}

	public void setUsers(Contacts<User> users) {
		this.users = users;
	}

}
