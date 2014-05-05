package core.models.init_modele;

import javax.swing.*;

import application_temoin_cloud.Controller;

import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

public class InstallationFramework extends JPanel implements ActionListener {
	JButton go;

	JFileChooser chooser;
	String choosertitle;

	public InstallationFramework() {
		go = new JButton("Choisissez le projet");
		go.addActionListener(this);
		add(go);
	}

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
			copieRecursive(new java.io.File("../src/core"), lib);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null,
				"Framework installé dans le projet", "Succès",
				JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}

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

	public Dimension getPreferredSize() {
		return new Dimension(200, 200);
	}

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