package br.com.jefferson.RN;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.jefferson.DAO.UsuarioDao;
import br.com.jefferson.Model.Usuario;

@Stateless
public class UsuarioRN {
	
	@Inject
	UsuarioDao usuarioDao;

	public Usuario findByLogin(String login) {
		return usuarioDao.findByLogin(login);
	}
	

}
