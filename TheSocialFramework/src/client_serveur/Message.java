package client_serveur;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author forestip
 */
public class Message {

    private OutputStream out = null;
    private InputStream in = null;
    private DataOutputStream sortie;
    private DataInputStream entree;
    private Socket socketTransfert;

    public Message(Socket s) {
        try {
            this.setSocket(s);        
            this.setOut(this.getSocket().getOutputStream());
            this.setSortie(new DataOutputStream(this.getOut()));
            this.setIn(this.getSocket().getInputStream());
            this.setEntree(new DataInputStream(this.getIn()));
        } catch (IOException ex) {
            Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void envoiMessage(String message) {
        try {
            sortie.writeUTF(message);
        } catch (IOException ex) {
           JOptionPane.showMessageDialog(new JFrame(), "Erreur lors de l'envoi" );
        }
    }

    public String receptionMessage() throws IOException  {
        String res = "initialisation";
        try {
            res = entree.readUTF();
        } catch (IOException ex) {
            this.getSocket().close();
            this.getEntree().close();
            this.getSortie().close();
            JOptionPane.showMessageDialog(new JFrame(), "Crash du serveur - Merci de vous reconnecter ultérieurement" );
            System.exit(0);
        }
        return res;
    }

    public DataInputStream getEntree() {
        return entree;
    }

    public void setEntree(DataInputStream entree) {
        this.entree = entree;
    }

    public InputStream getIn() {
        return in;
    }

    public void setIn(InputStream in) {
        this.in = in;
    }

    public OutputStream getOut() {
        return out;
    }

    public void setOut(OutputStream out) {
        this.out = out;
    }

    public Socket getSocket() {
        return socketTransfert;
    }

    public void setSocket(Socket socket) {
        this.socketTransfert = socket;
    }

    public DataOutputStream getSortie() {
        return sortie;
    }

    public void setSortie(DataOutputStream sortie) {
        this.sortie = sortie;
    }

    public Socket getSocketTransfert() {
        return socketTransfert;
    }

    public void setSocketTransfert(Socket socketTransfert) {
        this.socketTransfert = socketTransfert;
    }    
}
