package core.models.modules.module_clientserveur;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import core.models.core_modele.ServeurUDP;
import core.models.core_modele.TraitementClientUDP;
import core.models.core_modele.commandes.commandesServeur.CommandeServeurUDP;

public class LanceurServeurUDP {
	public static ServeurUDP run(int numPort,
			HashMap<String, CommandeServeurUDP> listeCommandes) {
		ServeurUDP serv = null;
		if (numPort > 0 && numPort <= 65535) {
			try {
				serv = new ServeurUDP(numPort);
				serv.getListeCommandes().putAll(listeCommandes);
	            //buffer to receive incoming data
	            byte[] buffer = new byte[65536];
	            DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
	            //Wait for an incoming data
	            System.out.println("Server socket created. Waiting for incoming data...");
				while (true) {		
					System.out.println("IM WAITING BIATCH");
					serv.getSocket().receive(incoming);
	                byte[] data = incoming.getData();
	                String s = new String(data, 0, incoming.getLength());
	                System.out.println(incoming.getAddress().getHostAddress() + " : " + incoming.getPort() + " - " + s);
	                boolean connected = false;
	                for(String ip : serv.getListeClients()){
	                	if(ip.equals(incoming.getAddress().getHostAddress().toString())){
	                		connected=true;
	                	}
	                }
					if(!connected){
						System.out.println("JE crée");
						new TraitementClientUDP(incoming.getAddress().getHostAddress().toString(),serv);
					}
				}
			} catch (IOException ex) {
				// TODO Changer pour lever une exception
				JOptionPane.showMessageDialog(new JFrame(),
						"Ce port à cette adresse est occupé");
				System.exit(0);
			}
		} else {
			// TODO Lever Exceptions mauvais port
		}
		return serv;
	}

}
