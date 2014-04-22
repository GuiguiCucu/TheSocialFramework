package core.models.modules.client_serveur;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import core.models.modules.client_serveur.commandes.Commande;

/**
 * Classe matérialisant le client sous forme de thread
 * 
 * @author forestip
 * 
 */
public class Client implements Runnable {
	private Socket socket;
	private InetAddress serv;
	private int port;
	private Message message;
	private Thread serveur = null;
	private HashMap<String, Commande> listeCommandes;
	private String nomServeur;
	private String pseudoClient;

	/*
	 * public static void main(String[] args) throws IOException { String
	 * nameServer = new String("0.0.0.0"); int numPort = 7846; new
	 * Client(nameServer, numPort); }
	 */

	/**
	 * Constructeur
	 * 
	 * @param serverName
	 *            nom / IP du serveur
	 * @param numPort
	 *            port du serveur
	 * @throws IOException
	 */
	public Client(String serverName, int numPort) throws IOException {
		this.setNomServeur(serverName);
		this.setPort(numPort);
		this.connexionServeur();
		this.getServeur().start();
		this.setListeCommandes(new HashMap<String, Commande>());
		/*
		 * this.getMessage().envoiMessage("@sendfile");
		 * System.out.println("Envoi fichier...");
		 * this.getMessage().envoiFichier();
		 */
	}

	/**
	 * Etablissement d'une connexion avec le serveur identifié par numéro de
	 * port et IP
	 */
	public void connexionServeur() {
		try {
			this.setSocket(new Socket(this.getNomServeur(), this.getPort()));
			this.setMessage(new Message(this.getSocket()));
			this.setServeur(new Thread(this));
		} catch (UnknownHostException ex) {
			Logger.getLogger(Client.class.getName())
					.log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			JOptionPane
					.showMessageDialog(new JFrame(),
							"Le serveur est inactif - Merci de réessayer ultérieurement");
			System.exit(0);
		}
	}

	/**
	 * Fermeture de la connexion avec le serveur
	 */
	public void deconnexionServeur() {
		try {
			// Thread + appel
			this.getMessage().getEntree().close();
			this.getMessage().getSortie().close();
			this.getSocket().close();

		} catch (IOException ex) {
			Logger.getLogger(Client.class.getName())
					.log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Redéfinition de la méthode run : Description des instructions du thread
	 * (écoute en boucle)
	 */
	@Override
	public void run() {

		System.out.println("Connecté à " + this.getNomServeur());
		boolean connect = true;
		while (connect) {
			String recu = "";
			try {
				recu = this.getMessage().receptionMessage();
				Commande cmd = 	this.getListeCommandes().get(recu);
				cmd.execute(this.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/* GETTERS & SETTERS */

	/**
	 * Accesseur de socket
	 * 
	 * @return socket
	 */
	public Socket getSocket() {
		return socket;
	}

	/**
	 * Mutateur de socket
	 * 
	 * @param socket
	 */
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	/**
	 * Accesseur d'inetAdresse
	 * 
	 * @return inetAddress
	 */
	public InetAddress getServ() {
		return serv;
	}

	/**
	 * Mutateur d'inetAddress
	 * 
	 * @param serv
	 */
	public void setServ(InetAddress serv) {
		this.serv = serv;
	}

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
	 * Accesseur de Message
	 * 
	 * @return message
	 */
	public Message getMessage() {
		return message;
	}

	/**
	 * Mutateur de message
	 * 
	 * @param message
	 */
	public void setMessage(Message message) {
		this.message = message;
	}

	/**
	 * Accesseur de Serveur
	 * 
	 * @return serveur
	 */
	public Thread getServeur() {
		return serveur;
	}

	/**
	 * Mutateur de Serveur
	 * 
	 * @param serveur
	 */
	public void setServeur(Thread serveur) {
		this.serveur = serveur;
	}

	/**
	 * Accesseur de nom de serveur
	 * 
	 * @return nomServeur
	 */
	public String getNomServeur() {
		return nomServeur;
	}

	/**
	 * Mutateur de nom de serveur
	 * 
	 * @param nomServeur
	 */
	public void setNomServeur(String nomServeur) {
		this.nomServeur = nomServeur;
	}

	/**
	 * Accesseur de pseudo client
	 * 
	 * @return pseudoClient
	 */
	public String getPseudoClient() {
		return pseudoClient;
	}

	/**
	 * Mutateur de pseudoClient
	 * 
	 * @param pseudoClient
	 */
	public void setPseudoClient(String pseudoClient) {
		this.pseudoClient = pseudoClient;
	}

	/**
	 * Accesseur de la liste des commandes
	 * @return la liste des commandes
	 */
	public HashMap<String, Commande> getListeCommandes() {
		return listeCommandes;
	}

	/**
	 * Mutateur de la liste de commande
	 * @param listeCommandes la liste de commande
	 */
	public void setListeCommandes(HashMap<String, Commande> listeCommandes) {
		this.listeCommandes = listeCommandes;
	}

}
