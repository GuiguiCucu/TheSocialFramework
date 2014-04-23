package application_temoin_cloud;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VueCloud extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VueCloud frame = new VueCloud();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VueCloud() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 551);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("CLOUD");
		label.setFont(new Font("Calibri", Font.PLAIN, 29));
		label.setBounds(217, 11, 110, 48);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Export / Import de fichiers");
		label_1.setFont(new Font("Calibri", Font.ITALIC, 12));
		label_1.setBounds(200, 44, 144, 14);
		contentPane.add(label_1);
		
		JList list = new JList();
		list.setToolTipText("yes");
		list.setSelectedIndex(1);
		list.setBounds(37, 70, 425, 332);
		contentPane.add(list);
		
		JButton btnExporter = new JButton("Télécharger");
		btnExporter.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnExporter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExporter.setBounds(100, 431, 110, 23);
		contentPane.add(btnExporter);
		
		JButton btnUploader = new JButton("Uploader");
		btnUploader.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnUploader.setBounds(305, 431, 100, 23);
		contentPane.add(btnUploader);
		
		JButton btnSeDconnecter = new JButton("Se déconnecter");
		btnSeDconnecter.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnSeDconnecter.setBounds(37, 479, 425, 23);
		contentPane.add(btnSeDconnecter);
	}
}