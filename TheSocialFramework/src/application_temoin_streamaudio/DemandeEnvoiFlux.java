package application_temoin_streamaudio;

import core.models.core_modele.TraitementClientUDP;
import core.models.core_modele.commandes.commandesServeur.CommandeServeurUDP;

public class DemandeEnvoiFlux implements CommandeServeurUDP {

	@Override
	public void execute(TraitementClientUDP tc) {
		tc.getMessage().envoiMessage("@confirmenvoiflux");
		for(TraitementClientUDP t : tc.getServ().getTraiteClients()){
			if(t!=tc){
				t.getMessage().envoiMessage("@confirmreceptionflux");
			}
		}
		while(true){
			byte[] soundbytes =tc.getMessage().receptionFlux();
			for(TraitementClientUDP t : tc.getServ().getTraiteClients()){
				if(t!=tc){
					
					t.getMessage().envoiFlux(soundbytes);
				}
			}
		}
	}

}
