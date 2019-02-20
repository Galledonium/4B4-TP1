
public class Plat {

	private String repas;
	private double prix;
	
	public Plat(String repas) {
		
		this.repas = repas;
		
	}
	
	public Plat(String repas, double prix) {
		
		this.repas = repas;
		this.prix = prix;
		
	}
	
	public void setRepas(String repas) {
		
		this.repas = repas;
		
	}
	
	public void setPrix(double prix) {
		
		this.prix = prix;
		
	}
	
	public String getRepas() {
		
		return this.repas;
		
	}
	
	public double getPrix() {
		
		return this.prix;
		
	}
	
}
