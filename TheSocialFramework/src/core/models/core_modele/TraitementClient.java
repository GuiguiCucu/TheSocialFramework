package core.models.core_modele;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

import core.models.core_modele.commandes.commandesServeur.CommandeServeur;

/**
 * Thread représentant la connexion d'un client au serveur TCP
 * 
 * @author forestip
 */
public class TraitementClient implements Runnable {

	private Socket socketDeTransfert = null;
	private Thread client = null;
	private Serveur serv = null;
	private Message message;
	private InetAddress adresseClient;
	private String nomClient;
	private File currentDir;
	private boolean connect;

	/**
	 * 
	 * @param socketTransfert
	 *            Socket de transfert propre au serveur
	 * @param serv
	 *            Serveur sur lequel est rattaché le thread
	 */
	public TraitementClient(Socket socketTransfert, Serveur serv) {
		this.setSocketDeTransfert(socketTransfert);
		this.setServ(serv);
		this.getServ().addClient(this);
		this.setMessage(new Message(this.getSocketDeTransfert()));
		this.setAdresseClient(this.getSocketDeTransfert().getInetAddress());
		this.setNomClient("");
		this.setConnect(true);
		this.setClient(new Thread(this));
		this.getClient().start();

	}

	/**
	 * Redéfinition de la méthode run : description des instructions du thread
	 * (écoute en boucle)
	 */
	public void run() {
		this.getServ().printWelcome((this));

		while (isConnect()) {
			String envoi = "";
			envoi = this.getMessage().receptionMessage();
			CommandeServeur cmd = this.getServ().getListeCommandes().get(envoi);
			if (cmd != null) {
				cmd.execute(this);
			} else {
				this.getMessage().envoiMessage(envoi.toUpperCase());
			}

		}
	}

	/**
	 * crée un dossier pour l'utilisateur courant si il n'exite pas
	 */
	public void folder() {
		String repertoireCourant = System.getProperty("user.dir");
		setCurrentDir(new File(repertoireCourant + "/" + getNomClient()));
		if (!getCurrentDir().exists()) {
			getCurrentDir().mkdir();
		}
	}

	/**
	 * Accesseur de socketDeTransfert
	 * 
	 * @return socketDeTransfert
	 */
	public Socket getSocketDeTransfert() {
		return socketDeTransfert;
	}

	/**
	 * Mutateur de socketDeTransfert
	 * 
	 * @param socketDeTransfert
	 */
	public void setSocketDeTransfert(Socket socketDeTransfert) {
		this.socketDeTransfert = socketDeTransfert;
	}

	/**
	 * Accesseur de thread client
	 * 
	 * @return client
	 */
	public Thread getClient() {
		return client;
	}

	/**
	 * Mutateur de thread client
	 * 
	 * @param client
	 */
	public void setClient(Thread client) {
		this.client = client;
	}

	/**
	 * Accesseur de serveur
	 * 
	 * @return serv
	 */
	public Serveur getServ() {
		return serv;
	}

	/**
	 * Mutateur de serveur
	 * 
	 * @param serv
	 */
	public void setServ(Serveur serv) {
		this.serv = serv;
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
	 * Mutateur de Message
	 * 
	 * @param message
	 */
	public void setMessage(Message message) {
		this.message = message;
	}

	/**
	 * Accesseur d'adresse client
	 * 
	 * @return adresseClient
	 */
	public InetAddress getAdresseClient() {
		return adresseClient;
	}

	/**
	 * Mutateur d'adresse client
	 * 
	 * @param adresseClient
	 */
	public void setAdresseClient(InetAddress adresseClient) {
		this.adresseClient = adresseClient;
	}

	/**
	 * Accesseur de nom client
	 * 
	 * @return nomClient
	 */
	public String getNomClient() {
		return nomClient;
	}

	/**
	 * Mutateur de nom client
	 * 
	 * @param nomClient
	 */
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	
	/**
	 * Accesseur du répertoire courant du client identifié par le processus
	 * courant
	 * 
	 * @return Le répertoire courant du client identifié par le processus
	 *         courant
	 */
	public File getCurrentDir() {
		return currentDir;
	}

	
	/**
	 * Mutateur du répertoire courant du client identifié par le processus
	 * courant
	 * 
	 * @param currentDir
	 *            Le répertoire courant du client identifié par le processus
	 *            courant
	 */
	public void setCurrentDir(File currentDir) {
		this.currentDir = currentDir;
	}

	
	/**
	 * Booleen utilisé dans la boucle d'écoute du processus client sur le serveur UDP
	 * @return l'état de la boucle d'écoute
	 */
	public boolean isConnect() {
		return connect;
	}

	/**
	 * Mutateur du boolean de la boucle d'écoute du processus sur le serveur TCP
	 * @param connect
	 */
	public void setConnect(boolean connect) {
		this.connect = connect;
	}

}
