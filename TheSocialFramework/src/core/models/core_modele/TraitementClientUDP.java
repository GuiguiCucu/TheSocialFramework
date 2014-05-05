package core.models.core_modele;

import java.io.File;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

import core.models.core_modele.commandes.commandesServeur.CommandeServeurUDP;

public class TraitementClientUDP implements Runnable {

	private DatagramSocket socketDeTransfert = null;
	private Thread client = null;
	private ServeurUDP serv = null;
	private MessageUDP message;
	private InetAddress adresseClient;
	private String nomClient;
	private File currentDir;
	private boolean connect ;

	/**
	 * 
	 * @param socketTransfert
	 *            Socket de transfert propre au serveur
	 * @param serv
	 *            Serveur sur lequel est rattaché le thread
	 */
	public TraitementClientUDP(DatagramSocket socketTransfert, ServeurUDP serv) {
		this.setSocketDeTransfert(socketTransfert);
		this.setServ(serv);
		this.getServ().addClient(this);
		this.setMessage(new MessageUDP(this.getSocketDeTransfert()));
		this.setAdresseClient(this.getSocketDeTransfert().getInetAddress());
		this.setNomClient("");
		this.setConnect(true);
		this.setClient(new Thread(this));
		this.getClient().start();
		
	}

	/**
	 * Redéfinition de la méthode run : 
	 * description des instructions du thread (écoute en boucle)
	 */
	public void run() {
		this.getServ().printWelcome((this));
		while (isConnect()) {
			String envoi = "";
			try {
				envoi = this.getMessage().receptionMessage();
				CommandeServeurUDP cmd = 	this.getServ().getListeCommandes().get(envoi);
				if(cmd!=null){
					cmd.execute(this);
				}else{
					System.out.println("recu : "+envoi);
					this.getMessage().envoiMessage(envoi.toUpperCase());
				}
				
			} catch (IOException ex) {
				Logger.getLogger(TraitementClient.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		}
	}
	
	/**
	 * crée un dossier pour l'utilisateur courant si il n'exite pas
	 */
	public void folder() {
		String repertoireCourant = System.getProperty("user.dir");
		setCurrentDir(new File(repertoireCourant+"/"+getNomClient()));
		if(!getCurrentDir().exists()){
			getCurrentDir().mkdir();
		}
	}
	

	/**
	 * Accesseur de socketDeTransfert
	 * @return socketDeTransfert
	 */
	public DatagramSocket getSocketDeTransfert() {
		return socketDeTransfert;
	}

	/**
	 * Mutateur de socketDeTransfert
	 * @param socketDeTransfert
	 */
	public void setSocketDeTransfert(DatagramSocket socketDeTransfert) {
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
	public ServeurUDP getServ() {
		return serv;
	}

	/**
	 * Mutateur de serveur
	 * @param serv
	 */
	public void setServ(ServeurUDP serv) {
		this.serv = serv;
	}

	/**
	 * Accesseur de Message
	 * @return message
	 */
	public MessageUDP getMessage() {
		return message;
	}

	/**
	 * Mutateur de Message
	 * @param message
	 */
	public void setMessage(MessageUDP message) {
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

	public File getCurrentDir() {
		return currentDir;
	}

	public void setCurrentDir(File currentDir) {
		this.currentDir = currentDir;
	}

	public boolean isConnect() {
		return connect;
	}

	public void setConnect(boolean connect) {
		this.connect = connect;
	}
	
}
