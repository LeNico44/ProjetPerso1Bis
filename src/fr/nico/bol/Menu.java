package fr.nico.bol;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity(name ="Menu")
@Table(name = "menu")
public class Menu implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long menu_id;
	private Date dateDebut;
	private Date dateFin;
	private int nbPersonne;
	private Double prixMenu;

	@ManyToMany(cascade = { 
        CascadeType.PERSIST, 
        CascadeType.MERGE
    })
    @JoinTable(name = "menu_recette",
        joinColumns = @JoinColumn(name = "menu_id"),
        inverseJoinColumns = @JoinColumn(name = "recette_id")
    )
	private List<Recette> recettes;
	
	public Menu() {}

	public Long getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(Long menu_id) {
		this.menu_id = menu_id;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public int getNbPersonne() {
		return nbPersonne;
	}

	public void setNbPersonne(int nbPersonne) {
		this.nbPersonne = nbPersonne;
	}

	public List<Recette> getRecettes() {
		return recettes;
	}

	public void setRecettes(List<Recette> recettes) {
		this.recettes = recettes;
	}

	public Double getPrixMenu() {
		return prixMenu;
	}

	public void setPrixMenu(Double prixMenu) {
		this.prixMenu = prixMenu;
	}
}
