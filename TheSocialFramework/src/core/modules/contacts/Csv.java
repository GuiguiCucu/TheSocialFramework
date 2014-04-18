package core.modules.contacts;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.jcsv.CSVStrategy;
import com.googlecode.jcsv.reader.CSVReader;
import com.googlecode.jcsv.reader.internal.CSVReaderBuilder;
import com.googlecode.jcsv.reader.internal.DefaultCSVEntryParser;
import com.googlecode.jcsv.writer.CSVWriter;
import com.googlecode.jcsv.writer.internal.CSVWriterBuilder;

public class Csv {
	
	/**
	 * Permet d'importer un fichier CSV contenant les contacts issus de Gmail
	 * 
	 * @param fichier
	 */
	public static void importer(String fichier, Contacts contacts) {

		contacts.setContacts(new ArrayList<Personne>());

		try {
			Reader reader = new FileReader(fichier);
			CSVStrategy strategy = new CSVStrategy(',', '"', '#', true, false);

			CSVReader<String[]> csvParser = new CSVReaderBuilder(reader).strategy(strategy).entryParser(new DefaultCSVEntryParser()).build();

			List<String[]> data = csvParser.readAll();

			for (int i = 1; i < data.size() - 1; i += 2) {
				String nom = data.get(i)[3].replaceAll("[\u0000-\u001f]", "");
				String prenom = data.get(i)[1]
						.replaceAll("[\u0000-\u001f]", "");
				String mail = data.get(i)[28].replaceAll("[\u0000-\u001f]", "");
				
				Personne p = new Personne(nom, prenom, mail);

				contacts.ajouterPersonne(p);
			}
			
			csvParser.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Permet l'export de la liste de contacts dans un fichier csv
	 * 
	 * @param fichier
	 */
	public static void exporter(String fichier, Contacts contatcs) {
		if (contatcs.getContacts().isEmpty()) {
			throw new IllegalArgumentException(
					"Il n'y a aucun contact a exporter");
		} else {
				
			try {
				
				Writer writer = new FileWriter(fichier);
				
				CSVWriter<Personne> csvWriter = new CSVWriterBuilder<Personne>(writer).entryConverter(new PersonneEntryConverter()).build();
				csvWriter.writeAll(contatcs.getContacts());
				
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}

	}

}
