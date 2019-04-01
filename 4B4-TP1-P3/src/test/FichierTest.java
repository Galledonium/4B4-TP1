package test;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import main.Client;
import main.Commande;
import main.Fichier;
import main.Plat;

public class FichierTest {
	
	private Fichier fichier;
	
	@Before
	public void setUp() throws IOException {
		
		fichier = new Fichier();
		fichier.lire();
		
	}

	@Test
	public void testAjoutClientDansListeClients() throws IOException {
		

		ArrayList<Client> liste = fichier.getListeClients();
		
		for(Client client : liste) {
			
			assertNotNull(client);
			
		}
	}
	
	
	
	@Test
	public void testAjoutClientDansListePlats() throws IOException {

		ArrayList<Plat> liste = fichier.getListePlats();
		
		for(Plat plat : liste) {
			
			assertNotNull(plat);
			
		}
	}
	
	@Test
	public void testAjoutClientDansListeCommandes() throws IOException {

		ArrayList<Commande> liste = fichier.getListeCommandes();
		
		for(Commande commande : liste) {
			
			assertNotNull(commande);
			
		}
	}
	
	@Test
	public void testNombreClientsDansListeClientsApresLectureFichier () throws IOException {
		
		assertEquals(3, fichier.getClientListSize());
	}
	
	@Test
	public void testNombreClientsDansListePlatsApresLectureFichier () throws IOException {
		
		assertEquals(3, fichier.getPlatListSize());
	}
	
	@Test
	public void testNombreClientsDansListeCommandesApresLectureFichier () throws IOException {
		
		assertEquals(3, fichier.getCommandeListSize());
		
	}
	
	@Test
	public void testSousTotalTPSTVQTotalClient() throws IOException{

		assertEquals(4, fichier.getListeClients().get(0).getTabPrix().length);
		
	}
	
	@Test
	public void testEcritureDansFichierFacture() throws IOException{
		
		fichier.ecrireFichier();
		
		assertEquals(4, fichier.getListeClients().get(0).getTabPrix().length);
		
	}
	
	@Test
	public void testMethodeVerifierCommande () {
		String [] tab = new String[3];
		
		// Commande avec client inexistant, plat inexistant et quantité de commande entier
		tab[0] = "Jeff";
		tab[1] = "Poutine";
		tab[2] = "2";
		
		String messageErreur = "\nLa commande (Jeff Poutine 2) est invalide car le client n'existe pas.\n";
		
		assertEquals(messageErreur, fichier.verifierCommande(tab));
		assertEquals(1, fichier.getListeMessagesErreur().size());
		
		// Commande avec client existant, plat inexistant et quantité de commande entier
		tab[0] = "Roger";
		tab[1] = "Bacon";
		tab[2] = "2";
				
		messageErreur = "\nLa commande (Roger Bacon 2) est invalide car le plat n'existe pas.\n";
				
		assertEquals(messageErreur, fichier.verifierCommande(tab));
		assertEquals(2, fichier.getListeMessagesErreur().size());
		
		// Commande avec client et plat inexistant, mais quantité de commande entier
		tab[0] = "Jeff";
		tab[1] = "Bacon";
		tab[2] = "2";
		
		messageErreur = "\nLa commande (Jeff Bacon 2) est invalide car le client et le plat n'existent pas.\n";
		
		assertEquals(messageErreur, fichier.verifierCommande(tab));
		assertEquals(3, fichier.getListeMessagesErreur().size());
	}
	
	@After
	public void tearDown() {
		
		fichier = null;
		
	}

}
