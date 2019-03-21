import java.io.IOException;

public class Executable {
	
	public static void main(String [] args) throws IOException {
		
		Fichier fichier = new Fichier();

		fichier.lecture();
		fichier.ecrireFichier();
	}
	
}
