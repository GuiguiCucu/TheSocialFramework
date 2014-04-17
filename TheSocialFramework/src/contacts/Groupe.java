package contacts;

import java.util.ArrayList;
import java.util.List;

/**
 * Possibilité de classer les contacts dans des groupes
 * @author montalda
 *
 */
public class Groupe {
	
	private String nom;
	private List liste;
	
	/**
	 * Constructeur d'un groupe de contact
	 * @param nom
	 */
	public Groupe(String nom){
		this.nom = nom;
		this.liste = new ArrayList<Personne>();
	}
	
	/**
	 * Ajoute une personne au groupe de contact
	 * @param p
	 */
	public void ajouterPersonne(Personne p){
		liste.add(p);
	}
}
