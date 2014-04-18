package contacts;

public class Groupe<P> extends Contacts<P> {

	private String nom;
	
	public Groupe(String nom) {
		super();
		this.setNom(nom);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
