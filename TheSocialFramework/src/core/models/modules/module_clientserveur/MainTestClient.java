package core.models.modules.module_clientserveur;

import java.io.IOException;

public class MainTestClient {

	public static void main(String[] args) throws IOException {
		LanceurClient.run(new String("0.0.0.0"), 7846);
	}

}
