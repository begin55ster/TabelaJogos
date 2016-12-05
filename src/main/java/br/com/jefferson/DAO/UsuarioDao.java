package br.com.jefferson.DAO;

import javax.persistence.NoResultException;

import br.com.jefferson.Model.Usuario;

public class UsuarioDao extends GenericDao<Long, Usuario>{

	private static final long serialVersionUID = -7242160340237112810L;
	
	public UsuarioDao() {
		super(Usuario.class);
	}

	public Usuario findByLogin(String login) {
		Usuario user;
		try {
			user = createNamedQuery("Usuario.findByLogin").setParameter("login", login).getSingleResult();
			return user;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Usuario findByEmail(String email) {
		Usuario user;
		try {
			user = createNamedQuery("Usuario.findByEmail").setParameter("email", email).getSingleResult();
			return user;
		} catch (NoResultException e) {
			return null;
		}
	}

}
