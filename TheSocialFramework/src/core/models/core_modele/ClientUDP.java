package core.models.core_modele;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;

import core.controleur.SuperControleur;
import core.models.core_modele.commandes.commandesClient.CommandeClient;

/**
 * Classe matérialisant le Client d'un serveur UDP sous forme de processus à part entière (Thread)
 * @author forestip
 *
 */
public class ClientUDP implements Runnable {
	private DatagramSocket socket;
	private InetAddress serv;
	private int port;
	private MessageUDP message;
	private Thread serveur = null;
	private HashMap<String, CommandeClient> listeCommandes;
	private String nomServeur;
	private String pseudoClient;
	private SuperControleur controleur;
	private boolean connect;
	private InetAddress ipAdresse;

	/**
	 * Constructeur
	 * 
	 * @param serverName
	 *            nom / IP du serveur
	 * @param numPort
	 *            port du serveur
	 * @throws IOException
	 */
	public ClientUDP(String serverName, int numPort, SuperControleur controleur) {
		this.setNomServeur(serverName);
		this.setPort(numPort);
		try {
			this.setIpAdresse(InetAddress.getByName(this.getNomServeur()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
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
			this.setSocket(new DatagramSocket());
			this.getSocket().connect(getIpAdresse(), getPort());
			
		} catch (SocketException e) {
			e.printStackTrace();
		}
		this.setMessage(new MessageUDP(this.getSocket()));
		this.setServeur(new Thread(this));
	}

	/**
	 * Fermeture de la connexion avec le serveur
	 */
	public void deconnexionServeur() {
		this.getSocket().close();
	}

	/**
	 * Redéfinition de la méthode run : Description des instructions du thread
	 * (écoute en boucle)
	 */
	@Override
	public void run() {
		//System.out.println("Connecté à " + this.getNomServeur());
		this.getMessage().envoiMessage("Hi!");
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
	public DatagramSocket getSocket() {
		return socket;
	}

	/**
	 * Mutateur de socket
	 * 
	 * @param socket
	 */
	public void setSocket(DatagramSocket socket) {
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
	public MessageUDP getMessage() {
		return message;
	}

	/**
	 * Mutateur de message
	 * 
	 * @param message
	 */
	public void setMessage(MessageUDP message) {
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

	/**
	 * Accesseur du controleur
	 * @return le controleur
	 */
	public SuperControleur getControleur() {
		return controleur;
	}

	/**
	 * Mutateur du controleur
	 * @param controleur
	 */
	public void setControleur(SuperControleur controleur) {
		this.controleur = controleur;
	}

	/**
	 * Booleen utilisé dans la boucle d'écoute du processus client
	 * @return l'état de la boucle d'écoute
	 */
	public boolean isConnect() {
		return connect;
	}

	/**
	 * Mutateur du booleen de connexion
	 * @param connect
	 */
	public void setConnect(boolean connect) {
		this.connect = connect;
	}

	/**
	 * Accesseur du booleen de connexion
	 * @return
	 */
	public InetAddress getIpAdresse() {
		return ipAdresse;
	}

	/**
	 * Accesseur de l'inetAdress du serveur auquel le client est connecté
	 * @param ipAdresse
	 */
	public void setIpAdresse(InetAddress ipAdresse) {
		this.ipAdresse = ipAdresse;
	}
	
	

}
