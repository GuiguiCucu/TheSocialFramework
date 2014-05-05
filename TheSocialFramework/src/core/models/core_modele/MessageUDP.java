package core.models.core_modele;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MessageUDP {
	
	private DatagramSocket socketTransfert;

	/**
	 * Constructeur
	 * 
	 * @param s
	 *            le socket de liaison
	 */
	public MessageUDP(DatagramSocket s) {
		this.setSocket(s);
	}

	/**
	 * Envoi d'une chaine de caractères
	 * 
	 * @param message
	 *            Message
	 */
	public void envoiMessage(String message) {
		try {
			byte[] sendData = new byte[1024];
			sendData = message.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData,
					sendData.length,
					this.getSocketTransfert().getInetAddress(), this
							.getSocketTransfert().getPort());
			this.getSocketTransfert().send(sendPacket);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Erreur lors de l'envoi");
		}
	}

	/**
	 * Réception d'une chaine de caractères
	 * 
	 * @return le message reçu
	 * @throws IOException
	 *             exception relative aux objets de communication
	 */
	public String receptionMessage() throws IOException {

		byte[] receiveData = new byte[1024];
		DatagramPacket receivePacket = new DatagramPacket(receiveData,
				receiveData.length);
		this.getSocketTransfert().receive(receivePacket);
		String reponse = new String(receivePacket.getData());
		return reponse;
	}

	
	/**
	 * Envoi d'un tableau de byte à travers la liaison client-serveur UDP
	 * @param packet le tableau de byte
	 */
	public void envoiFlux(byte packet[]) {
		try {
			DatagramPacket sendPacket = new DatagramPacket(packet,
					packet.length, this.getSocketTransfert().getInetAddress(),
					this.getSocketTransfert().getPort());
			this.getSocketTransfert().send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Reception d'un tableau de byte à travers la liaison client-serveur UDP
	 * @return un tableau de byte
	 */
	public byte[] receptionFlux(){
		try {
			DatagramSocket sock;
			sock = new DatagramSocket(this.getSocketTransfert().getPort());
			byte packet[] = new byte[8192];
			DatagramPacket datagram = new DatagramPacket(packet,
					packet.length, this.getSocketTransfert().getInetAddress(),
					this.getSocketTransfert().getPort());
			sock.receive(datagram);
			sock.close();
			return datagram.getData();
		} catch (SocketException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	public void fermeture() throws IOException {
		this.getSocket().close();
	}

	/**
	 * Accesseur de socket
	 * 
	 * @return socket
	 */
	public DatagramSocket getSocket() {
		return socketTransfert;
	}

	/**
	 * Mutateur de socket
	 * 
	 * @param socket
	 */
	public void setSocket(DatagramSocket socket) {
		this.socketTransfert = socket;
	}


	/**
	 * Accesseur de transfert
	 * 
	 * @return socketTransfert
	 */
	public DatagramSocket getSocketTransfert() {
		return socketTransfert;
	}

	/**
	 * Mutateur de transfert
	 * 
	 * @param socketTransfert
	 */
	public void setSocketTransfert(DatagramSocket socketTransfert) {
		this.socketTransfert = socketTransfert;
	}

}
