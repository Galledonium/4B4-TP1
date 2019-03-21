package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import main.Client;
import main.Fichier;

class FichierTest {

	@Test
	public void testAjoutClientDansListeClients() throws IOException {
		Fichier fichier = new Fichier();
		
		fichier.lire();

		ArrayList<Client> liste = fichier.getListeClients();
		
		for(Client client : liste) {
			
			assertNotNull(client);
			
		}
	}

}
