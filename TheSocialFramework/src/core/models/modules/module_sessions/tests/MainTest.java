package core.models.modules.module_sessions.tests;

import java.util.ArrayList;

import javax.rmi.CORBA.Util;

import core.models.modules.module_contacts.Contacts;
import core.models.modules.module_contacts.Personne;
import core.models.modules.module_sessions.Utilisateur;

public class MainTest {

	public static void main(String[] args) {
		new MainTest();
	}

	public MainTest() {
		
		Contacts<Utilisateur> c = new Contacts<Utilisateur>();

		User1 user1 = new User1("michel", "robert"); 
		
		System.out.println("password encodé : "+user1.getPassword());

		c.ajouterPersonne(user1);
		
		if(Utilisateur.verification("michel", "robert", c))
			System.out.println("utilisateur connecté");
		else
			System.out.println("login ou mdp incorrect");

	}
}
