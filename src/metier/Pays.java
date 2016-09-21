

package metier;


public class Pays {
	private String nomPays;
	private String nomCapitale;
	private int nbHabitants;
	
	public Pays(String nomPays, String nomCapitale, int nbHabitants) {
		this.nomPays = nomPays;
		this.nomCapitale = nomCapitale;
		this.nbHabitants = nbHabitants;
	}

	public String getNomPays() {
		return nomPays;
	}

	public String getNomCapitale() {
		return nomCapitale;
	}

	public int getNbHabitants() {
		return nbHabitants;
	}
}
