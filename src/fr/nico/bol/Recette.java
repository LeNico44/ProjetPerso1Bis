package fr.nico.bol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

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
