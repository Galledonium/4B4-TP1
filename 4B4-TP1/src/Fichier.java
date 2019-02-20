import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Fichier {
	private String chemin;
	private String ligne;
	
	private BufferedReader reader;
	
	private ArrayList<Client> listeClients = new ArrayList<Client>();
	private ArrayList<Plat> listePlats = new ArrayList<Plat>();
	private ArrayList<Commande> listeCommandes = new ArrayList<Commande>();
	
	public Fichier (String chemin) throws IOException {
		this.chemin = chemin;
		
		reader = new BufferedReader( new FileReader(this.chemin));
		
		this.ligne = reader.readLine();
		
		this.lireClients();
		
	}
	
	private void lireClients () throws IOException {
		
		// Si la première ligne est "Clients :" on se crée des objets clients tant qu'on est pas rendu à "Plats"
		try {
			
			if (ligne.equals("Clients :")) {
				
				ligne = reader.readLine();
				
				while (!ligne.equals("Plats :")) {
					
					Client client = new Client (ligne);
					System.out.println(client.getNom());
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
					System.out.println(plat.getRepas() + " " + plat.getPrix());
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
			
			if (ligne.equals("Commandes :")) {
				
				ligne = reader.readLine();
				
				while (!ligne.equals("Fin")) {
					
					tab = ligne.split(" ");

					for (Client client : listeClients) {
						
						if (client.getNom().equals(tab[0])) {
							
							for (Plat plat : listePlats) {
								
								if (plat.getRepas().equals(tab[1])) {
									
									Commande commande = new Commande (client, plat, Integer.parseInt(tab[2]));
									System.out.println(commande.getClient().getNom() + " " + commande.getPlat().getRepas() + " " + commande.getQteCommande());
									listeCommandes.add(commande);
								}
								
							}
							
						}
						
					}
					
					ligne = reader.readLine();
					
				}				
				
			}
			
		} finally {
			reader.close();
		}
	
	}
	
	private void chercherCommande (Client client) {
		// La liste d'indice ou le client reçu en paramètre est répertorié
		ArrayList<Integer> indices = new ArrayList<Integer>();
		int indice = 0;
		
		for (Commande commande : listeCommandes) { // Pour toutes les commandes dans la liste de commmandes
			if (commande.getClient().getNom() != client.getNom()) { // Si le client n'a pas de commande a son nom
				System.out.println("Le client " + client.getNom() + " n'a rien commandé.");
			}else if (commande.getClient().getNom() == client.getNom()) { // Si le client a une commande a son nom
				indices.add(indice); // On sauvegarde l'indice dans lequel on le retrouve dans la liste de commandes ("listeCommandes")
			}
			indice++;
		}
		
	}
	
	
	
}
