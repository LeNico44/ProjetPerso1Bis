package fr.nico.bol;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue( "PRODUIT_EMBALLE" )
public class ProduitEmballe extends Produit {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProduitEmballe () {
		super();
	}
}
