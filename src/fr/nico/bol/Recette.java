package fr.nico.bol;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table( name = "recette" )
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )//strategy d'heritage
public class Recette implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long recette_id;
	private String titre;
	private int nbPersonne;
	private String description;
	
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Recette_Produit", 
        joinColumns = { @JoinColumn(name = "recette_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "produit_id") }
    )
    Set<Produit> produits = new HashSet<>();
	
	public Recette() {}

	public Long getRecette_id() {
		return recette_id;
	}

	public void setRecette_id(Long id) {
		this.recette_id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getNbPersonne() {
		return nbPersonne;
	}

	public void setNbPersonne(int nbPersonne) {
		this.nbPersonne = nbPersonne;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Produit> getProduits() {
		return produits;
	}

	public void setProduits(Set<Produit> produits) {
		this.produits = produits;
	}
}
