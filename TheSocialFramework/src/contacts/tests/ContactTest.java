package contacts.tests;

import contacts.Contacts;
import contacts.Groupe;
import contacts.Personne;

public class ContactTest {

	public static void main(String[] args) {
		
		Contacts contacts = new Contacts();
		Groupe groupe = new Groupe("Mes bestos");
		
		// test de l'import
		//contacts.importer("Z:/POO/ProjetFramework/google.csv");
		groupe.importer("/home/c/cutroneg/PPO/google.csv");
		
		for(int i=0; i<groupe.getContacts().size(); i++){
			System.out.print(((Personne) groupe.getContacts().get(i)).getNom()+" ");
			System.out.println(((Personne) groupe.getContacts().get(i)).getPrenom());
			System.out.println(((Personne) groupe.getContacts().get(i)).getAdresse());
			System.out.println("\n*****************\n");
		}
		
		//System.out.println(groupe.findPersonneByPrenom("Adèle").get(0).getNom());

		
		// test de l'export
		//contacts.exporter("Z:/POO/ProjetFramework/test.csv");
		//contacts.exporter("/home/c/cutroneg/PPO/contacts.csv");
	}

}
