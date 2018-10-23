package fr.nico.bll;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import fr.nico.bol.Recette;
import fr.nico.bol.RecetteProduit;
import fr.nico.dao.RecetteDAO;

public class RecetteManager {
	private Recette recette;
	
	public RecetteManager() {}

	public Recette getRecette() {
		return recette;
	}

	public void setRecette(Recette recette) {
		this.recette = recette;
	}
	
	public void creationRecette() throws SQLException {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner( System.in ).useLocale(Locale.US);
		@SuppressWarnings("resource")
		Scanner sc2 = new Scanner( System.in ).useLocale(Locale.US);
		RecetteDAO recetteDao = new RecetteDAO();
		
		System.out.println("Merci de donner un nom à votre recette.");
		String titre = sc.nextLine();
		
		System.out.println("Merci d'indiquer pour combien de personne est prévu cette recette.");
		int nbPersonne = sc2.nextInt();
		
		System.out.println("Merci de donner une petite description.");
		System.out.println("! Ne pas passer à la ligne ! la touche enter arrête la saisie.");
		String description = sc.nextLine();
		
		Recette recette = new Recette();
		recette.setTitre(titre);
		recette.setNbPersonne(nbPersonne);
		recette.setDescription(description);
		
		ProduitManager produitManager = new ProduitManager();
		RecetteProduit recetteProduit = new RecetteProduit();
		
		int reponse = 1;
		do {
			System.out.println("Sélectionner un produit dans la liste pour votre recette.");
			recetteProduit.setProduit(produitManager.choixProduit(produitManager.voirTousProduits()));
			System.out.println("Pour combien souhaitez-vous " + recetteProduit.getProduit().getLibelle() + " ?");
			Double quantiteKilo = sc.nextDouble();
//			recetteProduit.setQuantiteKilo(quantiteKilo);
//			recetteProduits.add(recetteProduit);
			recette.addProduit(recetteProduit.getProduit(), quantiteKilo);
			
			//Affichages des produits déjà dans la recette.
			System.out.println(" ");
			System.out.println("***** Liste des ingrédients de la recette *****");
			for (RecetteProduit rp: recette.getRecettesProduits()) {
				System.out.println("- "+ rp.getQuantiteKilo() + " kg de " + rp.getProduit().getLibelle());
			}
			
			System.out.println(" ");
			//FIN Affichages des produits déjà dans la recette.
			System.out.println("Souhaitez-vous ajouter un autre ingredient à votre recette ?");
			System.out.println("1- oui / 2- non");
			reponse = sc.nextInt();
			System.out.println(reponse);
		}while(reponse != 2);
		
		recetteDao.create(recette);
	}
	
	public List<Recette> listeRecettes() throws SQLException {
		RecetteDAO recetteDao = new RecetteDAO();
		List<Recette> recettes = recetteDao.read();
		if(recettes != null && recettes.size() > 0) {
			System.out.println( "******************************************************" );
			System.out.println( "****************** Liste des recettes ****************" );
			System.out.println( "******************************************************" );
			
			int index = 0;
			for (Recette recette : recettes) {
				System.out.println(index++ + "- " + recette.getTitre());
			}
		}
		
		return recettes;
		
	}
	
	public Recette choixRecette(List<Recette> recettes) throws SQLException {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner( System.in ).useLocale(Locale.US);
		System.out.println( " " );
		System.out.println( "Sélectionnez une recette." );
		int response;
		response = sc.nextInt();
		recette = recettes.get(response);
		return recette;
	}
	
	public Double prixRecette(Recette recette) {
    	Double prixCalcule = 0.0;
    	for (RecetteProduit rp: recette.getRecettesProduits()) {
			prixCalcule = prixCalcule + (rp.getQuantiteKilo() * rp.getProduit().getPrixKilo());
		}
    	System.out.println(prixCalcule);
    	return prixCalcule;
    }
	
	
}
