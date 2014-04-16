package contacts.tests;

import contacts.Contacts;
import contacts.Personne;

public class ContactTest {

	public static void main(String[] args) {
		
		// test de l'import
		Contacts.importer("/home/c/cutroneg/PPO/google.csv");
		
		for(int i=0; i<Contacts.contacts.size(); i++){
			System.out.print(((Personne) Contacts.contacts.get(i)).getNom()+" ");
			System.out.println(((Personne) Contacts.contacts.get(i)).getPrenom());
			System.out.println(((Personne) Contacts.contacts.get(i)).getAdresse());
			System.out.println("\n*****************\n");
		}
		
		// test de l'export
		Contacts.exporter("/home/c/cutroneg/PPO/contacts.csv");
	}

}
