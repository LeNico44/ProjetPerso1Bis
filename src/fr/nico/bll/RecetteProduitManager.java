package fr.nico.bll;

import fr.nico.bol.RecetteProduit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.nico.bol.*;

public class RecetteProduitManager {
	private RecetteProduit recetteProduit;
	private List<Produit> produits;
	private Produit produit;
	
	public RecetteProduitManager() {}

	public RecetteProduit getRecetteProduit() {
		return recetteProduit;
	}

	public void setRecetteProduit(RecetteProduit recetteProduit) {
		this.recetteProduit = recetteProduit;
	}
	 
	public List<Produit> listProduit(Recette recette)throws SQLException{
		produits = new ArrayList<Produit>();
		for(RecetteProduit recetteProduit : recette.getRecettesProduits()) {
			produits.add(recetteProduit.getProduit());
		}
		for(Produit produit : produits) {
			System.out.println(produit.getLibelle());
		}
		return produits;
	}
}
