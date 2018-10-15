package fr.nico.dao;

import java.sql.SQLException;

import javax.persistence.EntityManager;

import fr.nico.bol.Produit;

public class ProduitDAO implements IDAO <Produit>{
	private static EntityManager em = JPAConnectionManager.getEm();

	@Override
	public void create(Produit o) throws SQLException {
		begin();
		em.persist(o);
		commit();
	}

	@Override
	public void update(Produit o) throws SQLException {
		begin();
		em.merge(o);
		commit();	
	}

	@Override
	public void delete(Produit o) throws SQLException {
		begin();
		em.remove(em.contains(o) ? o : em.merge(o));
		commit();
		
	}
	
	/**
	 * factorisation du begin et du commit(+ close em et emf) des transactions
	 */
	public void begin() {
		em.getTransaction().begin();
	}
	
	public void commit() {
		em.getTransaction().commit();
		em.close();
		JPAConnectionManager.closeEmf();
	}

}
