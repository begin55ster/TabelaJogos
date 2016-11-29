package br.com.jefferson.Controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.jefferson.Abstrata.ActionMessager;
import br.com.jefferson.Model.Jogador;
import br.com.jefferson.RN.JogadorRN;

@ManagedBean(name="jogadorController")
@ViewScoped
public class JogadorController extends ActionMessager {
	
	private static final String NOME_JOGADOR_OBRIGATORIO = "Por favor, preencher o nome do jogador !";
	private static final String TIPO_SUCESSO = "Sucesso !";
	private static final String JOGADOR_CADASTRADO = "Jogador Cadastrado !";
	private static final String JOGADOR_ATUALIZADO = "Jogador Atualizado !";
	private static final String JOGADOR_REMOVIDO = "Jogador Removido !";
	private Jogador jogador = new Jogador();
	private Boolean isEdicao = Boolean.FALSE;
	private Jogador selectJogador;
	private List<Jogador> listaJogadores;
	private String nomeJogador;
	
	@EJB
	private JogadorRN jogadorRN;

	public void gravar() {
		if(nomeJogador != null || nomeJogador.isEmpty()){
			jogador.setNomeJogador(nomeJogador);
			cadastrarJogador();
		} else {
			messagerErro(NOME_JOGADOR_OBRIGATORIO);
		}
	}
	
	public void cadastrarJogador() {
		try {
			if(!isEdicao) {
				this.jogadorRN.gravar(jogador);
				messagerInformativa(TIPO_SUCESSO, JOGADOR_CADASTRADO);
			} else {
				this.jogadorRN.atualizarJogador(jogador);
				messagerInformativa(TIPO_SUCESSO, JOGADOR_ATUALIZADO);
			}
		} catch (Exception e) {
			messagerErro(e.getMessage());
		} finally {
			limparCampos();
		}
	}
	
	public void removerJogador() {
		try {
			jogadorRN.removerJogador(selectJogador);
			messagerInformativa(TIPO_SUCESSO, JOGADOR_REMOVIDO);
		} catch (Exception e) {
			messagerErro(e.getMessage());
		}
	}
	
	public List<Jogador> buscarJogadores() {
		listaJogadores = jogadorRN.recuperarJogadores();
		return listaJogadores;	
	}
	
	public void editarJogador() {
			this.isEdicao = Boolean.TRUE;
			this.jogador = jogadorRN.recuperarJogador(selectJogador);
			nomeJogador = jogador.getNomeJogador();
	}

	public void limparCampos() {
		jogador = new Jogador();
		nomeJogador = "";
		selectJogador = null;
	}
	
	public String voltarParaHome() {
		return "navegar-CadastroJogadorHome";	
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public Jogador getSelectJogador() {
		return selectJogador;
	}

	public void setSelectJogador(Jogador selectJogador) {
		this.selectJogador = selectJogador;
	}

	public List<Jogador> getListaJogadores() {
		return listaJogadores;
	}

	public void setListaJogadores(List<Jogador> listaJogadores) {
		this.listaJogadores = listaJogadores;
	}
	
	public String getNomeJogador() {
		return nomeJogador;
	}

	public void setNomeJogador(String nomeJogador) {
		this.nomeJogador = nomeJogador;
	}

}
