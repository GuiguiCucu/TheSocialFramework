package core.modules.contacts.tests;

import java.nio.charset.Charset;

import core.modules.contacts.Contacts;
import core.modules.contacts.Csv;
import core.modules.contacts.Groupe;
import core.modules.contacts.Personne;

public class ContactTest {

	public static void main(String[] args) {
		
		Contacts contacts = new Contacts();
		Groupe groupe = new Groupe("Mes bestos");
		
		// test de l'import
		//Csv.importer("Z:/POO/ProjetFramework/google.csv", contacts);
		Csv.importer("/home/c/cutroneg/PPO/google.csv", contacts);
		
		for(int i=0; i<contacts.getContacts().size(); i++){
			System.out.print(((Personne) contacts.getContacts().get(i)).getNom()+" ");
			System.out.println(((Personne) contacts.getContacts().get(i)).getPrenom());
			System.out.println(((Personne) contacts.getContacts().get(i)).getAdresse());
			System.out.println("\n*****************\n");
		}
		
		//System.out.println(groupe.findPersonneByPrenom("Adèle").get(0).getNom());

//		String chaine = "Adèle";
//		String str2 = new String(chaine.getBytes(),Charset.forName("UTF-8"));
//		System.out.println(str2);
		
		// test de l'export
		//Csv.exporter("Z:/POO/ProjetFramework/test.csv", contacts);
		Csv.exporter("/home/c/cutroneg/PPO/contacts.csv", contacts);
	}

}
