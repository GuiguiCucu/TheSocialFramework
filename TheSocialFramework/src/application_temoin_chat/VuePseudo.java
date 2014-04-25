package application_temoin_chat;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VuePseudo extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private Controller c;


	/**
	 * Create the frame.
	 */
	public VuePseudo(Controller c) {
		setC(c);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 361, 194);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(104, 61, 150, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblEntrezVotrePseudo = new JLabel("Entrez votre pseudo");
		lblEntrezVotrePseudo.setBounds(104, 34, 150, 15);
		contentPane.add(lblEntrezVotrePseudo);
		
		JButton btnSeConnecter = new JButton("Se connecter");
		btnSeConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pseudo = textField.getText();
				if(!pseudo.equals("")){
					getC().connexion(pseudo);
				}
			}
		});
		btnSeConnecter.setBounds(104, 98, 150, 25);
		contentPane.add(btnSeConnecter);
	}


	public Controller getC() {
		return c;
	}


	public void setC(Controller c) {
		this.c = c;
	}

}
