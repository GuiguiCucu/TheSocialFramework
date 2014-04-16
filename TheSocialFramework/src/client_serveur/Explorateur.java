package client_serveur;

import java.io.File;

public class Explorateur {

	public static void main(String[] args) {
		Explorateur e = new Explorateur();
		e.listerRepertoireCourant();
	}

	public Explorateur() {
	}

	public void listerRepertoireCourant() {
		String repertoireCourant = System.getProperty("user.dir");
		File repertoire = new File(repertoireCourant);
		File[] listefichiers;
		listefichiers = repertoire.listFiles();
		for (int i = 0; i < listefichiers.length; i++) {
			if(listefichiers[i].isDirectory()){
				System.out.println("Dossier : " + listefichiers[i].getName());
			}
			else if(listefichiers[i].isFile()){
				System.out.println("Fichier : " + listefichiers[i].getName());
			}
		}
	}
}
