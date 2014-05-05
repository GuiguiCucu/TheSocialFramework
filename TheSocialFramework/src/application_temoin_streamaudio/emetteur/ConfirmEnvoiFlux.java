package application_temoin_streamaudio.emetteur;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import core.controleur.SuperControleur;
import core.models.core_modele.commandes.commandesClient.CommandeClient;
import core.models.modules.module_voip.CaptureAudio;

public class ConfirmEnvoiFlux implements CommandeClient {

	@Override
	public void execute(SuperControleur controleur) {
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
			((Controller)controleur).getClient().getMessage().envoiFlux(tempBuffer);
		}

	}

}
