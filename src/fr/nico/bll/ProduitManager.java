package fr.nico.bll;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import fr.nico.bol.Produit;
import fr.nico.bol.Produit.Magasin;
import fr.nico.dao.ProduitDAO;
import fr.nico.bol.ProduitEmballe;
import fr.nico.bol.ProduitVrac;

public class ProduitManager {
	private Produit produit;
	
	public ProduitManager() {
		
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	
	public Produit creationProduit (String libelle, Magasin magasin, double poids, double prix, double prixKilo, double poidsStock, int quantiteStock) {
		if (prixKilo == 0) {
			produit = new ProduitEmballe();
			produit.setLibelle(libelle);
			produit.setMagasin(magasin);
			if (poids == 0) {
				produit.setPoids(null);
				produit.setPrixKilo(null);
			}else {
				produit.setPrixKilo(prix / poids);
				produit.setPoids(poids);
			}
			if (prix == 0) {
				produit.setPrix(null);
			}else {
				produit.setPrix(prix);
			}
			produit.setDateMaj(new Date());
		}else {
			produit = new ProduitVrac();
			produit.setLibelle(libelle);
			produit.setMagasin(magasin);
			produit.setPoids(null);
			produit.setPrixKilo(prixKilo);
			produit.setPrix(null);
			produit.setDateMaj(new Date());
			produit.setPoidsStock(poidsStock);
			produit.setQuantiteStock(quantiteStock);
		}
		return produit;
		
	}
	
	public void showStockProduit() throws SQLException {
		ProduitDAO produitDao = new ProduitDAO();
		List<Produit> produits = produitDao.read();
		if ( produits != null && produits.size() > 0 ) {
			boolean first = true;
			System.out.println( "******************************************************" );
			System.out.println( "***************** Voici les réserves *****************" );
			System.out.println( "******************************************************" );
			
			if ( !first ) {
				System.out.println( "**********************************************************" );
				System.out.println( "* Mauvais choix - agence inconnue; merci de recommencer ! *" );
				System.out.println( "**********************************************************" );
			}
			produits.forEach( item->{
				if(item.getPoidsStock() != 0 || item.getQuantiteStock() != 0){
					System.out.println(item.getLibelle() + " / " + item.getPoidsStock()  + " / " + item.getQuantiteStock());
				}
			});
			first = false;
		}
	}
		
	public List<Produit> voirTousProduits() throws SQLException {
		ProduitDAO produitDao = new ProduitDAO();
		List<Produit> produits = produitDao.read();
		if ( produits != null && produits.size() > 0 ) {
			System.out.println( "******************************************************" );
			System.out.println( "*************** Voir tous  les produits **************" );
			System.out.println( "******************************************************" );
			
			int index = 0;
            for ( Produit item  : produits ) {
			
				System.out.println(index++ + "- " + item.getLibelle() + " / " + item.getPoidsStock()  + " / " + item.getQuantiteStock());

            }
		}
		
		return produits;
	}
	
	public Produit choixProduit(List<Produit> produits) throws SQLException {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner( System.in );
		System.out.println( " " );
		System.out.println( "Sélectionnez un produit pour modifier son stock." );
		int response;
		response = sc.nextInt();
		produit = produits.get(response);
		return produit;
	}
	
	public void majStock(Produit produit) {
		System.out.println( "Test récupération du produit !" );
		System.out.println( produit.getLibelle());
	}
}
