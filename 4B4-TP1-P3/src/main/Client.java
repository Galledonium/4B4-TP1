package main;

public class Client {
	
	private String nom;
	private double sousTotal;
	
	public Client() {
		
		
		
	}
	
	public Client(String nom) {
		
		this.nom = nom;
		
	}
	
	public Client(String nom, double prixTotal) {
		
		this.nom = nom;
		this.sousTotal = prixTotal;
		
	}
	
	public void setNom(String nom) {
		
		this.nom = nom;
		
	}
	
	public String getNom() {
		
		return this.nom;
		
	}
	
	public void setPrixTotal(double prixTotal) {
		
		this.sousTotal = prixTotal;
		
	}
	
	public double[] getTabPrix() {
		/* Tab[0] = Sous-total (Prix total sans les taxes)
     	 * Tab[1] = Montant du TPS
     	 * Tab[2] = Montant du TVQ
     	 * Tab[3] = Montant total (avec les taxes)
		 */
		double[] tabPrix = new double[4];
		
		tabPrix[0] = this.sousTotal;
		tabPrix[1] = this.sousTotal * 0.05;
		tabPrix[2] = this.sousTotal * 0.10;
		tabPrix[3] = tabPrix[0] + (tabPrix[1] + tabPrix[2]);
		
		return tabPrix;
	}
	
}
