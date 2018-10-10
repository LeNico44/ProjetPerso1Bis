package fr.nico.bol;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table( name = "produit" )
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )//strategy d'heritage
@DiscriminatorColumn( name = "type" )
public abstract class Produit implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	private String libelle;
	private Double prix;
	private Double prixKilo;
	private Double poids;
	@Enumerated( EnumType.STRING )
	private Magasin magasin;

	public Produit () {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public Double getPrixKilo() {
		return prixKilo;
	}

	public void setPrixKilo(Double prixKilo) {
		this.prixKilo = prixKilo;
	}

	public Double getPoids() {
		return poids;
	}

	public void setPoids(Double poids) {
		this.poids = poids;
	}
	
	public Magasin getMagasin() {
		return magasin;
	}

	public void setMagasin(Magasin magasin) {
		this.magasin = magasin;
	}
	
	public enum Magasin {
		Leclerc, Chlorophylle, Lidl, HyperU;
		
		private Magasin() {}
	}
}
