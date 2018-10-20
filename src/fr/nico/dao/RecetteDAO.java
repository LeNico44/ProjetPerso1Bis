package fr.nico.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.nico.bol.Recette;

public class RecetteDAO implements IDAO <Recette>{
	private static EntityManager em = JPAConnectionManager.getEm();

	@Override
	public void create(Recette o) throws SQLException {
		begin();
		em.persist(o);
		commit();
	}

	@Override
	public List<Recette> read() throws SQLException {
		TypedQuery<Recette> query = em.createQuery( "FROM Recette", Recette.class );
		List<Recette> recettes = query.getResultList();
		return recettes;
	}

	@Override
	public void update(Recette o) throws SQLException {
		begin();
		em.merge(o);
		commit();
		
	}

	@Override
	public void delete(Recette o) throws SQLException {
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
