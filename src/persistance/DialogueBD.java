package persistance;

import java.sql.*;
import java.util.*;

import meserreurs.MonException;
import metier.Pays;

public class DialogueBD {
	private static DialogueBD instance = null;

	public static DialogueBD getInstance() {
		if (instance == null) {
			instance = new DialogueBD();
		}
		return instance;
	}

	private DialogueBD() {
		super();
	}

	public void insertionBD(String mysql) throws MonException {
		Connection cnx = null;
		try {
			try {
				cnx = Connexion.getInstance().getConnexion();
				Statement unstatement = cnx.createStatement();
				unstatement.execute(mysql); // on ferme la connexion
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			cnx.close();
		} catch (SQLException e) {
			throw new MonException(e.getMessage());
		}
	}
	
	

	public  List<Object> lecture(String req) throws MonException {
		Connection cnx = null;

		List<Object> mesRes = new ArrayList<Object>();
		int nbCols;
		try {
			try {
				cnx = Connexion.getInstance().getConnexion();
				Statement stmt = cnx.createStatement();
				// Execution de la requete
				ResultSet rs = stmt.executeQuery(req);
				// on retrouve le nombre de colonnes de la requête
				ResultSetMetaData rsmd = rs.getMetaData();
				nbCols = rsmd.getColumnCount();
				int i = 1;
				// on balaie toutes les lignes
				while (rs.next()) {
					// On balaie les colonnes
					i = 1;
					while (i <= nbCols) {
						mesRes.add(rs.getObject(i));
						i++;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			cnx.close();
			// Retourner la table
			return mesRes;
		} catch (SQLException e) {
			throw new MonException(e.getMessage());
		} finally {
			// S'il y a eu un problème, la connexion
			// peut être encore ouverte, dans ce cas
			// il faut la fermer.
			if (cnx != null) {
				try {
					cnx.close();
				} catch (SQLException e) {
				}
			}
		}
	}
}
