package contacts;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.jcsv.reader.CSVReader;
import com.googlecode.jcsv.reader.internal.CSVReaderBuilder;
import com.googlecode.jcsv.reader.internal.DefaultCSVEntryParser;
import com.googlecode.jcsv.CSVStrategy;

/**
 * 
 * @author montalda
 * 
 */
public class Contacts {
	
	static List contacts;

	public Contacts() {
		

	}

	/**
	 * Fonction qui permet d'importer un fichier CSV contenant les contacts
	 * issus de Gmail
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
				String prenom = data.get(i)[1].replaceAll("[\u0000-\u001f]", "");
				String mail = data.get(i)[28].replaceAll("[\u0000-\u001f]", "");
				
				Personne p = new Personne(nom, prenom, mail);
				contacts.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
