package sessions.tests;

import java.util.ArrayList;

import sessions.Utilisateur;

public class MainTest {
	
	private ArrayList<Utilisateur> listUsers;

	public static void main(String[] args) {
		new MainTest();
	}
	
	public MainTest() {
		listUsers = new ArrayList<Utilisateur>();
		Utilisateur user1 = new Utilisateur("michel", "micheldu38");
		
		listUsers.add(user1);
		System.out.println(user1.getPassword());
	}

}
