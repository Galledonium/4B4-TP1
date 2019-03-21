public class Client {
	
	private String nom;
	private double prixTotal;
	
	public Client() {
		
		
		
	}
	
	public Client(String nom) {
		
		this.nom = nom;
		
	}
	
	public Client(String nom, double prixTotal) {
		
		this.nom = nom;
		this.prixTotal = prixTotal;
		
	}
	
	public void setNom(String nom) {
		
		this.nom = nom;
		
	}
	
	public String getNom() {
		
		return this.nom;
		
	}
	
	public void setPrixTotal(double prixTotal) {
		
		this.prixTotal = prixTotal;
		
	}
	
	public double getPrixTotal() {
		
		return this.prixTotal;
		
	}
	
}
