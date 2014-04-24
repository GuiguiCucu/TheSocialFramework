package application_temoin_cloud;

import java.io.File;

import core.models.modules.module_clientserveur.LanceurServeur;

public class Serv {
	
	public File currentDir;
	private File[] liste;

	public static void main(String[] args) {
		LanceurServeur.run(2048);
	}

	/**
	 * crée un dossier pour l'utilisateur courant si il n'exite pas
	 */
	public void folder() {
		String repertoireCourant = System.getProperty("user.dir");
		
		setCurrentDir(new File(repertoireCourant+"/"+getUserName()));
		
		if(!getCurrentDir().exists()){
			getCurrentDir().mkdir();
		}
	}
	
	/**
	 * liste tous les fichiers dans le répertoire courant
	 */
	public void list(){

		setListe(getCurrentDir().listFiles());
		for (int i = 0; i < getListe().length; i++) {
//			if (listefichiers[i].isDirectory()) {
//				System.out.println("Dossier : " + listefichiers[i].getName());
//			} else if (listefichiers[i].isFile()) {
//				
				System.out.println("Fichier : " + getListe()[i].getName());
			//}
		}
	}
	
	public File getCurrentDir() {
		return currentDir;
	}

	public void setCurrentDir(File currentDir) {
		this.currentDir = currentDir;
	}
	
	public File[] getListe() {
		return liste;
	}

	public void setListe(File[] liste) {
		this.liste = liste;
	}
}
