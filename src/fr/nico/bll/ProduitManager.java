package fr.nico.bll;

import java.util.Date;

import fr.nico.bol.Produit;
import fr.nico.bol.Produit.Magasin;
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
	
	public Produit creationProduit (String libelle, Magasin magasin, double poids, double prix, double prixKilo) {
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
		}
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
