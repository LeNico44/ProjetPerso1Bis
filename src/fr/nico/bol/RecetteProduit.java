package fr.nico.bol;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Entity(name = "RecetteProduit")
@Table(name = "recette_produit")
public class RecetteProduit {
	
	@Embeddable
	public static class RecetteProduitId implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		@Column(name = "recette_id")
		private Long recette_id;
		
		@Column(name = "produit_id")
		private Long produit_id;
		
		public RecetteProduitId() {}
		
		public RecetteProduitId(Long recette_id, Long produit_id) {
			this.recette_id = recette_id;
			this.produit_id = produit_id;
		}

		public Long getRecette_id() {
			return recette_id;
		}

		public void setRecette_id(Long recette_id) {
			this.recette_id = recette_id;
		}

		public Long getProduit_id() {
			return produit_id;
		}

		public void setProduit_id(Long produit_id) {
			this.produit_id = produit_id;
		}
		
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			
			if (o == null || getClass() != o.getClass()) return false;
			
			RecetteProduitId that = (RecetteProduitId) o;
			return Objects.equals(recette_id, that.recette_id) && Objects.equals(produit_id, that.produit_id);
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(recette_id, produit_id);
		}
		

	}

	@EmbeddedId
	private RecetteProduitId id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("recette_id")
	private Recette recette;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("produit_id")
	private Produit produit;
	
	@Column(name = "quantite_kilo")
	private Double quantiteKilo;
	
	public RecetteProduit() {}
	
	public RecetteProduit(Recette recette, Produit produit) {
		this.recette = recette;
		this.produit = produit;
		this.id = new RecetteProduitId(recette.getRecette_id(), produit.getProduit_id());
	}

	public RecetteProduitId getId() {
		return id;
	}

	public void setId(RecetteProduitId id) {
		this.id = id;
	}

	public Recette getRecette() {
		return recette;
	}

	public void setRecette(Recette recette) {
		this.recette = recette;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Double getQuantiteKilo() {
		return quantiteKilo;
	}

	public void setQuantiteKilo(Double quantiteKilo) {
		this.quantiteKilo = quantiteKilo;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		
		if (o == null || getClass() != o.getClass()) return false;
		
		RecetteProduit that = (RecetteProduit) o;
		return Objects.equals(recette, that.recette) && Objects.equals(produit, that.produit);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(recette, produit);
	}
}
