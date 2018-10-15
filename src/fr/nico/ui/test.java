package fr.nico.ui;

import java.util.Scanner;

import fr.nico.bll.ProduitManager;
import fr.nico.bol.Produit.Magasin;
import fr.nico.dao.ProduitDAO;

public class test {
	
	private static final Scanner sc = new Scanner( System.in );
	private static final Scanner sc2 = new Scanner( System.in );
	private static final double doubleNull = 0;

	public static void main(String[] args) {
		
		System.out.println("                        ici menu pour choisir   modifier le stock d'un produit                           ");
		creationDunProduit();
		

	}
	private static void creationDunProduit() {
		double poids;
		double prix;
		double poidsStock = 0;
		float quantiteStock = 0;
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
		System.out.println("Souhaitez-vous entrer un nouveau produit ?");
		System.out.println("1 : oui ");
		System.out.println("2 : non ");
		int encore = sc.nextInt();
		if(encore == 1) {
			creationDunProduit();
		}else if(encore == 2) {
			System.out.println("bye !");
		}else
		{
			System.out.println("Vous n'êtes pas capable de choisir entre 1 et 2 !");
		}
		
		
	}

}
