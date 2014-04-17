package dropbox;
//
////Include the Dropbox SDK.
//import com.dropbox.core.*;
//import java.io.*;
//import java.util.Locale;
//
///**
// * Permet l'utilisateur d'avoir accès à son contenu sur son compte Dropbox
// * 
// * @author montalda
// * 
// */
//public class Dropbox {
//
//	public DbxClient client;
//	public DbxWebAuthNoRedirect webAuth;
//	public DbxRequestConfig config;
//
//	/**
//	 * Identification pour accéder à l'application de la Dropbox se rendre à
//	 * l'url retournée et cliquer sur Allow puis copier le code renvoyé
//	 * 
//	 * @param APP_KEY
//	 * @param APP_SECRET
//	 * @throws IOException
//	 * @throws DbxException
//	 * @return authorizeUrl
//	 */
//	public String identification(String APP_KEY, String APP_SECRET)
//			throws IOException, DbxException {
//
//		DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
//
//		config = new DbxRequestConfig("JavaTutorial/1.0", Locale.getDefault()
//				.toString());
//		webAuth = new DbxWebAuthNoRedirect(config, appInfo);
//
//		// Have the user sign in and authorize your app.
//		String authorizeUrl = webAuth.start();
//		// String code = new BufferedReader(new
//		// InputStreamReader(System.in)).readLine().trim();
//
//		return authorizeUrl;
//
//	}
//
//	public void acces(String code) {
//		try {
//			// This will fail if the user enters an invalid authorization code.
//			DbxAuthFinish authFinish = webAuth.finish(code);
//			String accessToken = authFinish.accessToken;
//
//			client = new DbxClient(config, accessToken);
//
//			System.out.println("Linked account: "
//					+ client.getAccountInfo().displayName);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void upload(String fichier, String newFichier) {
//
//		try {
//			File inputFile = new File(fichier);
//			FileInputStream inputStream = new FileInputStream(inputFile);
//			try {
//				DbxEntry.File uploadedFile = client.uploadFile(newFichier,
//						DbxWriteMode.add(), inputFile.length(), inputStream);
//				System.out.println("Uploaded: " + uploadedFile.toString());
//			} finally {
//				inputStream.close();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void liste() {
//		try {
//			DbxEntry.WithChildren listing = client.getMetadataWithChildren("/");
//			System.out.println("Files in the root path:");
//			for (DbxEntry child : listing.children) {
//				System.out.println("	" + child.name + ": " + child.toString());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void download() {
//		try {
//			FileOutputStream outputStream = new FileOutputStream(
//					"magnum-opus.txt");
//			try {
//				DbxEntry.File downloadedFile = client.getFile(
//						"/magnum-opus.txt", null, outputStream);
//				System.out.println("Metadata: " + downloadedFile.toString());
//			} finally {
//				outputStream.close();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//}







import com.dropbox.core.*;
import java.io.*;
import java.util.Locale;

public class Dropbox {
    public static void main(String[] args) throws IOException, DbxException {
        // Get your app key and secret from the Dropbox developers website.
        final String APP_KEY = "tajxrbh3ovhxxxp";
        final String APP_SECRET = "password";

        DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);

        DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0",
            Locale.getDefault().toString());
        DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);

        // Have the user sign in and authorize your app.
        String authorizeUrl = webAuth.start();
        System.out.println("1. Go to: " + authorizeUrl);
        System.out.println("2. Click \"Allow\" (you might have to log in first)");
        System.out.println("3. Copy the authorization code.");
        String code = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();

        // This will fail if the user enters an invalid authorization code.
        DbxAuthFinish authFinish = webAuth.finish(code);
        String accessToken = authFinish.accessToken;

        DbxClient client = new DbxClient(config, accessToken);

        System.out.println("Linked account: " + client.getAccountInfo().displayName);

        File inputFile = new File("working-draft.txt");
        FileInputStream inputStream = new FileInputStream(inputFile);
        try {
            DbxEntry.File uploadedFile = client.uploadFile("/magnum-opus.txt",
                DbxWriteMode.add(), inputFile.length(), inputStream);
            System.out.println("Uploaded: " + uploadedFile.toString());
        } finally {
            inputStream.close();
        }

        DbxEntry.WithChildren listing = client.getMetadataWithChildren("/");
        System.out.println("Files in the root path:");
        for (DbxEntry child : listing.children) {
            System.out.println("	" + child.name + ": " + child.toString());
        }

        FileOutputStream outputStream = new FileOutputStream("magnum-opus.txt");
        try {
            DbxEntry.File downloadedFile = client.getFile("/magnum-opus.txt", null,
                outputStream);
            System.out.println("Metadata: " + downloadedFile.toString());
        } finally {
            outputStream.close();
        }
    }
}
