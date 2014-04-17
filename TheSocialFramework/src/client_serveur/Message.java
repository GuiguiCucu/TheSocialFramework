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
 * Abstraction des objets et méthodes de communication sur le réseau
 * @author forestip
 */
public class Message {
	
	/**
	 * TODO : Enregistrer chemin d'upload
	 * TODO : enregistrer utilisateur serveur?
	 * TODO : authentification : réponse serveur
	 * TODO : Droit sur dossier? Un dossier par utilisateur?
	 * TODO : Taille du dossier utilisateur?
	 * TODO : Dernière modification ?
	 */

    private OutputStream out = null;
    private InputStream in = null;
    private DataOutputStream sortie;
    private DataInputStream entree;
    private Socket socketTransfert;

    /**
     * Constructeur
     * @param s le socket de liaison
     */
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

    /**
     * Envoi d'une chaine de caractères
     * @param message Message
     */
    synchronized public void envoiMessage(String message) {
        try {
            sortie.writeUTF(message);
        } catch (IOException ex) {
           JOptionPane.showMessageDialog(new JFrame(), "Erreur lors de l'envoi" );
        }
    }
    
    /**
     * Envoi d'un fichier par le réseau
     * @throws IOException 
     */
    synchronized public void envoiFichier() throws IOException{
    	File fileToSend = new File("/home/f/forestip/git/TheSocialFramework/TheSocialFramework/test.zip");
    	this.envoiMessage("@nameFile:"+fileToSend.getName());
    	this.envoiMessage("@sizeFile:"+fileToSend.length());
    	int count;
    	byte[] buffer = new byte[1024];
    	BufferedInputStream inBuf = new BufferedInputStream(new FileInputStream(fileToSend));
    	while ((count = inBuf.read(buffer)) >= 0) {
    	     out.write(buffer, 0, count);
    	     out.flush();
    	}
    	inBuf.close();
    }
    
    /**
     * Réception d'un fichier transité par le fichier
     * @throws IOException 
     */
    synchronized public void receptionFichier() throws IOException{
    	System.out.println("Réception fichier....");
    	String fileName = this.receptionMessage();
    	System.out.println(fileName);
    	String cmd ="@nameFile:";
    	fileName = fileName.replace(cmd, "");
    	
    	String fileSize = this.receptionMessage();
    	System.out.println(fileSize);
    	String cmd2 ="@sizeFile:";
    	fileSize = fileSize.replace(cmd2, "");
		System.out.println("Taille de " +fileName+" : "+fileSize);
    	
    	
       	FileOutputStream fos = new FileOutputStream("/home/f/forestip/git/TheSocialFramework/TheSocialFramework/uploads/"+fileName);
    	BufferedOutputStream outBuf = new BufferedOutputStream(fos);
    	byte[] buffer = new byte[1024];
    	int count;
    	InputStream in = this.getSocket().getInputStream();
    	while((count=in.read(buffer)) >=0){
    	    fos.write(buffer, 0, count);
    	}
    	outBuf.close();
    }

    /**
     *  Réception d'une chaine de caractères
     * @return le message reçu
     * @throws IOException exception relative aux objets de communication
     */
    synchronized public String receptionMessage() throws IOException  {
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
