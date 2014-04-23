package core.vue;

import javax.swing.JFrame;

import core.controleur.SuperControleur;

import java.util.Observable;
import java.util.Observer;
/**
 * Super classe des vues
 * @author Forestier Pierre-Antoine
 */
public abstract class View extends JFrame implements Observer{
	
	// énumération des états possibles pour une vue
	// cet état permet de contrôler l'enchaînement des dialogues possibles pour l'utilisateur
	// dans une vue
	private static final long serialVersionUID = 1L;
	public final static int initiale = 0, inter1 = 1, inter2 = 2, inter3 = 3, inter4 = 4, finale = 5;
	
	// toute vue communique avec le controleur
	private SuperControleur _controleur;
	private int _etat;

	public View(SuperControleur controleur) {
		this.setControleur(controleur);
	}
	
	protected SuperControleur getControleur() {
		return _controleur;
	}
	
	protected void setControleur(SuperControleur cont) {
		_controleur = cont;
	}
	
	public int getEtat() {
		return _etat;
	}
	
	public void setEtat(int etat) {
		_etat = etat;
	}
	// méthode de la classe interface Observer 
	// à implémenter par toute classe réalisant cette interface
	public void update(Observable observable, Object objet) {
		this.repaint();
	} 
	
}