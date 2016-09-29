package service;

import java.util.ArrayList;
import java.util.List;

import meserreurs.MonException;
import metier.Pays;
import persistance.DialogueBD;

public class WebServices {

	/***
	 * Récupération de la liste de tous les noms pays
	 * @return Liste de /Pays
	 * @throws MonException
	 */
	public List<String> getNomsPays() throws MonException {
		List<String> noms = new ArrayList<String>();

		List<Pays> mespays = getTousLesPays();

		for (Pays p : mespays) {
			noms.add(p.getNomPays());
		}
		return noms;
	}

	/***
	 * Récupération de la liste de tous les pays
	 * @return
	 * @throws MonException
	 */
	public List<Pays> getTousLesPays() throws MonException {
		List<Pays> resultat = new ArrayList<Pays>();
		
		DialogueBD undlg = DialogueBD.getInstance();
		List<Object> res = undlg.lecture("SELECT * FROM Pays");
		for (int i = 0; i < res.size(); i += 3) {

			String nomPays = res.get(i + 0).toString();
			String nomCapitale = res.get(i + 1).toString();
			int nbHabitants = Integer.parseInt(res.get(i + 2).toString());

			Pays p = new Pays(nomPays, nomCapitale, nbHabitants, false);

			resultat.add(p);
		}

		return resultat;
	}

	/***
	 * Récupération d'un pays
	 * @param search
	 * @return
	 * @throws MonException
	 */
	public Pays getUnPays(String search) throws MonException {
		Pays resultat = null;
		
		DialogueBD undlg = DialogueBD.getInstance();
		List<Object> res = undlg.lecture("SELECT * FROM Pays WHERE nom_pays = '" + search + "'");

		if(res != null) {
			// String nomPays = res.get(0).toString();
			String nomCapitale = res.get(1).toString();
			int nbHabitants = Integer.parseInt(res.get(2).toString());
	
			resultat = new Pays(search, nomCapitale, nbHabitants, false);
		}

		return resultat;
	}
	
	/***
	 * Recherche de pays ou capitale
	 * @param search
	 * @return
	 * @throws MonException
	 */
	public ArrayList<Pays> searchPays(String search) throws MonException {
		
		ArrayList<Pays> listeRecherche = new ArrayList<Pays>();
		
		DialogueBD undlg = DialogueBD.getInstance();
		List<Object> resPays = undlg.lecture("SELECT * FROM Pays WHERE nom_pays LIKE \'%"+search+"%\'");
		for (int i = 0; i < resPays.size(); i += 3) {

			String nomPays = resPays.get(i + 0).toString();
			String nomCapitale = resPays.get(i + 1).toString();
			int nbHabitants = Integer.parseInt(resPays.get(i + 2).toString());

			Pays p = new Pays(nomPays, nomCapitale, nbHabitants, true);
			
			listeRecherche.add(p);
		}
		
		List<Object> resCapitale = undlg.lecture("SELECT * FROM Pays WHERE nom_capitale LIKE \'%"+search+"%\'");
		for (int i = 0; i < resCapitale.size(); i += 3) {

			String nomPays = resCapitale.get(i + 0).toString();
			String nomCapitale = resCapitale.get(i + 1).toString();
			int nbHabitants = Integer.parseInt(resCapitale.get(i + 2).toString());

			Pays p = new Pays(nomPays, nomCapitale, nbHabitants, false);
			
			listeRecherche.add(p);
		}
		
		return listeRecherche;
	}

}
