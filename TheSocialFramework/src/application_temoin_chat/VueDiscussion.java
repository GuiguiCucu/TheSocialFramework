package application_temoin_chat;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VueDiscussion extends JFrame {

	private JPanel contentPane;
	private Controller c;
	private JTextArea textArea_1;
	private JTextArea textArea;

	/**
	 * Create the frame.
	 */
	public VueDiscussion(Controller c) {
		setC(c);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 551, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(32, 36, 366, 310);
		textArea.setLineWrap(true);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_2.setBounds(32, 36, 366, 310);
		contentPane.add(scrollPane_2);
		scrollPane_2.setViewportView(textArea);
		
		
		JList list = new JList();
		list.setBounds(410, 36, 112, 310);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(410, 36, 112, 310);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(list);
		
		JLabel lblFilDeDiscussion = new JLabel("Fil de discussion");
		lblFilDeDiscussion.setBounds(32, 12, 205, 15);
		contentPane.add(lblFilDeDiscussion);
		
		JLabel lblUtilisateursConnects = new JLabel("Utilisateurs");
		lblUtilisateursConnects.setBounds(410, 3, 112, 32);
		contentPane.add(lblUtilisateursConnects);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(32, 358, 366, 52);
		contentPane.add(scrollPane_1);
		
		textArea_1 = new JTextArea();
		textArea_1.setBounds(32, 359, 366, 51);
		scrollPane_1.setViewportView(textArea_1);
		textArea_1.setLineWrap(true); 
		
		JButton btnEnvoyer = new JButton("Envoyer");
		btnEnvoyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String msg = textArea_1.getText();
				if(!msg.equals(""))
					getC().send(msg);
					textArea_1.setText("");
			}
		});
		btnEnvoyer.setBounds(410, 358, 112, 52);
		contentPane.add(btnEnvoyer);
	}

	public Controller getC() {
		return c;
	}

	public void setC(Controller c) {
		this.c = c;
	}

	public void alimenteFilDiscussion(String reponse) {
		this.textArea.append(reponse+"\n");
		
	}
}
