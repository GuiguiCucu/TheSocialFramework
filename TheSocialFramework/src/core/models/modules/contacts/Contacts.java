package core.models.modules.contacts;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author montalda
 * 
 */
public class Contacts<P> {

	private List<P> contacts;
	
	public Contacts(){
		this.contacts = new ArrayList<P>();
	}

	

	/**
	 * accesseur de la liste de contacts 
	 * @return contacts
	 */
	public List<P> getContacts() {
		return contacts;
	}

	/**
	 * mutateur de liste de contacts
	 * @param contacts
	 */
	public void setContacts(List<P> contacts) {
		this.contacts = contacts;
	}
	

	/**
	 * Cree une nouvelle personne et ajoute cette personne a la liste de contacts
	 * @param nom
	 * @param prenom
	 * @param adresse
	 */
	public void ajouterPersonne(P personne){
		getContacts().add(personne);
	}

}
