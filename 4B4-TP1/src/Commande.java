
public class Commande {

	private Client client;
	private Plat plat;
	private int qteCommande;
	
	public Commande(Client client, Plat plat, int qteCommande) {
		
		this.client = client;
		this.plat = plat;
		this.qteCommande = qteCommande;
		
	}
	
	public void afficherCommande() {
		
		System.out.println( "Bienvenue chez Barette!" + "\n"
						  + this.client.getNom() + " " + (this.plat.getPrix() * this.qteCommande) + "$");
		
	}
	
}
