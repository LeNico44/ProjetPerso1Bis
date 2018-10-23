package fr.nico.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.nico.bol.Menu;

public class MenuDAO implements IDAO <Menu>{
	private static EntityManager em = JPAConnectionManager.getEm();

	@Override
	public void create(Menu o) throws SQLException {
		begin();
		em.persist(o);
		commit();
	}

	@Override
	public List<Menu> read() throws SQLException {
		TypedQuery<Menu> query = em.createQuery( "FROM Menu", Menu.class );
		List<Menu> recettes = query.getResultList();
		return recettes;
	}

	@Override
	public void update(Menu o) throws SQLException {
		begin();
		em.merge(o);
		commit();
		
	}

	@Override
	public void delete(Menu o) throws SQLException {
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