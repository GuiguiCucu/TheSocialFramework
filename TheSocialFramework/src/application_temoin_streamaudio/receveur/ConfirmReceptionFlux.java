package application_temoin_streamaudio.receveur;

import application_temoin_streamaudio.emetteur.Controller;
import core.controleur.SuperControleur;
import core.models.core_modele.commandes.commandesClient.CommandeClient;
import core.models.modules.module_voip.CaptureAudio;

public class ConfirmReceptionFlux implements CommandeClient {

	@Override
	public void execute(SuperControleur controleur) {
		while(true){
			byte[] soundbytes = ((Controller)controleur).getClient().getMessage().receptionFlux();
			CaptureAudio.toSpeaker(soundbytes);
		}

	}

}
