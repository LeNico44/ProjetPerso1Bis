package fr.nico.bol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

@Entity(name = "Recette")
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
	@ManyToMany(mappedBy = "recettes")
    private List<Menu> menus = new ArrayList<>();
	
	@OneToMany(
		mappedBy = "recette",
		cascade = { CascadeType.ALL },
		orphanRemoval = true
	)
    
	private Set<RecetteProduit> recettesProduits = new HashSet<>();
	
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

	public Set<RecetteProduit> getRecettesProduits() {
		return recettesProduits;
	}

	public void setRecettesProduits(Set<RecetteProduit> recettesProduits) {
		this.recettesProduits = recettesProduits;
	}
	
	//méthode add du tuto pour la table de jointure avec colonne supplémentaire
	public void addProduit(Produit produit, Double quantiteKilo) {
		RecetteProduit recetteProduit = new RecetteProduit(this, produit, quantiteKilo);
		recettesProduits.add(recetteProduit);
		produit.getRecettesProduits().add(recetteProduit);
	}
	
	//méthode remove du tuto pour la table de jointure avec colonne supplémentaire
	public void removeProduit(Produit produit) {
        for (Iterator<RecetteProduit> iterator = recettesProduits.iterator(); 
             iterator.hasNext(); ) {
        	RecetteProduit recetteProduit = iterator.next();
 
            if (recetteProduit.getRecette().equals(this) &&
            		recetteProduit.getProduit().equals(produit)) {
                iterator.remove();
                recetteProduit.getProduit().getRecettesProduits().remove(recetteProduit);
                recetteProduit.setRecette(null);
                recetteProduit.setProduit(null);
            }
        }
    }
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass()) 
            return false;
 
        Recette recette = (Recette) o;
        return Objects.equals(titre, recette.titre);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(titre);
    }
    
}
