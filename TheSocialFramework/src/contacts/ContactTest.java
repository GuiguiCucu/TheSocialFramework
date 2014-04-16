package contacts;

public class ContactTest {

	public static void main(String[] args) {
		Contacts.importer("/home/c/cutroneg/PPO/google.csv");
		
		for(int i=0; i<Contacts.contacts.size(); i++){
			System.out.print(((Personne) Contacts.contacts.get(i)).getNom()+" ");
			System.out.println(((Personne) Contacts.contacts.get(i)).getPrenom());
			System.out.println(((Personne) Contacts.contacts.get(i)).getAdresse());
			System.out.println("\n*****************\n");
		}
	}

}
