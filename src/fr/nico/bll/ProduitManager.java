package fr.nico.bll;

import fr.nico.bol.Produit;
import fr.nico.bol.Produit.Magasin;
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
	
	public Produit creationProduit (String libelle, Magasin magasin, double poids, double prixKilo) {
		produit = new ProduitVrac();
		produit.setLibelle(libelle);
		produit.setMagasin(magasin);
		produit.setPoids(poids);
		produit.setPrixKilo(prixKilo);
		double prix;
		prix = (prixKilo * poids);
		produit.setPrix(prix);
		return produit;
	}
	
	/*public Account creationDeCompte(double solde, String type, double decouvert, Agency agence) {
		if(type.equals("SIMPLE_ACCOUNT")) {
			compte = new SimpleAccount();
			compte.setBalance(solde);
			(( SimpleAccount ) compte).setOverdraft( decouvert );
			compte.setAgency(agence);
		}		
		return compte;
		
	}*/
}
