package br.com.jefferson.Controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.jefferson.Abstrata.ActionMessager;
import br.com.jefferson.Model.Usuario;
import br.com.jefferson.RN.UsuarioRN;

@RequestScoped
@ManagedBean
public class LoginController extends ActionMessager{
	
	
	@ManagedProperty(value = UsuarioController.INJECTION_NAME)
	
	@Inject
	private UsuarioController usuarioController;
	
	@EJB
	UsuarioRN usuarioRN;
	
	public static final String VERIFICAR_LOGIN_SENHA = "Por favor, verifique seu login ou senha !";
	
	private String login;
	private String senha;
	
	
	public void setUsuarioController(UsuarioController usuarioController) {
		this.usuarioController = usuarioController;
	}
	
	private Usuario isValidLogin(String login, String senha) {
		Usuario user = usuarioRN.findByLogin(login);
		
		if(user == null || !senha.equals(user.getSenha())) {
			return null;
		}
		return user;
	}
	
	public String entrar() {
		Usuario user = isValidLogin(login, senha);
		if(user != null) {
			usuarioController.setUser(user);
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
			request.getSession().setAttribute("user", user);
			return "index";
		}
		messagerErro("VERIFICAR_LOGIN_SENHA");
		return null;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	

}
