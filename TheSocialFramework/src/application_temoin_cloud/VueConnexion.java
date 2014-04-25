package application_temoin_cloud;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;

import java.awt.Font;
import java.io.IOException;

public class VueConnexion extends JFrame {

	private JPanel contentPane;
	/**
	 * @wbp.nonvisual location=-39,49
	 */
	private final JTextField textField = new JTextField();
	private JTextField txtLogin;
	private JPasswordField pwdPassword;
	private JLabel lblExportImport;

	private Controller c;

	/**
	 * Create the frame.
	 */
	public VueConnexion(Controller c) {
		this.c = c;
		textField.setColumns(10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCloud = new JLabel("CLOUD");
		lblCloud.setFont(new Font("Calibri", Font.PLAIN, 29));
		lblCloud.setBounds(171, 11, 110, 48);
		contentPane.add(lblCloud);

		JButton btnSeConnecter = new JButton("Se connecter");
		btnSeConnecter.setFont(new Font("Calibri", Font.PLAIN, 13));

		btnSeConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String login = txtLogin.getText();
				String pwd = pwdPassword.getText();
				//getC().setCurrentUser(login, pwd);
				getC().connexion(login, pwd);
			}
		});
		btnSeConnecter.setBounds(237, 228, 127, 23);
		contentPane.add(btnSeConnecter);

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

		lblExportImport = new JLabel("Export / Import de fichiers");
		lblExportImport.setFont(new Font("Calibri", Font.ITALIC, 12));
		lblExportImport.setBounds(140, 43, 167, 14);
		contentPane.add(lblExportImport);
		
		JButton btnSinscrire = new JButton("S'inscrire");
		btnSinscrire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnSinscrire.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnSinscrire.setBounds(81, 228, 89, 23);
		contentPane.add(btnSinscrire);

	}

	public Controller getC() {
		return c;
	}

	public void setC(Controller c) {
		this.c = c;
	}
}
