package application_temoin_streamaudio.receveur;

import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import core.controleur.SuperControleur;
import core.models.core_modele.ClientUDP;
import core.models.modules.module_clientserveur.LanceurClientUDP;
import core.models.modules.module_voip.CaptureAudio;

public class Controller extends SuperControleur {
	
	private ClientUDP client;
	
	public static void main(String[] args) {
		Controller c = new Controller();
	}

	public Controller() {
		initialiserConnexion();

	}

	private void initialiserConnexion() {
		try {
			ClientUDP client = new LanceurClientUDP().run(new String(
					"152.77.116.163"), 7846);
			this.setClient(client);
			client.getListeCommandes().put("@confirmreceptionflux", new ConfirmReceptionFlux());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void receveurFluxAudio() {
		while (true) {
			byte[] soundbytes = this.getClient().getMessage().receptionFlux();
			CaptureAudio.toSpeaker(soundbytes);
		}
	}

	public ClientUDP getClient() {
		return client;
	}

	public void setClient(ClientUDP client) {
		this.client = client;
	}
	
	
}
