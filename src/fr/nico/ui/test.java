package fr.nico.ui;

import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;

import fr.nico.bll.*;
import fr.nico.bol.Produit.Magasin;
import fr.nico.dao.ProduitDAO;

public class test {
	
	private static final Scanner sc = new Scanner( System.in ).useLocale(Locale.US);
	private static final Scanner sc2 = new Scanner( System.in ).useLocale(Locale.US);
	private static final double doubleNull = 0;

	public static void main(String[] args) throws SQLException {
		
		System.out.println("Affichage du garde manger au démarrage de l'application. (permet en un coup d'oeil de voir ce que l'on a à dispo.)");
		ProduitManager produitManager = new ProduitManager();
		produitManager.showStockProduit();
		menuPrincipal();

	}
	
	private static void menuPrincipal() throws SQLException {
		ProduitManager produitManager = new ProduitManager();
		RecetteManager recetteManager = new RecetteManager();
		
		System.out.println("************************************************************************");
		System.out.println("**************************** Menu Principal ****************************");
		System.out.println("** 1- Voir tous les produits enregistrés                              **");
		System.out.println("** 2- Ajouter un produit en base                                      **");
		System.out.println("** 3- Mettre à jour le stock                                          **");
		System.out.println("** 4- Liste des recettes                                              **");
		System.out.println("** 5- Enregistrer une recette                                         **");
		
		String choix = sc2.nextLine();
		
		switch (choix){
		case "1" : 
			produitManager.voirTousProduits();
			menuPrincipal();
			break;
		case "2" : 
			creationDunProduit();
			menuPrincipal();
			break;
		case "3" : 
			produitManager.majStock(produitManager.choixProduit(produitManager.voirTousProduits()));
			menuPrincipal();
			break;
		case "4" : 
			recetteManager.listeRecettes();
			menuPrincipal();
			break;
		case "5" : 
			recetteManager.creationRecette();
			menuPrincipal();
			break;
		}
	}
	
	private static void creationDunProduit() {
		double poids;
		double prix;
		double poidsStock = 0;
		int quantiteStock = 0;
		System.out.println("Coucou !!!");
		System.out.println("Bienvenue dans la création de produit !");
		System.out.println("***************************************");
		System.out.println("Entrez le libellé de votre produit : ");
		String libelle = sc2.nextLine();
		System.out.println("Entrez ensuite le prix au kilo de votre produit : ");
		System.out.println("Si vous ne le connaissez pas, taper 0.");
		double prixKilo = sc.nextDouble();
		
		if (prixKilo == doubleNull) {
			System.out.println("Entrez ensuite le poids de votre produit : ");
			System.out.println("Si vous ne le connaissez pas, taper 0.");
			poids = sc.nextDouble();
			System.out.println("Entrez ensuite le prix de votre produit : ");
			prix = sc.nextDouble();
		}else {
			poids = doubleNull;
			prix = doubleNull;
		}
		
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
		
		ProduitDAO produitDao = new ProduitDAO();
		
		try {
        	produitDao.create( produitManager.creationProduit(libelle, magasin, poids, prix, prixKilo, poidsStock, quantiteStock) );
        } catch ( java.sql.SQLException e ) {
            System.out.println( e.getMessage() );
        }
		
		System.out.println("It's ok !");
	}

}
