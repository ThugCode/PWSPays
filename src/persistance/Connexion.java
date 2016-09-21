package persistance;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import meserreurs.MonException;

public class Connexion {
	private static Connexion instance = null;
	// On utilise un singleton
	public static Connexion getInstance() {
		if (instance == null){
			instance = new Connexion();
		}
		return instance;
	}
	
	private Connexion (){
	}
	
	public Connection getConnexion( ) throws MonException {
		Connection conn = null; 
		try {
			Context ctxt = new InitialContext();
			Context envCtx = (Context) ctxt.lookup("java:comp/env"); // On recherche la data source
			DataSource ds = (DataSource) envCtx.lookup("jdbc/DSPays"); 
			conn = ds.getConnection();
		}catch (Exception e) {
			throw new MonException(e.getMessage());
		}
		return conn;
	}
}