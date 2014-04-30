package application_temoin_cloud;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;

import core.models.core_modele.Client;

import java.awt.Font;

public class VueInscription extends JFrame {

	private JPanel contentPane;
	/**
	 * @wbp.nonvisual location=-39,49
	 */
	private final JTextField textField = new JTextField();
	private JTextField txtLogin;
	private JPasswordField pwdPassword;
	private JPasswordField pwdPasswordBis;

	private Controller c;

	/**
	 * Create the frame.
	 */
	public VueInscription(Controller c) {
		this.c = c;
		textField.setColumns(10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCloud = new JLabel(" Inscription");
		lblCloud.setFont(new Font("Calibri", Font.PLAIN, 29));
		lblCloud.setBounds(171, 11, 190, 48);
		contentPane.add(lblCloud);

		JButton btnInscription = new JButton("S'inscrire");
		btnInscription.setFont(new Font("Calibri", Font.PLAIN, 13));

		btnInscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String login = txtLogin.getText();
				String pwd = pwdPassword.getText();
				String pwdBis = pwdPasswordBis.getText();
				getC().inscription(login, pwd, pwdBis);
			}
		});
		btnInscription.setBounds(237, 228, 127, 23);
		contentPane.add(btnInscription);

		txtLogin = new JTextField();
		txtLogin.setFont(new Font("Calibri", Font.PLAIN, 11));
		txtLogin.setToolTipText("cxxcxc");
		txtLogin.setText("login");
		txtLogin.setBounds(169, 114, 86, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);

		pwdPassword = new JPasswordField();
		pwdPassword.setFont(new Font("Calibri", Font.PLAIN, 11));
		pwdPassword.setText("password");
		pwdPassword.setBounds(169, 145, 86, 20);
		contentPane.add(pwdPassword);
		
		pwdPasswordBis = new JPasswordField();
		pwdPasswordBis.setFont(new Font("Calibri", Font.PLAIN, 11));
		pwdPasswordBis.setText("Password Bis");
		pwdPasswordBis.setBounds(169, 176, 86, 20);
		contentPane.add(pwdPasswordBis);
	}

	public Controller getC() {
		return c;
	}

	public void setC(Controller c) {
		this.c = c;
	}
}
