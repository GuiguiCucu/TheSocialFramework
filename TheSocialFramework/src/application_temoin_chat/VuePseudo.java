package application_temoin_chat;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VuePseudo extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private Controller c;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblPort;


	/**
	 * Create the frame.
	 */
	public VuePseudo(Controller c) {
		setC(c);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 361, 298);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(104, 41, 150, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblEntrezVotrePseudo = new JLabel("Entrez votre pseudo");
		lblEntrezVotrePseudo.setBounds(104, 25, 150, 15);
		contentPane.add(lblEntrezVotrePseudo);
		
		JButton btnSeConnecter = new JButton("Se connecter");
		btnSeConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pseudo = textField.getText();
				String ip = textField_1.getText();
				String port = textField_2.getText();
				if(pseudo.equals("") || port.equals("") || ip.equals(""))
					JOptionPane.showMessageDialog(getC().getVuePseudo(), "Tous les champs sont obligatoires", "Erreur", JOptionPane.WARNING_MESSAGE);
				else {
					if(isValid(port)){
						getC().connexion(pseudo, ip, port);
					}
					else
						JOptionPane.showMessageDialog(getC().getVuePseudo(), "Port invalide", "Erreur", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnSeConnecter.setBounds(104, 222, 150, 25);
		contentPane.add(btnSeConnecter);
		
		textField_1 = new JTextField();
		textField_1.setBounds(104, 99, 150, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblAdresseIpServeur = new JLabel("Adresse IP serveur");
		lblAdresseIpServeur.setBounds(114, 82, 150, 15);
		contentPane.add(lblAdresseIpServeur);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(104, 161, 150, 19);
		contentPane.add(textField_2);
		
		lblPort = new JLabel("Port");
		lblPort.setBounds(163, 141, 150, 15);
		contentPane.add(lblPort);
	}


	public Controller getC() {
		return c;
	}


	public void setC(Controller c) {
		this.c = c;
	}
	
	/**
	 * vérifie si un port est valide
	 * @param port
	 * @return boolean
	 */
	private boolean isValid(String port){
		boolean res = true;
		char s[] = port.toCharArray();
		if(s.length>5){
			res = false;
		}
		for(int i = 0; i<s.length; i++){
			if(!Character.isDigit(s[i])){
				res=false;
			}
		}
		return res;
	}
}
