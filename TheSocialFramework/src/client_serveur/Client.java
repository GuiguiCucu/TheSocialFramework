package client_serveur;


import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Classe matérialisant le client sous forme de thread
 * @author forestip
 *
 */
public class Client implements Runnable {

	private Socket socket;
	private InetAddress serv;
	private int port;
	private Message message;
	private Thread serveur = null;
	
	private String nomServeur;
	private String pseudoClient;
	

	public static void main(String[] args) throws IOException {
		String nameServer = new String("0.0.0.0");
        int numPort = 7846;
        new Client(nameServer, numPort);
	}

	/**
	 * Constructeur
	 * @param serverName nom / IP du serveur
	 * @param numPort port du serveur
	 * @throws IOException 
	 */
    public Client(String serverName, int numPort) throws IOException {
        this.setNomServeur(serverName);
        this.setPort(numPort);
        this.connexionServeur();
        this.getServeur().start();
        this.getMessage().envoiMessage("@sendfile");
        System.out.println("Envoi fichier...");
        this.getMessage().envoiFichier();
    }
    
    /**
     * Etablissement d'une connexion avec le serveur identifié par numéro de port et IP
     */
    public void connexionServeur() {
        try {
            this.setSocket(new Socket(this.getNomServeur(), this.getPort()));
            this.setMessage(new Message(this.getSocket()));
            this.setServeur(new Thread(this));
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Le serveur est inactif - Merci de réessayer ultérieurement");
            System.exit(0);
        }
    }

    /**
     * Fermeture de la connexion avec le serveur
     */
    public void deconnexionServeur() {
        try {
            //Thread + appel
            this.getMessage().getEntree().close();
            this.getMessage().getSortie().close();
            this.getSocket().close();

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Redéfinition de la méthode run : 
	 * Description des instructions du thread (écoute en boucle)
     */
	@Override
	public void run() {

        System.out.println("Connecté à " + this.getNomServeur());
        boolean connect = true;
        while (connect) {
            String recu = "";
            try {
				recu = this.getMessage().receptionMessage();
				System.out.println(recu);
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        
	}
	
	
	/*GETTERS & SETTERS*/
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public InetAddress getServ() {
		return serv;
	}

	public void setServ(InetAddress serv) {
		this.serv = serv;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Thread getServeur() {
		return serveur;
	}

	public void setServeur(Thread serveur) {
		this.serveur = serveur;
	}

	public String getNomServeur() {
		return nomServeur;
	}

	public void setNomServeur(String nomServeur) {
		this.nomServeur = nomServeur;
	}

	public String getPseudoClient() {
		return pseudoClient;
	}

	public void setPseudoClient(String pseudoClient) {
		this.pseudoClient = pseudoClient;
	}

	
}
