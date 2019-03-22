package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Fichier {
	private String chemin;
	private String ligne;
	
	private BufferedReader reader;
	
	private static final String message = "Bienvenue chez Barette!\n" + "Factures:";
	
	private ArrayList<Client> listeClients = new ArrayList<Client>();
	private ArrayList<Plat> listePlats = new ArrayList<Plat>();
	private ArrayList<Commande> listeCommandes = new ArrayList<Commande>();
	
	private Client clientTemp;
	private Plat platTemp;
	
	private Path cheminFichier = Paths.get("src/fichiers", "commandes.txt");
	private final Path cheminSortie = Paths.get("src/fichiers");
	
	public Fichier () throws IOException {
		
		this.chemin = cheminFichier.toRealPath().toString();
		
		reader = new BufferedReader( new FileReader(this.chemin));
		
		this.ligne = reader.readLine();
		
	}
	
	public void lecture () throws IOException {
		this.lireClients();
	}
	
	private void lireClients () throws IOException {
		
		// Si la première ligne est "Clients :" on se crée des objets clients tant qu'on est pas rendu à "Plats"
		try {
			
			if (ligne.equals("Clients :")) {
				
				ligne = reader.readLine();
				
				while (!ligne.equals("Plats :")) {
					
					Client client = new Client (ligne);
					listeClients.add(client);
					ligne = reader.readLine();
					
				}				
				
			}
			
			this.lirePlats();
			
		} finally {
			reader.close();
		}
	}
	
	private void lirePlats () throws IOException {
		
		String [] tab;
		
		try {
			
			if (ligne.equals("Plats :")) {
				
				ligne = reader.readLine();
				
				while (!ligne.equals("Commandes :")) {
					
					tab = ligne.split(" ");

					Plat plat = new Plat (tab[0], Double.parseDouble(tab[1]));
					listePlats.add(plat);
					ligne = reader.readLine();
					
				}				
				
			}
			
			this.lireCommande();
			
		} finally {
			reader.close();
		}
	
		
	}
	
	private void lireCommande() throws IOException{
		
		String [] tab;
		
		boolean toutEstValide = true;
		
		try {
			
			if (ligne.equals("Commandes :")) {
				
				ligne = reader.readLine();
				
				while (!ligne.equals("Fin")) {
					
					tab = ligne.split(" ");
					
					if (this.clientExists(tab) && this.platExists(tab) && !this.isColle(tab)) {
									
						Commande commande = new Commande (clientTemp, platTemp, Integer.parseInt(tab[2]));
									
						listeCommandes.add(commande);
									
						this.getPrixTotaux();
									
					} else {
						System.out.println("Le fichier ne respecte pas le format demandé !");
						toutEstValide = false;
					}
					
					ligne = reader.readLine();
					
				}
				
			}
			
		} finally {
			reader.close();
		}
		
		if (toutEstValide) {
			this.afficherFactures();
		}
	
	}
	
	private ArrayList<Integer> chercherCommande (Client client) {
		// La liste d'indice ou le client reçu en paramètre est répertorié
		ArrayList<Integer> indices = new ArrayList<Integer>();
		int indice = 0;
		
		for (Commande commande : listeCommandes) { // Pour toutes les commandes dans la liste de commmandes
			if (commande.getClient().getNom() == client.getNom()) { // Si le client a une commande a son nom
				indices.add(indice); // On sauvegarde l'indice dans lequel on le retrouve dans la liste de commandes ("listeCommandes")
			}
			indice++;
		}
		
		return indices;
		
	}
	
	private void getPrixTotaux() {
		
		ArrayList<Integer> listeIndices;
		double prixTotal = 0;
		
		for(int i = 0; i < listeCommandes.size(); i++) {
			
			listeIndices = chercherCommande(listeCommandes.get(i).getClient());
			
			if(listeIndices.size() > 0) {
				
				prixTotal = 0;
				
				for(int j = 0; j < listeIndices.size(); j++) {
					
					prixTotal += (listeCommandes.get(listeIndices.get(j)).getPlat().getPrix() * listeCommandes.get(listeIndices.get(j)).getQteCommande());
					
					listeCommandes.get(listeIndices.get(j)).getClient().setPrixTotal(prixTotal);
					
				}
				
			}
			
		}
		
	}
	
	private void afficherFactures() {
		
		System.out.println(message);
		
		for(Client client : listeClients) {
			
			System.out.println(client.getNom() + " " + client.getPrixTotal() + "$");
			
		}
		
	}
	
	private boolean clientExists (String  [] tab) {
		boolean existe = false;
		
		for (Client client : listeClients) {
			
			if (client.getNom().equals(tab[0])) {
				clientTemp = client;
				existe = true;
			}
			
		}
		
		return existe;
	}
	
	private boolean platExists (String [] tab) {
		boolean existe = false;
		
		for (Plat plat : listePlats) {
			
			if (plat.getRepas().equals(tab[1])) {
				platTemp = plat;
				existe = true;
			}
			
		}
		
		return existe;
	}
	
	private boolean isColle (String [] tab) {
		boolean colle = true;
		
		if (tab.length == 3) {
			colle = false;
		}
		
		return colle;
	}
	
	public void ecrireFichier () throws IOException {
		FileWriter fichierSortie = new FileWriter(cheminSortie + "\\Facture.txt");
		BufferedWriter writer = new BufferedWriter(fichierSortie);
		
		try {
			
			writer.write(message + "\n");
			
			for(Client client : listeClients) {
				
				writer.write(client.getNom() + " " + client.getPrixTotal() + "$" + "\n");
				
			}
			
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
				
				if (fichierSortie != null) {
					fichierSortie.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
}
