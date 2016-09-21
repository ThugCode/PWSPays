package service;

import java.util.ArrayList;
import java.util.List;

import meserreurs.MonException;
import metier.Pays;
import persistance.DialogueBD;

public class WebServices {

	public List<String> getNomsPays() throws MonException {
		List<String> noms = new ArrayList<String>();

		List<Pays> mespays = getTousLesPays();

		for (Pays p : mespays) {
			noms.add(p.getNomPays());
		}
		return noms;
	}

	

	public List<Pays> getTousLesPays() throws MonException {
		List<Pays> resultat = new ArrayList<Pays>();
		
		DialogueBD undlg = DialogueBD.getInstance();
		List<Object> res = undlg.lecture("SELECT * FROM Pays");
		for (int i = 0; i < res.size(); i += 3) {

			String nomPays = res.get(i + 0).toString();
			String nomCapitale = res.get(i + 1).toString();
			int nbHabitants = Integer.parseInt(res.get(i + 2).toString());

			Pays p = new Pays(nomPays, nomCapitale, nbHabitants);

			resultat.add(p);
		}

		return resultat;
	}

	public Pays getUnPays(String nomPays) throws MonException {
		Pays resultat = null;
		DialogueBD undlg = DialogueBD.getInstance();
		List<Object> res = undlg.lecture("SELECT * FROM Pays WHERE nom_pays = '" + nomPays + "'");

		// String nomPays = res.get(0).toString();
		String nomCapitale = res.get(1).toString();
		int nbHabitants = Integer.parseInt(res.get(2).toString());

		resultat = new Pays(nomPays, nomCapitale, nbHabitants);

		return resultat;
	}

}
