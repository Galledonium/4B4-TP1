package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Fichier {
	private String chemin;
	private String ligne;
	
	private BufferedReader reader;
	
	private static final String message = "Bienvenue chez Barette!";
	private String messageErreur;
	
	private ArrayList<Client> listeClients = new ArrayList<Client>();
	private ArrayList<Plat> listePlats = new ArrayList<Plat>();
	private ArrayList<Commande> listeCommandes = new ArrayList<Commande>();
	private ArrayList<String> listeMessagesErreur = new ArrayList<String>();
	
	private Client clientTemp;
	private Plat platTemp;
	
	private Path cheminFichier = Paths.get("src/fichiers", "commandes.txt");
	private final Path cheminSortie = Paths.get("src/fichiers");
	
	public Fichier () throws IOException {
		
		this.chemin = cheminFichier.toRealPath().toString();
		
		reader = new BufferedReader( new FileReader(this.chemin));
		
		this.ligne = reader.readLine();
		
	}
	
	public void lire () throws IOException {
		this.lireClients();
	}
	
	private void lireClients () throws IOException {
		
		// Si la premi�re ligne est "Clients :" on se cr�e des objets clients tant qu'on est pas rendu � "Plats"
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
		
		try {
			
			System.out.print(message + "\n");
			
			if (ligne.equals("Commandes :")) {
				
				ligne = reader.readLine();
				
				while (!ligne.equals("Fin")) {
					
					tab = ligne.split(" ");
					
					verifierCommande(tab);
					
					ligne = reader.readLine();
					
				}
				
			}
			
			for(String erreur : listeMessagesErreur) {
				
				System.out.print(erreur);
				
			}
			
			System.out.println();
			
			this.afficherFactures();
			
		} finally {
			reader.close();
			
		}
		
	
	}
	
	private ArrayList<Integer> chercherCommande (Client client) {
		// La liste d'indice ou le client re�u en param�tre est r�pertori�
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
	
	public ArrayList<Client> getListeClients() {
		
		return listeClients;
		
	}
	
	public ArrayList<Plat> getListePlats() {
		
		return listePlats;
		
	}

	public ArrayList<Commande> getListeCommandes() {
	
		return listeCommandes;
	
	}
	
	public int getClientListSize() {
		
		return listeClients.size();
		
	}
	
	public int getPlatListSize() {
		
		return listePlats.size();
		
	}
	
	public int getCommandeListSize() {
		
		return listeCommandes.size();
		
	}
	
	public ArrayList<String> getListeMessagesErreur (){
		
		return listeMessagesErreur;
		
	}
	
	private void afficherFactures() {
		
		String facture = "";
		
		for(Client client : listeClients) {
			
			facture = "Facture de : " + client.getNom() + " \n"
					+ "============================" + " \n"
					+ "Sous-total : \t" + "| " + client.getTabPrix()[0] + " $\n"
					+ "TPS : \t\t" + "| " + client.getTabPrix()[1] + " $\n"
					+ "TVQ : \t\t" + "| " + client.getTabPrix()[2] + " $\n"
					+ "Prix total : \t" + "| " + client.getTabPrix()[3] + " $\n\n";
			
			System.out.print(facture);
			
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
		DateTimeFormatter formateDate = DateTimeFormatter.ofPattern("dd MM yyyy");
		DateTimeFormatter formateHeure = DateTimeFormatter.ofPattern("HH");
		
		LocalDate dateActuelle = LocalDate.now();
		LocalTime heureActuel = LocalTime.now();
		
		String extensionFichier = ".txt";
		
		String nomFichier = "Facture-du-[" + formateDate.format(dateActuelle) + "]-[" + formateHeure.format(heureActuel) + "]" + extensionFichier;
		
		FileWriter fichierSortie = new FileWriter(cheminSortie + "\\" + nomFichier);
		BufferedWriter writer = new BufferedWriter(fichierSortie);
		
		try {
			
			String facture = "";
			
			writer.write(message + "\n");
			
			for(String erreur : listeMessagesErreur) {
				
				writer.write(erreur);
				
			}
			
			writer.write("\n");
				
			for(Client client : listeClients) {
				
				if(client.getTabPrix()[3] > 0) {
					
					facture = "Facture de : " + client.getNom() + " \n"
							+ "============================" + " \n"
							+ "Sous-total : \t" + "| " + client.getTabPrix()[0] + " $\n"
							+ "TPS : \t\t" + "| " + client.getTabPrix()[1] + " $\n"
							+ "TVQ : \t\t" + "| " + client.getTabPrix()[2] + " $\n"
							+ "Prix total : \t" + "| " + client.getTabPrix()[3] + " $\n\n";
					
					writer.write(facture);
					
				}
				
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
	
	public String verifierCommande(String[] tab) {
		
		messageErreur = "";
		
		if(!this.clientExists(tab) && this.platExists(tab) && Double.parseDouble(tab[2]) % 1 == 0) {
			
			messageErreur = "\nLa commande (" + tab[0] + " " + tab[1] + " " + tab[2] + ") est invalide car le client n'existe pas.\n";
			
			listeMessagesErreur.add(messageErreur);
			
		}else if(!this.platExists(tab) && this.clientExists(tab) && Double.parseDouble(tab[2]) % 1 == 0) {
			
			messageErreur = "\nLa commande (" + tab[0] + " " + tab[1] + " " + tab[2] + ") est invalide car le plat n'existe pas.\n";
			
			listeMessagesErreur.add(messageErreur);
			
		}else if(!this.clientExists(tab) && !this.platExists(tab) && Double.parseDouble(tab[2]) % 1 == 0) {
			
			messageErreur = "\nLa commande (" + tab[0] + " " + tab[1] + " " + tab[2] + ") est invalide car le client et le plat n'existent pas.\n";
			
			listeMessagesErreur.add(messageErreur);
			
		}else if(!(Double.parseDouble(tab[2]) % 1 == 0) && this.clientExists(tab) && this.platExists(tab)) {
			
			messageErreur = "\nLa commande (" + tab[0] + " " + tab[1] + " " + tab[2] + ") est invalide car la quantit� dans la commande est erron�e.\n";
			
			listeMessagesErreur.add(messageErreur);
			
		}else if(!(Double.parseDouble(tab[2]) % 1 == 0) && !this.clientExists(tab) && this.platExists(tab)){
			
			messageErreur = "\nLa commande (" + tab[0] + " " + tab[1] + " " + tab[2] 
					+ ") est invalide car le client n'existe pas et quantit� dans la commande est erron�e.\n";
			
			listeMessagesErreur.add(messageErreur);
			
		}else if(!(Double.parseDouble(tab[2]) % 1 == 0) && this.clientExists(tab) && !this.platExists(tab)) {
		
			messageErreur = "\nLa commande (" + tab[0] + " " + tab[1] + " " + tab[2] 
					+ ") est invalide car le plat n'existe pas et quantit� dans la commande est erron�e.\n";
			
			listeMessagesErreur.add(messageErreur);
		
		}else if(this.isColle(tab)) {
			
			String msg = "";
			
			for(int i = 0; i < tab.length; i++) {
				
				msg += tab[i];
				
			}
			
			messageErreur = "La commande (" + msg + ") est invalide car le format de la commande est erron�e.\n\n";
			
			listeMessagesErreur.add(messageErreur);
			
		}else if (this.clientExists(tab) && this.platExists(tab) && !this.isColle(tab)) {
			
			Commande commande = new Commande (clientTemp, platTemp, Integer.parseInt(tab[2]));
						
			listeCommandes.add(commande);
						
			this.getPrixTotaux();
			
		}
		
		return messageErreur;
		
	}
	
}
