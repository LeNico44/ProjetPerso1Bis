package fr.nico.bol;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue( "PRODUIT_VRAC" )
public class ProduitVrac extends Produit {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProduitVrac () {
		super();
	}
}
