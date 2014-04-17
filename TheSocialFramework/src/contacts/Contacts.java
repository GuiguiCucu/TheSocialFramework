package contacts;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.jcsv.reader.CSVReader;
import com.googlecode.jcsv.writer.CSVWriter;
import com.googlecode.jcsv.writer.internal.CSVWriterBuilder;
import com.googlecode.jcsv.reader.internal.CSVReaderBuilder;
import com.googlecode.jcsv.reader.internal.DefaultCSVEntryParser;
import com.googlecode.jcsv.CSVStrategy;

/**
 * 
 * @author montalda
 * 
 */
public class Contacts {

	private List contacts;

	/**
	 * Permet d'importer un fichier CSV contenant les contacts issus de Gmail
	 * 
	 * @param fichier
	 */
	public void importer(String fichier) {

		setContacts(new ArrayList<Personne>());

		try {
			Reader reader = new FileReader(fichier);
			CSVStrategy strategy = new CSVStrategy(',', '"', '#', true, false);

			CSVReader<String[]> csvParser = new CSVReaderBuilder(reader)
					.strategy(strategy)
					.entryParser(new DefaultCSVEntryParser()).build();

			List<String[]> data = csvParser.readAll();

			for (int i = 1; i < data.size() - 1; i += 2) {
				String nom = data.get(i)[3].replaceAll("[\u0000-\u001f]", "");
				String prenom = data.get(i)[1]
						.replaceAll("[\u0000-\u001f]", "");
				String mail = data.get(i)[28].replaceAll("[\u0000-\u001f]", "");

				creerPersonne(nom, prenom, mail);
			}
			
			csvParser.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Permet l'export de la liste de ocntacts dans un fichier csv
	 * 
	 * @param fichier
	 */
	public void exporter(String fichier) {
		if (getContacts().isEmpty()) {
			throw new IllegalArgumentException(
					"Il n'y a aucun contact a exporter");
		} else {
				
			try {
				
				Writer writer = new FileWriter(fichier);
				
				CSVWriter<Personne> csvWriter = new CSVWriterBuilder<Personne>(writer).entryConverter(new PersonneEntryConverter()).build();
				csvWriter.writeAll(getContacts());
				
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}

	}

	/**
	 * accesseur de la liste de contacts 
	 * @return contacts
	 */
	public List<Personne> getContacts() {
		return contacts;
	}

	/**
	 * mutateur de liste de contacts
	 * @param contacts
	 */
	public void setContacts(List<Personne> contacts) {
		this.contacts = contacts;
	}
	

	/**
	 * Cree une nouvelle personne et ajoute cette personne a la liste de contacts
	 * @param nom
	 * @param prenom
	 * @param adresse
	 */
	public void creerPersonne(String nom, String prenom, String adresse){
		Personne p = new Personne(nom, prenom, adresse);
		getContacts().add(p);
	}

}
