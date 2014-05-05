package core.models.modules.module_clientserveur;

import java.io.IOException;

public class MainTestClientUDP {

	public static void main(String[] args) {
		try {
			System.out.println("IN");
			LanceurClientUDP.run(new String("152.77.116.163"), 7846);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
