package fr.nico.bll;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import fr.nico.bol.Produit;
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
		Scanner sc = new Scanner( System.in );
		RecetteDAO recetteDao = new RecetteDAO();
		System.out.println("Merci de donner un nom à votre recette.");
		String titre = sc.nextLine();
		System.out.println("Merci de donner une petite description.");
		System.out.println("! Ne pas passer à la ligne ! la touche enter arrête la saisie.");
		String description = sc.nextLine();
		Recette recette = new Recette();
		recette.setTitre(titre);
		recette.setDescription(description);
		ProduitManager produitManager = new ProduitManager();
		Set<RecetteProduit> recettesProduits = new HashSet<>();
		
		int reponse = 1;
		do {
			System.out.println("Sélectionner un produit dans la liste pour votre recette.");
			recettesProduits.add(produitManager.choixProduit(produitManager.voirTousProduits()));
			//Affichages des produits déjà dans la recette.
			System.out.println(" ");
			System.out.println("***** Liste des ingrédients de la recette *****");
			for (RecetteProduit recetteProduit : recettesProduits) {
				System.out.println(recetteProduit.getProduit().getLibelle());
				//System.out.println("- " + produit.getLibelle());
			}
			System.out.println(" ");
			//FIN Affichages des produits déjà dans la recette.
			System.out.println("Souhaitez-vous ajouter un autre ingredient à votre recette ?");
			System.out.println("1- oui / 2- non");
			reponse = sc.nextInt();
			System.out.println(reponse);
		}while(reponse != 2);
			
		
		
		recette.setRecettesProduits(recettesProduits);
		
		
		recetteDao.create(recette);
	}
	
	public void listeRecettes() throws SQLException {
		RecetteDAO recetteDao = new RecetteDAO();
		List<Recette> recettes = recetteDao.read();
		if(recettes != null && recettes.size() > 0) {
			System.out.println( "******************************************************" );
			System.out.println( "****************** Liste des recettes ****************" );
			System.out.println( "******************************************************" );
			
			int index = 0;
			for (Recette recette : recettes) {
				System.out.println(index++ + "- " + recette.getTitre() + " /?/ " + recette.getRecettesProduits().iterator().toString());
			}
		}
		
	}
	
	
}
