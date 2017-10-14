package com.patrice.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class TestClient {  
	 
	private EntityManagerFactory emf = null;
    private EntityManager em = null;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		TestClient c1 = new TestClient(); 
		c1.test();

	} 
	
    private void test(){
    	
		try {
			emf = Persistence.createEntityManagerFactory("JPA_HIBERNATE");
			em = emf.createEntityManager();

			Student g1 = (Student) em.createQuery("select s from Student s where s.name = :chrName").setParameter("chrName", "fahad shehzad").getSingleResult();
			System.out.println(g1.toString());
			
			Student g = (Student) em.createQuery("select s from Student s where s.name = :chrName").setParameter("chrName", "Patrice Mpoyi").getSingleResult();
			System.out.println(g.toString());
		} finally {

			if (em != null) em.close();
			if (emf != null) emf.close();
		}
		
    }


}
