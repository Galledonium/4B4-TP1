package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import main.Client;
import main.Commande;
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
	
	@Test
	public void testAjoutClientDansListeCommandes() throws IOException {
		Fichier fichier = new Fichier();
		
		fichier.lire();

		ArrayList<Commande> liste = fichier.getListeCommandes();
		
		for(Commande commande : liste) {
			
			assertNotNull(commande);
			
		}
	}
	
	@Test
	public void testNombreClientsDansListeClientsApresLectureFichier () throws IOException {
		Fichier fichier = new Fichier();

		fichier.lire();
		
		assertEquals(3, fichier.getClientListSize());
	}

}
