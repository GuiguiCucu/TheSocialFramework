package core.models.modules.module_contacts;

import java.util.*;

public class Personne extends Contact<Personne> {

	private String nom;
	private String prenom;
	private String adresse;
	private final static String MAILER_VERSION = "Java";

	public Personne(String nom, String prenom, String adresse) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	@Override
	public boolean compareTo(Contact<Personne> c) {
		boolean res = false;
		if (((Personne) c).getNom().equals(this.getNom())
				&& ((Personne) c).getPrenom().equals(this.getPrenom())
				&& ((Personne) c).getAdresse().equals(this.getAdresse()))
			res = true;
		return res;
	}

}
