package core.models.modules.module_contacts.tests;

import java.nio.charset.Charset;

import core.models.modules.module_contacts.Contacts;
import core.models.modules.module_contacts.Csv;
import core.models.modules.module_contacts.Groupe;
import core.models.modules.module_contacts.Personne;

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
		
		if(((Personne) contacts.getContacts().get(0)).compareTo(new Personne("Montaldi", "Ad�le", "adele.montaldi@gmail.com"))){
			System.out.println("ok");
		}

//		String chaine = "Adèle";
//		String str2 = new String(chaine.getBytes(),Charset.forName("UTF-8"));
//		System.out.println(str2);
		
		// test de l'export
		//Csv.exporter("Z:/POO/ProjetFramework/test.csv", contacts);
		//Csv.exporter("/home/c/cutroneg/PPO/contacts.csv", contacts);
	}

}
