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
	public void envoiFichier(String path) {
		File fileToSend = new File(path);
		this.envoiMessage("@nameFile:" + fileToSend.getName());
		this.envoiMessage("@sizeFile:" + fileToSend.length());
		int count;
		byte[] buffer = new byte[1024];
		BufferedInputStream inBuf;
		try {
			inBuf = new BufferedInputStream(new FileInputStream(fileToSend));
			while ((count = inBuf.read(buffer)) >= 0) {
				out.write(buffer, 0, count);
				out.flush();
			}
			inBuf.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Réception d'un fichier transité par le fichier
	 * 
	 * @throws IOException
	 */
	public void receptionFichier(String path) {
		String fileName = this.receptionMessage();
		String cmd = "@nameFile:";
		fileName = fileName.replace(cmd, "");
		String fileSize = this.receptionMessage();
		String cmd2 = "@sizeFile:";
		fileSize = fileSize.replace(cmd2, "");
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(path + "/" + fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedOutputStream outBuf = new BufferedOutputStream(fos);
		byte[] buffer = new byte[1024];
		int count;
		InputStream in = null;
		try {
			in = this.getSocket().getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			while ((count = in.read(buffer)) >= 0) {
				fos.write(buffer, 0, count);
				fos.flush();
				if (count < buffer.length) {
					fos.close();
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			outBuf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Réception d'une chaine de caractères
	 * 
	 * @return le message reçu
	 * @throws IOException
	 *             exception relative aux objets de communication
	 */
	public String receptionMessage() {
		String res = "initialisation";
		try {
			res = entree.readUTF();
		} catch (IOException ex) {
			try {
				this.getSocket().close();
				this.getEntree().close();
				this.getSortie().close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			JOptionPane
					.showMessageDialog(new JFrame(),
							"Crash du serveur - Merci de vous reconnecter ultérieurement");
			System.exit(0);
		}
		return res;
	}

	public void fermeture() {
		try {
			this.getEntree().close();
			this.getSortie().close();
			this.getIn().close();
			this.getOut().close();
			this.getSocket().close();
		} catch (IOException e) {
			e.printStackTrace();
		}

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
