package client_serveur;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		try {
			Serveur serv = new Serveur(7846);
			System.out.println("Serveur lancé!");

			while (true) {
				new TraitementClient(serv.getSocketEcoute().accept(), serv);
			}
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Ce port à cette adresse est occupé");
			System.exit(0);
		}
	}

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
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Ce port à cette adresse est occupé");
			System.exit(0);
		}
	}

	/**
	 * Fermeture de la connexion synchronized car partagé par plusieurs threads
	 * TraitementClient
	 */
	synchronized public void fermerConnexion() {
		try {
			this.sendAll(null, "Le serveur a fermé la connexion");
			this.getSocketTransfert().close();

		} catch (IOException ex) {
			Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null,
					ex);
		}
	}

	/**
	 * Diffusion d'un message à l'ensemble des connectés au serveur synchronized
	 * car partagée par plusieurs threads TraitementClient
	 * 
	 * @param origine
	 *            Thread émetteur
	 * @param message
	 *            Message
	 */
	synchronized public void sendAll(TraitementClient origine, String message) {
		DataOutput tmpFlux;
		for (int i = 0; i < this.getTraiteClients().size(); i++) // parcours de
																	// la table
																	// des
																	// connectés
		{
			try {
				tmpFlux = this.getTraiteClients().get(i).getMessage()
						.getSortie(); // extraction de l'élément courant
				// ecriture du texte passé en paramètre (et concaténation d'une
				// string de fin de chaine si besoin)
				tmpFlux.writeUTF(message);
			} catch (IOException ex) {
				Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}
	}

	/**
	 * 
	 * synchronized car partagée par plusieurs threads TraitementClient
	 * @param tC thread à qui souhaiter la bienvenue
	 */
	synchronized public void printWelcome(TraitementClient tC) {
		System.out.println("Client connecté " + tC.getAdresseClient());
	}

	/**
	 * synchronized car partagé par plusieurs threads TraitementClient
	 * @param tC thread à supprimer
	 */
	synchronized public void delClient(TraitementClient tC) {
		
	}

	/**
	 * Ajout d'un thread à la liste des processus s'éxécutant sur le serveur
	 * @param newClient thread à ajouter
	 * @return la nouvelle taille de la liste de thread éxécutés sur le serveur
	 */
	synchronized public int addClient(TraitementClient newClient) {
		this.getTraiteClients().add(newClient); // on ajoute le nouveau flux de
												// sortie au tableau
		return this.getTraiteClients().size() - 1; // on retourne le numéro du
													// client ajouté (size-1)
	}

	// ////////////////////////////////////////////////////////
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	// ////////////////////////////////////////////////////////

	public ServerSocket getSocketEcoute() {
		return socketEcoute;
	}

	public void setSocketEcoute(ServerSocket socketEcoute) {
		this.socketEcoute = socketEcoute;
	}

	// ////////////////////////////////////////////////////////

	public Socket getSocketTransfert() {
		return socketTransfert;
	}

	public void setSocketTransfert(Socket socketTransfert) {
		this.socketTransfert = socketTransfert;
	}

	public ArrayList<TraitementClient> getTraiteClients() {
		return traiteClients;
	}

	public void setTraiteClients(ArrayList<TraitementClient> traiteClients) {
		this.traiteClients = traiteClients;
	}

}
