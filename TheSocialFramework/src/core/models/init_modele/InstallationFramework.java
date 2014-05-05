package core.models.init_modele;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Classe permettant la production d'un .jar permettant l'installation du framework dans un projet Java
 * @author forestip
 *
 */
public class InstallationFramework extends JPanel implements ActionListener {
	JButton go;

	JFileChooser chooser;
	String choosertitle;

	/**
	 * Méthode d'initialisation graphique
	 */
	public InstallationFramework() {
		go = new JButton("Choisissez le projet");
		go.addActionListener(this);
		add(go);
	}

	/**
	 * Action lors de la séléction du répertoire
	 */
	public void actionPerformed(ActionEvent e) {
		int result;
		chooser = new JFileChooser();
		String currentPath = System.getProperty("user.home");
		// chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setCurrentDirectory(new java.io.File(currentPath));
		chooser.setDialogTitle(choosertitle);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			copieFramework(chooser.getSelectedFile());
		} else {
			System.out.println("Aucun dossier sélectionné ");
		}
	}

	/**
	 * Initialisation de la copie
	 * @param projectDestination la destination de la copie
	 */
	private void copieFramework(File projectDestination) {
		File controleur = new File(projectDestination.getPath()
				+ "/Controleurs");
		controleur.mkdirs();
		File modeles = new File(projectDestination.getPath() + "/Modèles");
		modeles.mkdirs();
		File vues = new File(projectDestination.getPath() + "/Vues");
		vues.mkdirs();
		File lib = new File(projectDestination.getPath() + "/Lib");
		lib.mkdirs();
		try {
			System.out.println(System.getProperty("user.dir" ));
			copieRecursive(new java.io.File("../TheSocialFramework/src/core"), lib);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null,
				"Framework installé dans le projet", "Succès",
				JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}

	/**
	 * Copie récursive
	 * @param src le fichier source
	 * @param dest le fichier destination
	 * @throws IOException Erreur lors de l'ouverture d'un fichier
	 */
	public static void copieRecursive(File src, File dest) throws IOException {
		if (src.isDirectory()) {
			System.out.println("test1");
			if (!dest.exists()) {
				System.out.println("test2");
				dest.mkdir();
			}
			String files[] = src.list();
			for (String file : files) {
				File srcFile = new File(src, file);
				File destFile = new File(dest, file);
				copieRecursive(srcFile, destFile);
			}
		} else {
			System.out.println("test3");
			InputStream in = new FileInputStream(src);
			OutputStream out = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			in.close();
			out.close();
		}
	}

	/**
	 * Dimension de la fenêtre
	 */
	public Dimension getPreferredSize() {
		return new Dimension(200, 200);
	}

	/**
	 * Exceution
	 * @param s liste d'arguments
	 */
	public static void main(String s[]) {
		JFrame frame = new JFrame("");
		InstallationFramework panel = new InstallationFramework();
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.getContentPane().add(panel, "Center");
		frame.setSize(panel.getPreferredSize());
		frame.setVisible(true);
	}
}