package fr.nico.ui;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.nico.bol.Produit;

public class test {
	
	private static final String PERSISTENCE_UNIT = "projet-perso-1-bis";

	public static void main(String[] args) {
		Produit produit = new Produit();
		produit.setNom("leProduit");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory( PERSISTENCE_UNIT );
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(produit);
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		
	}

}
