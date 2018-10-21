//https://vladmihalcea.com/the-best-way-to-map-a-many-to-many-association-with-extra-columns-when-using-jpa-and-hibernate/

package fr.nico.bol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
	private Long produit_id;
	private String libelle;
	private Double prix;
	private Double prixKilo;
	private Double poids;
	@Enumerated( EnumType.STRING )
	private Magasin magasin;
	@Temporal(TemporalType.DATE)// avec DATE ou TIME ou DATE TIME permet de préciser le type enregistré
	private Date dateMaj;
	private int quantiteStock;
	private Double poidsStock;
	
	@OneToMany(
	        mappedBy = "produit",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
    private Set<RecetteProduit> recettesProduits = new HashSet<>();

	public Produit () {}
	
	public Long getProduit_id() {
		return produit_id;
	}

	public void setProduit_id(Long id) {
		this.produit_id = id;
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
	
	public Date getDateMaj() {
		return dateMaj;
	}

	public void setDateMaj(Date dateMaj) {
		this.dateMaj = dateMaj;
	}

	public int getQuantiteStock() {
		return quantiteStock;
	}

	public void setQuantiteStock(int quantiteStock) {
		this.quantiteStock = quantiteStock;
	}

	public Double getPoidsStock() {
		return poidsStock;
	}

	public void setPoidsStock(Double poidsStock) {
		this.poidsStock = poidsStock;
	}

	public Set<RecetteProduit> getRecettesProduits() {
		return recettesProduits;
	}

	public void setRecettesProduits(Set<RecetteProduit> recettesProduits) {
		this.recettesProduits = recettesProduits;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass()) 
            return false;
 
        Produit produit = (Produit) o;
        return Objects.equals(libelle, produit.libelle);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(libelle);
    }

	public enum Magasin {
		Leclerc, Chlorophylle, Lidl, HyperU;
		
		private Magasin() {}
	}
	
}
