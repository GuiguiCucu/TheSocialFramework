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
public class Contacts<P> {

	private List<P> contacts;
	
	public Contacts(){
		this.contacts = new ArrayList<P>();
	}

	

	/**
	 * accesseur de la liste de contacts 
	 * @return contacts
	 */
	public List<P> getContacts() {
		return contacts;
	}

	/**
	 * mutateur de liste de contacts
	 * @param contacts
	 */
	public void setContacts(List<P> contacts) {
		this.contacts = contacts;
	}
	

	/**
	 * Cree une nouvelle personne et ajoute cette personne a la liste de contacts
	 * @param nom
	 * @param prenom
	 * @param adresse
	 */
	public void ajouterPersonne(P personne){
		getContacts().add(personne);
	}

}
