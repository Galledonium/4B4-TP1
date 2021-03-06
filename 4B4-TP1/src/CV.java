public class CV {
	
	private static CV gregson = new CV(
			"Gregson",
			"Destin",
			"Informatique de Gestion",
			"Centre d'appel Rotisserie St-Hubert (2 Novembre 2017 - ...)",
			"Communication impeccable, Patience gargantuesque, Concentration accrue, Performant",
			"Note finale minimale de 80%.");
	private static CV isaac = new CV(
			"Isaac David",
			"Zolana",
			"Informatique de Gestion",
			"Centre d'appel en informatique � Revenu Quebec (13 Juin 2018 - ...)",
			"Patient, Efficace, Performant, Attentif, Concentr�, Sociable, Compatissant, Organis�, Respectueux",
			"Passer le cours avec 100%."
			);
	
	public String prenom;
	public String nom;
	public String formation;
	public String expTravail;
	public String competences;
	public String attentes;
	
	public CV(String prenom, String nom, String formation, String expTravail, String competences, String attentes) {
		
		this.prenom = prenom;
		this.nom = nom;
		this.formation = formation;
		this.expTravail = expTravail;
		this.competences = competences;
		this.attentes = attentes;
		
	}	

	public static void main(String[] args) {
		
		System.out.println("Bienvenue chez Barette!\n");
		
		System.out.println(gregson.affiche());
		
		System.out.print("\n");
		
		System.out.println(isaac.affiche());

	}
	
	public String affiche() {
		
		return 	"CV de " + prenom + " " + nom + ":\n\n" +
				"Prenom:\t\t\t\t " + prenom + "\n" +
				"Nom:\t\t\t\t " + nom + "\n" +
				"Formation:\t\t\t " + formation + "\n" +
				"Exp�rience(s) de travail:\t " + expTravail + "\n" +
				"Comp�tences:\t\t\t " + competences + "\n" +
				"Attentes vis � vis le cours:\t " + attentes;
		
	}
}