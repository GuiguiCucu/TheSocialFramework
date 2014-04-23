package core.models.modules.module_contacts;

import com.googlecode.jcsv.writer.CSVEntryConverter;

public class PersonneEntryConverter implements CSVEntryConverter<Personne> {

	@Override
	public String[] convertEntry(Personne p) {
		String[] columns = new String[3];

	    columns[0] = p.getNom();
	    columns[1] = p.getPrenom();
	    columns[2] = p.getAdresse();

	    return columns;
	}

}
