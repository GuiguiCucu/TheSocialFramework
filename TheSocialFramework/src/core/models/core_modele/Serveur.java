package core.models.core_modele;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import application_temoin_cloud.User;
import core.models.core_modele.commandes.commandesServeur.CommandeServeur;
import core.models.modules.module_contacts.Contacts;

/**
 * Objet simulant le serveur
 * 
 * @author forestip
 */
public class Serveur {

	private ArrayList<TraitementClient> traiteClients;
	private Vector<String> listeClients;
	private int port = 5010;
	private ServerSocket socketEcoute;
	private Socket socketTransfert;
	private HashMap<String, CommandeServeur> listeCommandes;

	private File[] liste;
	private File currentDir;

	private Contacts<User> users;

	/**
	 * Constructeur
	 * 
	 * @param p
	 *            numéro de port
	 */
	public Serveur(int p) {
		try {
			this.setTraiteClients(new ArrayList<TraitementClient>());
			this.setPort(p);
			this.setSocketEcoute(new ServerSocket(this.getPort()));
			this.setListeCommandes(new HashMap<String, CommandeServeur>());

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
	synchronized public void printWelcome(TraitementClient tC) {
		System.out.println("Client connecté " + tC.getAdresseClient());
	}

	/**
	 * synchronized car partagé par plusieurs threads TraitementClient
	 * 
	 * @param tC
	 *            thread à supprimer
	 */
	synchronized public void delClient(TraitementClient tC) {
		this.getTraiteClients().remove(tC);
	}

	/**
	 * Ajout d'un thread à la liste des processus s'éxécutant sur le serveur
	 * 
	 * @param newClient
	 *            thread à ajouter
	 * @return la nouvelle taille de la liste de thread éxécutés sur le serveur
	 */
	synchronized public int addClient(TraitementClient newClient) {
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
	 * Accesseur de socketEcoute
	 * 
	 * @return socketEcoute
	 */
	public ServerSocket getSocketEcoute() {
		return socketEcoute;
	}

	/**
	 * Mutateur de socketEcoute
	 * 
	 * @param socketEcoute
	 */
	public void setSocketEcoute(ServerSocket socketEcoute) {
		this.socketEcoute = socketEcoute;
	}

	/**
	 * Accesseur de socketTransfert
	 * 
	 * @return socketTransfert
	 */
	public Socket getSocketTransfert() {
		return socketTransfert;
	}

	/**
	 * Mutateur socketTransfert
	 * 
	 * @param socketTransfert
	 */
	public void setSocketTransfert(Socket socketTransfert) {
		this.socketTransfert = socketTransfert;
	}

	/**
	 * Accesseur de traiteClients
	 * 
	 * @return traiteClients
	 */
	public ArrayList<TraitementClient> getTraiteClients() {
		return traiteClients;
	}

	/**
	 * Mutateur de traiteCliens
	 * 
	 * @param traiteClients
	 */
	public void setTraiteClients(ArrayList<TraitementClient> traiteClients) {
		this.traiteClients = traiteClients;
	}

	/**
	 * Accesseur de la liste des commandes
	 * 
	 * @return la liste des commandes
	 */
	public HashMap<String, CommandeServeur> getListeCommandes() {
		return listeCommandes;
	}

	/**
	 * Mutateur de la liste de commande
	 * 
	 * @param listeCommandes
	 *            la liste de commande
	 */

	public void setListeCommandes(
			HashMap<String, CommandeServeur> listeCommandes) {
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

	public Vector<String> getListeClients() {
		return listeClients;
	}

	public void setListeClients(Vector<String> listeClients) {
		this.listeClients = listeClients;
	}

	public Contacts<User> getUsers() {
		return users;
	}

	public void setUsers(Contacts<User> users) {
		this.users = users;
	}

}
