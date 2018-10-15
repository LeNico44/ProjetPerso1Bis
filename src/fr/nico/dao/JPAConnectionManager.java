package fr.nico.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAConnectionManager {
	private static final String PERSISTENCE_UNIT = "projet-perso-1-bis";
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
	private static EntityManager em = emf.createEntityManager();
	
	public static EntityManager getEm() {
		return em;
	}
	
	public static void closeEmf() {
		emf.close();
	}
	
}