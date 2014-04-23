package application_temoin_cloud;

import java.io.IOException;

import core.models.core_modele.Client;

public class Controller {

	public static void main(String[] args) throws IOException {
		Client client = new Client(new String("0.0.0.0"), 2048);
	}

}
