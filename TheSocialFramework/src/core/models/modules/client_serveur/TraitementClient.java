package core.models.modules.client_serveur;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Thread représentant la connexion d'un client au serveur
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
		this.setClient(new Thread(this));
		this.getClient().start();
	}

	/**
	 * Redéfinition de la méthode run : 
	 * description des instructions du thread (écoute en boucle)
	 */
	public void run() {
		this.getServ().printWelcome((this));
		boolean connect = true;
		System.out.println("Un nouveau client s'est connecté");

		while (connect) {
			String envoi = "";
			try {
				envoi = this.getMessage().receptionMessage();
				if(envoi.equals("@sendfile")){
					System.out.println("Demande d'envoi de fichier");
					System.out.println("Envoi de la confiormation de la demande pour débuter le transfert");
					this.getMessage().envoiMessage("@oksendfile");
					this.getMessage().receptionFichier();
				}
				//this.getMessage().receptionFichier();
				//System.out.println("MESSAGE RECU de " + this.getAdresseClient()+ " : " + envoi);
			} catch (IOException ex) {
				Logger.getLogger(TraitementClient.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		}
	}

	/**
	 * Accesseur de socketDeTransfert
	 * @return socketDeTransfert
	 */
	public Socket getSocketDeTransfert() {
		return socketDeTransfert;
	}

	/**
	 * Mutateur de socketDeTransfert
	 * @param socketDeTransfert
	 */
	public void setSocketDeTransfert(Socket socketDeTransfert) {
		this.socketDeTransfert = socketDeTransfert;
	}

	/**
	 * Accesseur de thread client
	 * @return client
	 */
	public Thread getClient() {
		return client;
	}

	/**
	 * Mutateur de thread client
	 * @param client
	 */
	public void setClient(Thread client) {
		this.client = client;
	}

	/**
	 * Accesseur de serveur
	 * @return serv
	 */
	public Serveur getServ() {
		return serv;
	}

	/**
	 * Mutateur de serveur
	 * @param serv
	 */
	public void setServ(Serveur serv) {
		this.serv = serv;
	}

	/**
	 * Accesseur de Message
	 * @return message
	 */
	public Message getMessage() {
		return message;
	}

	/**
	 * Mutateur de Message
	 * @param message
	 */
	public void setMessage(Message message) {
		this.message = message;
	}

	/**
	 * Accesseur d'adresse client
	 * @return adresseClient
	 */
	public InetAddress getAdresseClient() {
		return adresseClient;
	}

	/**
	 * Mutateur d'adresse client
	 * @param adresseClient
	 */
	public void setAdresseClient(InetAddress adresseClient) {
		this.adresseClient = adresseClient;
	}

	/**
	 * Accesseur de nom client
	 * @return nomClient
	 */
	public String getNomClient() {
		return nomClient;
	}

	/**
	 * Mutateur de nom client
	 * @param nomClient
	 */
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
}