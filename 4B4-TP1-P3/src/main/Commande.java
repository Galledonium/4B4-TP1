package main;

public class Commande {

	private Client client;
	private Plat plat;
	private int qteCommande;
	
	public Commande(int qteCommande) {
		
		this.qteCommande = qteCommande;
		
	}
	
	public Commande(Client client, Plat plat, int qteCommande) {
		
		this.client = client;
		this.plat = plat;
		this.qteCommande = qteCommande;
		
	}
	
	public void setClient (Client client) {
		this.client = client;
	}
	
	public void setPlat (Plat plat) {
		this.plat = plat;
	}
	
	public void setQteCommande (int qteCommande) {
		this.qteCommande = qteCommande;
	}
	
	public Client getClient () {
		return this.client;
	}
	
	public Plat getPlat () {
		return this.plat;
	}
	
	public int getQteCommande () {
		return this.qteCommande;
	}

}
