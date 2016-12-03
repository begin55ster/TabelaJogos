package br.com.jefferson.Controller;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.jefferson.Abstrata.ActionMessager;
import br.com.jefferson.Model.Role;
import br.com.jefferson.Model.Usuario;

@SessionScoped
@ManagedBean(name = "usuarioController")
public class UsuarioController extends ActionMessager {
	
	public static final String INJECTION_NAME = "#{usuarioController}";
	
	private Usuario user;
	
	public boolean isAdmin() {
		return Role.ADMIN.equals(user.getRole());
	}
	
	public boolean isDefaultUser() {
		return Role.USER.equals(user.getRole());
	}
	
	public Usuario getUser() {
		return user;
	}
	
	public void setUser(Usuario user) {
		this.user = user;
	}
	
	public String logOut() {
		((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
		return "login";
	}


}	
