package core.models.core_modele;

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
 * 
 * @author forestip
 */
public class Message {

	/**
	 * TODO : Enregistrer chemin d'upload TODO : enregistrer utilisateur
	 * serveur? TODO : authentification : réponse serveur TODO : Droit sur
	 * dossier? Un dossier par utilisateur? TODO : Taille du dossier
	 * utilisateur? TODO : Dernière modification ?
	 */

	private OutputStream out = null;
	private InputStream in = null;
	private DataOutputStream sortie;
	private DataInputStream entree;
	private Socket socketTransfert;

	/**
	 * Constructeur
	 * 
	 * @param s
	 *            le socket de liaison
	 */
	public Message(Socket s) {
		try {
			this.setSocket(s);
			this.setOut(this.getSocket().getOutputStream());
			this.setSortie(new DataOutputStream(this.getOut()));
			this.setIn(this.getSocket().getInputStream());
			this.setEntree(new DataInputStream(this.getIn()));
		} catch (IOException ex) {
			Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null,
					ex);
		}
	}

	/**
	 * Envoi d'une chaine de caractères
	 * 
	 * @param message
	 *            Message
	 */
	public void envoiMessage(String message) {
		try {
			sortie.writeUTF(message);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Erreur lors de l'envoi");
		}
	}

	/**
	 * Envoi d'un fichier par le réseau
	 * 
	 * @throws IOException
	 */
	public void envoiFichier() throws IOException {
		File fileToSend = new File(
				"/home/c/cutroneg/git/TheSocialFramework/TheSocialFramework/test.mp3");
		this.envoiMessage("@nameFile:" + fileToSend.getName());
		this.envoiMessage("@sizeFile:" + fileToSend.length());
		int count;
		byte[] buffer = new byte[1024];
		BufferedInputStream inBuf = new BufferedInputStream(
				new FileInputStream(fileToSend));
		while ((count = inBuf.read(buffer)) >= 0) {
			out.write(buffer, 0, count);
			out.flush();
		}
		inBuf.close();
		System.out.println("Fin envoi");
	}
	public void envoiFichier2() throws IOException {
		File fileToSend = new File(
				"/home/c/cutroneg/git/TheSocialFramework/TheSocialFramework/testEnvoi.txt");
		this.envoiMessage("@nameFile:" + fileToSend.getName());
		this.envoiMessage("@sizeFile:" + fileToSend.length());
		int count;
		byte[] buffer = new byte[1024];
		BufferedInputStream inBuf = new BufferedInputStream(
				new FileInputStream(fileToSend));
		while ((count = inBuf.read(buffer)) >= 0) {
			out.write(buffer, 0, count);
			out.flush();
		}
		inBuf.close();
	}

	/**
	 * Réception d'un fichier transité par le fichier
	 * 
	 * @throws IOException
	 */
	public void receptionFichier() throws IOException {
		System.out.println("Réception fichier....");
		String fileName = this.receptionMessage();
		System.out.println(fileName);
		String cmd = "@nameFile:";
		fileName = fileName.replace(cmd, "");

		String fileSize = this.receptionMessage();
		System.out.println(fileSize);
		String cmd2 = "@sizeFile:";
		fileSize = fileSize.replace(cmd2, "");
		System.out.println("Taille de " + fileName + " : " + fileSize);

		FileOutputStream fos = new FileOutputStream(
				"/home/c/cutroneg/git/TheSocialFramework/TheSocialFramework/uploads/"
						+ fileName);
		BufferedOutputStream outBuf = new BufferedOutputStream(fos);
		byte[] buffer = new byte[1024];
		int count;
		InputStream in = this.getSocket().getInputStream();
		while ((count = in.read(buffer)) >= 0) {
			fos.write(buffer, 0, count);
			fos.flush();
			if(count < buffer.length){
				fos.close();
				break;
			}	
		}
		System.out.println("Fin reception");
		
		outBuf.close();	
	}

	/**
	 * Réception d'une chaine de caractères
	 * 
	 * @return le message reçu
	 * @throws IOException
	 *             exception relative aux objets de communication
	 */
	public String receptionMessage() throws IOException {
		String res = "initialisation";
		try {
			res = entree.readUTF();
		} catch (IOException ex) {
			this.getSocket().close();
			this.getEntree().close();
			this.getSortie().close();
			JOptionPane
					.showMessageDialog(new JFrame(),
							"Crash du serveur - Merci de vous reconnecter ultérieurement");
			System.exit(0);
		}
		return res;
	}

	public void fermeture() throws IOException {
		this.getEntree().close();
		this.getSortie().close();
		this.getIn().close();
		this.getOut().close();
		this.getSocket().close();
	}

	/**
	 * Accesseur de d'entrée
	 * 
	 * @return entrée
	 */
	public DataInputStream getEntree() {
		return entree;
	}

	/**
	 * Mutateur d'entrée
	 * 
	 * @param entree
	 */
	public void setEntree(DataInputStream entree) {
		this.entree = entree;
	}

	/**
	 * Accesseur de d'inputStream
	 * 
	 * @return in
	 */
	public InputStream getIn() {
		return in;
	}

	/**
	 * Mutateur d'inputStream
	 * 
	 * @param in
	 */
	public void setIn(InputStream in) {
		this.in = in;
	}

	/**
	 * Accesseur de d'outpuStream
	 * 
	 * @return out
	 */
	public OutputStream getOut() {
		return out;
	}

	/**
	 * Mutateur d'outputStream
	 * 
	 * @param out
	 */
	public void setOut(OutputStream out) {
		this.out = out;
	}

	/**
	 * Accesseur de socket
	 * 
	 * @return socket
	 */
	public Socket getSocket() {
		return socketTransfert;
	}

	/**
	 * Mutateur de socket
	 * 
	 * @param socket
	 */
	public void setSocket(Socket socket) {
		this.socketTransfert = socket;
	}

	/**
	 * Accesseur de sortie
	 * 
	 * @return sortie
	 */
	public DataOutputStream getSortie() {
		return sortie;
	}

	/**
	 * Mutateur de sortie
	 * 
	 * @param sortie
	 */
	public void setSortie(DataOutputStream sortie) {
		this.sortie = sortie;
	}

	/**
	 * Accesseur de transfert
	 * 
	 * @return socketTransfert
	 */
	public Socket getSocketTransfert() {
		return socketTransfert;
	}

	/**
	 * Mutateur de transfert
	 * 
	 * @param socketTransfert
	 */
	public void setSocketTransfert(Socket socketTransfert) {
		this.socketTransfert = socketTransfert;
	}
}
