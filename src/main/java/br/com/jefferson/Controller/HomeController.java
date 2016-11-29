package br.com.jefferson.Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.jefferson.Abstrata.ActionMessager;

@ManagedBean(name="homeController")
@RequestScoped
public class HomeController extends ActionMessager {
	

	public String irParaCadastroTime() {
		return "navegar-CadastroTime";
	}
	
	public String irParaCadastroJogador() {
		return "navegar-CadastroJogador";
	}
	
	public String irParaMontarCampeonato() {
		return "navegar-MontarCampeonato";
	}
	
	public String irParaConfiguracoes() {
		return "navegar-Configuracoes";
	}
}
