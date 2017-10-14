package com.patrice.hibernate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.persistence.Persistence;    
import javax.persistence.EntityManagerFactory;    
import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

public class TestJPAHibernate {

	static private EntityManager em  = null;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		// Creer le Entity Manager
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_HIBERNATE");    
	    em = emf.createEntityManager();

	    em.getTransaction().begin();	    
	    
	    // Faire une insertion
	    Personne personne2 = new Personne();
	    personne2.setId(new Integer(8));
	    personne2.setNom("Paris");
	    personne2.setPrenom("Paul");
	    em.persist(personne2);
	    em.flush();
	    
	    // Faire une lecture
	    Personne personne = em.find(Personne.class, 8);  
	    
	    System.out.println("Personne.nom = "  + personne.getNom());  

	    // Faire une maj avec JDBC
	    em.flush();
	    Session session = em.unwrap(Session.class);
	    session.doWork(new MyWork(8, "JoJo"));        // Appel de JDBC dans la même session que Hibernate
	    em.refresh(personne);

	    Personne personne4 = em.find(Personne.class, 8);  
	    
	    System.out.println("Personne.nom = "  + personne4.getNom() + " Personne.prenom = " + personne4.getPrenom() ); 
	    
	    List<Personne> liste = getAllPersonnes();
	    for (Personne pers : liste) {
	    	System.out.println("id = " + pers.getId() + " Nom = "  + pers.getNom() + " Prenom = " + pers.getPrenom() ); 
	    }
	    
	    
	    em.getTransaction().commit();
	    em.close();    
	    emf.close();    		 

	}
	
	static void updatePersonne (Integer id, Connection conn) throws Exception {
		
		PreparedStatement ps = null;
		
		String updateTableSQL = "update Personne set nom = ?, prenom = ? where id = ? ";
		try {
			
			ps = conn.prepareStatement(updateTableSQL);
			ps.setString(1, "TOTO");
			ps.setString(2, "TITI");
			ps.setInt(3, id);
            ps.executeUpdate();
            
            System.out.println("Table Personne updated ");
            
		} finally {
			if (ps != null) ps.close();
		}
		
		
	}

	
	static void updatePersonne2 (Integer id, Connection conn) throws Exception {
		
		PreparedStatement ps = null;
		
		String updateTableSQL = "update Personne set prenom = ? where id = ? ";
		try {
			
			ps = conn.prepareStatement(updateTableSQL);
			ps.setString(1, "PATRICE");
			ps.setInt(2, id);
            ps.executeUpdate();
            
            System.out.println("Table Personne updated ");
            
		} finally {
			if (ps != null) ps.close();
		}
		
		
	}	
	
	static List<Personne> getAllPersonnes() {

	    Query q = em.unwrap(Session.class).createQuery("from Personne");

	    List<Personne> Personnes = (List<Personne>) q.list();

	    return Personnes;

	}
}
