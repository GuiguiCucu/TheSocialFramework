package contacts;

public class ContactTest {

	public static void main(String[] args) {
		Contacts.importer("Z:/POO/ProjetFramework/OutlookContacts.csv");
		
		for(int i=0; i<Contacts.contacts.size(); i++){
			System.out.println(((Personne) Contacts.contacts.get(i)).getAdresse());
		}
	}

}
