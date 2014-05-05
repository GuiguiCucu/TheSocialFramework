package core.controleur;

import java.util.LinkedList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import core.vue.View;
import core.vue.VueMenu;

/**
 * Classe controleur et application (système)
 * 
 * @author Pierre-Antoine Forestier
 */
public class SuperControleur implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * La classe Controleur est unique pour tous les cas d'utilisation.Elle est
	 * également la classe "application" qui gère l'ensemble des objets de
	 * l'application. Elle fournit au développeur une structure MVC.
	 */
	private LinkedList<View> _vues;

	/**
	 * Constructeur
	 */
	public SuperControleur() {
		this.setVue(new LinkedList<View>());
	}

	/**
	 * ajoute ou enlève la vue active courante de la liste des vues
	 * 
	 * @param vue
	 *            la vue à affecter
	 */
	private void setVue(LinkedList<View> vues) {
		this._vues = vues;
	}

	/**
	 *  Ajoute une vue dans la liste des interfaces (en dernière position)
	 * @param vue
	 */
	private void setVue(View vue) {
		this._vues.addLast(vue);
	}

	/**
	 * Supprime la dernière vue de la collection d'interfaces
	 */
	private void removeVue() {
		this._vues.removeLast();
	}

	/**
	 * Retourne la dernière vue de la collection d'interface
	 * @return la derniere vue
	 */
	private View getVue() {
		return (View) _vues.getLast();
	}

	/**
	 * Retourne la vue de menu de l'application
	 * @return la vue de menu
	 */
	private VueMenu getVueMenu() {
		return (VueMenu) _vues.getFirst();
	}

	// ************************************************************************************************************

	/**
	 * Lance l'interface de menu définie par l'utilisateur
	 */
	public void menu() {
		try {
			this.setVue(new VueMenu(this));
			this.getVueMenu().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Méthode générique appelée sur la vue à afficher
	 */
	public void initialiserVue() {
		this.getVueMenu().setVisible(false);
		this.getVue().setEtat(View.initiale);
		this.getVue().setVisible(true);
	}

	/**
	 * fermeture de la fenêtre vue lors de la fermeture de la fenêtre principale
	 * de l'application sauvegarde des objets sérialisés
	 */
	public void fermerVue(View vue) {
		// la vue est détruite et n'est plus la vue active courante
		if (vue instanceof VueMenu) {
			// Quitte l'aplication. Sauvegarde les objets du modèle
			vue.dispose();
			this.removeVue();
			this.sauve();
			System.exit(0);
		} else {
			// le Menu est rendu de nouveau visible
			vue.dispose();
			this.removeVue();
			this.getVueMenu().setVisible(true);
		}
	}

	/**
	 * restauration des objets de l'application
	 */
	public SuperControleur restaure() {
		try {
			FileInputStream fichier = new FileInputStream("Fsauv.ser");
			ObjectInputStream in = new ObjectInputStream(fichier);
			return ((SuperControleur) in.readObject());
		} catch (Exception e) {
			System.out
					.println("Pbs de Restauration ou fichier non encore créé");
			return this;
		}
	}

	/**
	 * sauvegarde des objets de l'application
	 */
	private void sauve() {
		try {
			FileOutputStream f = new FileOutputStream("Fsauv.ser");
			ObjectOutputStream out = new ObjectOutputStream(f);
			out.writeObject(this);
		} catch (Exception e) {
			System.out.println("Pb de Sauvegarde dans le fichier");
		}
	}
}
