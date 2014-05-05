package application_temoin_streamaudio.emetteur;

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
		envoiFluxAudio();

	}

	private void initialiserConnexion() {
		try {
			ClientUDP client = new LanceurClientUDP().run(new String(
					"152.77.116.163"), 7846);
			client.getListeCommandes().put("@confirmenvoiflux", new ConfirmEnvoiFlux());
			this.setClient(client);
			this.getClient().getMessage().envoiMessage("@demandeenvoiflux");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void envoiFluxAudio() {
		DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class,
				CaptureAudio.getAudioFormat());
		TargetDataLine targetDataLine = null;
		try {
			targetDataLine = (TargetDataLine) AudioSystem
					.getLine(dataLineInfo);
			targetDataLine.open(CaptureAudio.getAudioFormat());
			targetDataLine.start();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte tempBuffer[] = new byte[8192];
		int cnt = 0;
		while (true) {
			targetDataLine.read(tempBuffer, 0, tempBuffer.length);
			this.getClient().getMessage().envoiMessage("@sender");
			this.getClient().getMessage().envoiFlux(tempBuffer);
		}
	}

	public ClientUDP getClient() {
		return client;
	}

	public void setClient(ClientUDP client) {
		this.client = client;
	}
	
	
}
