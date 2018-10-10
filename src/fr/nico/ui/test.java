package fr.nico.ui;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.nico.bll.ProduitManager;
import fr.nico.bol.Produit.Magasin;

public class test {
	
	private static final String PERSISTENCE_UNIT = "projet-perso-1-bis";
	private static final Scanner sc = new Scanner( System.in );

	public static void main(String[] args) {
		System.out.println("Coucou !!!");
		System.out.println("Bienvenue dans la création de produit !");
		System.out.println("***************************************");
		System.out.println("Entrez le libellé de votre produit : ");
		String libelle = sc.nextLine();
		System.out.println("Entrez maintenant le poids de votre produit : ");
		double poids = sc.nextDouble();
		System.out.println("Entrez ensuite le prix au kilo de votre produit : ");
		double prixKilo = sc.nextDouble();
		System.out.println("Pour finir choisissez le magasin dans lequel vous allez acheter votre produit : ");
		Magasin magasin = null;
		System.out.println("1 : Leclerc");
		System.out.println("2 : Chlorophylle");
		System.out.println("3 : Hyper U");
		System.out.println("4 : Lidl");
		int response = sc.nextInt();
		
		switch (response){
			case 1 : 
				magasin = Magasin.Leclerc;
				break;
			case 2 : 
				magasin = Magasin.Chlorophylle;
				break;
			case 3 : 
				magasin = Magasin.HyperU;
				break;
			case 4 : 
				magasin = Magasin.Lidl;
				break;
		}
		
		
		ProduitManager produitManager = new ProduitManager();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory( PERSISTENCE_UNIT );
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(produitManager.creationProduit(libelle, magasin, poids, prixKilo));
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		System.out.println("It's ok !");
		
		
	}

}
