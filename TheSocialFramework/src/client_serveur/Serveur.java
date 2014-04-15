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
 *
 * @author forestip
 */
public class Serveur {

    /**
     * @param args the command line arguments
     */
    private ArrayList<TraitementClient> traiteClients;
    private Vector<String> listeClients;
    private int port = 5010;
    private ServerSocket socketEcoute;
    private Socket socketTransfert;

    public static void main(String[] args) {
        try {
            Serveur serv = new Serveur(7845);
            System.out.println("Serveur lancé!");

            while (true) {
                new TraitementClient(serv.getSocketEcoute().accept(), serv);
            }
        } catch (IOException ex) {
           JOptionPane.showMessageDialog(new JFrame(), "Ce port à cette adresse est occupé" );
            System.exit(0);
        }
    }
    
    public Serveur(int p) {
        try {
            this.setTraiteClients(new ArrayList<TraitementClient>());
            this.setPort(p);
            this.setSocketEcoute(new ServerSocket(this.getPort()));
        } catch (IOException ex) {
             JOptionPane.showMessageDialog(new JFrame(), "Ce port à cette adresse est occupé" );
            System.exit(0);
        }
    }

    synchronized public void fermerConnexion() {
        try {
            this.sendAll(null, "Le serveur a fermé la connexion");
            this.getSocketTransfert().close();

        } catch (IOException ex) {
            Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    synchronized public void sendAll(TraitementClient origine, String message) {
        DataOutput tmpFlux;
        for (int i = 0; i < this.getTraiteClients().size(); i++) // parcours de la table des connectés
        {
            try
            {
                tmpFlux = this.getTraiteClients().get(i).getMessage().getSortie(); // extraction de l'élément courant 
                // ecriture du texte passé en paramètre (et concaténation d'une string de fin de chaine si besoin)
                tmpFlux.writeUTF(message);
            } catch (IOException ex) {
                Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    synchronized public void printWelcome(TraitementClient tC) {
        System.out.println("Client connecté " + tC.getAdresseClient());
    }
    
    /*
     * Synchronized en cas d'actions multiples des Threads sur l'ArrayList
     */
    synchronized public void delClient(TraitementClient tC) {
        // parcourir pour retrouver tC puis le supprimer
    }

    synchronized public int addClient(TraitementClient newClient) {
        this.getTraiteClients().add(newClient); // on ajoute le nouveau flux de sortie au tableau
        return this.getTraiteClients().size() - 1; // on retourne le numéro du client ajouté (size-1)
    }


//////////////////////////////////////////////////////////
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
//////////////////////////////////////////////////////////

    public ServerSocket getSocketEcoute() {
        return socketEcoute;
    }

    public void setSocketEcoute(ServerSocket socketEcoute) {
        this.socketEcoute = socketEcoute;
    }
//////////////////////////////////////////////////////////

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
