package core.models.core_modele;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import core.controleur.SuperControleur;
import core.models.core_modele.commandes.commandesClient.CommandeClient;

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
	private HashMap<String, CommandeClient> listeCommandes;
	private String nomServeur;
	private String pseudoClient;
	private SuperControleur controleur;
	private boolean connect;

	/**
	 * Constructeur
	 * 
	 * @param serverName
	 *            nom / IP du serveur
	 * @param numPort
	 *            port du serveur
	 * @throws IOException
	 */
	public Client(String serverName, int numPort, SuperControleur controleur) {
		this.setNomServeur(serverName);
		this.setPort(numPort);
		this.setControleur(controleur);
		this.setConnect(true);
		this.connexionServeur();
		this.getServeur().start();
		this.setListeCommandes(new HashMap<String, CommandeClient>());
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
			this.getMessage().getEntree().close();
			this.getMessage().getSortie().close();
			this.getSocket().close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Redéfinition de la méthode run : Description des instructions du thread
	 * (écoute en boucle)
	 */
	@Override
	public void run() {

		//System.out.println("Connecté à " + this.getNomServeur());
		while (isConnect()) {
			String recu = "";
				recu = this.getMessage().receptionMessage();
				CommandeClient cmd = this.getListeCommandes().get(recu);
				if (cmd != null) {
					cmd.execute(this.getControleur());
				} else {
					//System.out.println("RECU : " + recu);
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
	 * 
	 * @return la liste des commandes
	 */
	public HashMap<String, CommandeClient> getListeCommandes() {
		return listeCommandes;
	}

	/**
	 * Mutateur de la liste de commande
	 * 
	 * @param listeCommandes
	 *            la liste de commande
	 */
	public void setListeCommandes(HashMap<String, CommandeClient> listeCommandes) {
		this.listeCommandes = listeCommandes;
	}

	public SuperControleur getControleur() {
		return controleur;
	}

	public void setControleur(SuperControleur controleur) {
		this.controleur = controleur;
	}

	public boolean isConnect() {
		return connect;
	}

	public void setConnect(boolean connect) {
		this.connect = connect;
	}

}
