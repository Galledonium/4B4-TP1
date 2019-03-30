package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Client;
import main.Commande;
import main.Fichier;
import main.Plat;

class FichierTest {
	
	private Fichier fichierTest;
	
	@BeforeEach
	public void setUp() throws IOException {
		
		fichierTest = new Fichier();
		fichierTest.lire();
		System.out.println("test");
		
	}

	@Test
	public void testAjoutClientDansListeClients() throws IOException {
		

		ArrayList<Client> liste = fichierTest.getListeClients();
		
		for(Client client : liste) {
			
			assertNotNull(client);
			
		}
	}
	
	
	
	@Test
	public void testAjoutClientDansListePlats() throws IOException {

		ArrayList<Plat> liste = fichierTest.getListePlats();
		
		for(Plat plat : liste) {
			
			assertNotNull(plat);
			
		}
	}
	
	@Test
	public void testAjoutClientDansListeCommandes() throws IOException {

		ArrayList<Commande> liste = fichierTest.getListeCommandes();
		
		for(Commande commande : liste) {
			
			assertNotNull(commande);
			
		}
	}
	
	@Test
	public void testNombreClientsDansListeClientsApresLectureFichier () throws IOException {
		
		assertEquals(3, fichierTest.getClientListSize());
	}
	
	@Test
	public void testNombreClientsDansListePlatsApresLectureFichier () throws IOException {
		
		assertEquals(3, fichierTest.getPlatListSize());
	}
	
	@Test
	public void testNombreClientsDansListeCommandesApresLectureFichier () throws IOException {
		
		assertEquals(3, fichierTest.getCommandeListSize());
		
	}
	
	@Test
	public void testSousTotalTPSTVQTotalClient() throws IOException{

		assertEquals(4, fichierTest.getListeClients().get(0).getTabPrix().length);
		
	}
	
	@Test
	public void testEcritureDansFichierFacture() throws IOException{
		
		fichierTest.ecrireFichier();
		
		assertEquals(4, fichierTest.getListeClients().get(0).getTabPrix().length);
		
	}
	
	@AfterEach
	public void tearDown() {
		
		fichierTest = null;
		
	}

}
