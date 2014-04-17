package dropbox.tests;

import java.io.IOException;

import com.dropbox.core.DbxException;

import dropbox.Dropbox;

public class TestDropbox {

	public static void main(String[] args) throws IOException, DbxException {
		String APP_KEY = "tajxrbh3ovhxxxp";
	    String APP_SECRET = "password";
	    
	    Dropbox dropbox = new Dropbox();
	    
	    dropbox.identification(APP_KEY, APP_SECRET);
	}

}
