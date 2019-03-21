package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import main.Client;
import main.Fichier;
import main.Plat;

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
	
	@Test
	public void testAjoutClientDansListePlats() throws IOException {
		Fichier fichier = new Fichier();
		
		fichier.lire();

		ArrayList<Plat> liste = fichier.getListePlats();
		
		for(Plat plat : liste) {
			
			assertNotNull(plat);
			
		}
	}

}
