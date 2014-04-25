package application_temoin_cloud;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class VueCloud extends JFrame {

	private JPanel contentPane;
	private Controller c;
	private JList<String> listeDocuments;
	private DefaultListModel modelListeDocuments;

	/**
	 * Create the frame.
	 */
	public VueCloud(Controller c) {
		this.setC(c);
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
		
		listeDocuments = new JList();
		listeDocuments.setToolTipText("yes");
		listeDocuments.setSelectedIndex(1);
		listeDocuments.setBounds(37, 70, 425, 332);
		getC().initialiserContenuRepertoire();
		contentPane.add(listeDocuments);
		
		modelListeDocuments = new DefaultListModel();
		listeDocuments.setModel(modelListeDocuments);
		
		JButton btnExporter = new JButton("Télécharger");
		btnExporter.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnExporter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getC().telechargement("hey.txt");
			}
		});
		btnExporter.setBounds(100, 431, 110, 23);
		contentPane.add(btnExporter);
		
		JButton btnUploader = new JButton("Uploader");
		btnUploader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					getC().fileChooser();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnUploader.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnUploader.setBounds(305, 431, 100, 23);
		contentPane.add(btnUploader);
		
		JButton btnSeDconnecter = new JButton("Se déconnecter");
		btnSeDconnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getC().deconnexion();
			}
		});
		btnSeDconnecter.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnSeDconnecter.setBounds(37, 479, 425, 23);
		contentPane.add(btnSeDconnecter);

	listeDocuments.addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent evt) {
	        if (evt.getClickCount() == 2) {
	        	getC().actualiserContenuRepertoire(listeDocuments.getSelectedValue());
	        } 
	    }
	});
	}
	
	public Controller getC() {
		return c;
	}

	public void setC(Controller c) {
		this.c = c;
	}

	public void alimenteContenu() {
		// TODO alimenter la vue avec des représentations de dossiers, fichiers
		
	}

	public void alimenteDocuments(ArrayList<String> dossiers,
			HashMap<String, Long> fichiers) {
		for(String dossier: dossiers){
			this.modelListeDocuments.addElement(dossier);
			}
		
	}
}