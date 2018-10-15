package fr.nico.dao;

import java.sql.SQLException;

public interface IDAO <T> {
	public abstract void create(T o) throws SQLException;
	public abstract void update(T o) throws SQLException;
	public abstract void delete(T o) throws SQLException;
}
