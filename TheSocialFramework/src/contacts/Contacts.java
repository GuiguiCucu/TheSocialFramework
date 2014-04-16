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

	public static List contacts;

	private char separator;

	/**
	 * Permet d'importer un fichier CSV contenant les contacts issus de Gmail
	 * 
	 * @param fichier
	 */
	public static void importer(String fichier) {

		contacts = new ArrayList<Personne>();

		try {
			Reader reader = new FileReader(fichier);
			CSVStrategy strategy = new CSVStrategy(',', '"', '#', true, false);

			CSVReader csvParser = new CSVReaderBuilder(reader)
					.strategy(strategy)
					.entryParser(new DefaultCSVEntryParser()).build();

			List<String[]> data = csvParser.readAll();

			for (int i = 1; i < data.size() - 1; i += 2) {
				String nom = data.get(i)[3].replaceAll("[\u0000-\u001f]", "");
				String prenom = data.get(i)[1]
						.replaceAll("[\u0000-\u001f]", "");
				String mail = data.get(i)[28].replaceAll("[\u0000-\u001f]", "");

				Personne p = new Personne(nom, prenom, mail);
				contacts.add(p);
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
	public static void exporter(String fichier) {
		if (contacts.isEmpty()) {
			throw new IllegalArgumentException(
					"Il n'y a aucun contact à exporter");
		} else {
				
			try {
				
				Writer writer = new FileWriter(fichier+".csv");
				
				CSVWriter<Personne> csvWriter = new CSVWriterBuilder<Personne>(writer).entryConverter(new PersonneEntryConverter()).build();
				csvWriter.writeAll(contacts);
				
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}

	}

}
