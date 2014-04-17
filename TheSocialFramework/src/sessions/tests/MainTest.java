package sessions.tests;

import java.util.ArrayList;

import javax.rmi.CORBA.Util;

import contacts.Personne;

import sessions.Utilisateur;

public class MainTest {

	private ArrayList<Utilisateur> listUsers;

	public static void main(String[] args) {
		new MainTest();
	}

	public MainTest() {
		boolean connected = false;
		Personne pers = new Personne("Jean", "Paul", "gcharlety@laposte.net");

		listUsers = new ArrayList<Utilisateur>();
		Utilisateur user1 = new Utilisateur("michel", "micheldu38");

		listUsers.add(user1);

		Utilisateur currentUser = Utilisateur.verification("michel",
				"micheldu38", listUsers);
		if (currentUser != null)
			System.out.println("utilisateur connecté");
		else
			System.out.println("login ou mot de passe incorrect");

		pers.envoyerMail("gcharlety@laposte.net", "SujetTest",
				"Ceci est un essai");
	}
}
