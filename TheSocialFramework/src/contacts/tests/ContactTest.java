package contacts.tests;

import contacts.Contacts;
import contacts.Personne;

public class ContactTest {

	public static void main(String[] args) {
		
		Contacts contacts = new Contacts();
		
		// test de l'import
		contacts.importer("Z:/POO/ProjetFramework/google.csv");
		//contacts.importer("/home/c/cutroneg/PPO/google.csv");
		
		for(int i=0; i<contacts.getContacts().size(); i++){
			System.out.print(((Personne) contacts.getContacts().get(i)).getNom()+" ");
			System.out.println(((Personne) contacts.getContacts().get(i)).getPrenom());
			System.out.println(((Personne) contacts.getContacts().get(i)).getAdresse());
			System.out.println("\n*****************\n");
		}
		
		// test de l'export
		 contacts.exporter("Z:/POO/ProjetFramework/test.csv");
		//contacts.exporter("/home/c/cutroneg/PPO/contacts.csv");
	}

}
