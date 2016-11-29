package br.com.jefferson.Controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.jefferson.Abstrata.ActionMessager;
import br.com.jefferson.Model.CampeonatoPontosCorridos;
import br.com.jefferson.RN.CampeonatoRN;
import br.com.jefferson.RN.JogosRN;
import br.com.jefferson.RN.TimeCampeonatoRN;

@ManagedBean(name="campeonatoController")
@SessionScoped
public class CampeonatoController extends ActionMessager {
	
	private static final String TIPO_SUCESSO = "Sucesso !";
	private static final String INCLUSAO_CAMPEONATO_SUCESSO = "Campeonato cadastrado !";
	private static final String VOLTAR_CONFIGURACOES = "navegar-Configuracoes";
	
	
	@EJB
	private TimeCampeonatoRN timeCampeonatoRN;
	
	@EJB
	private JogosRN jogosRN;
	
	@EJB
	private CampeonatoRN campeonatoRN;
	
	private int identificadorCampeonato = 0;
	private String nomeCampeonato ;
	
	
	public String gravarCampeonato() {
		try {
			CampeonatoPontosCorridos campeonato = new CampeonatoPontosCorridos();
			campeonato.setIdentificador(this.identificadorCampeonato);
			campeonato.setNomeCampeonato(this.nomeCampeonato);
			this.campeonatoRN.persistir(campeonato);
			messagerInformativa(TIPO_SUCESSO, INCLUSAO_CAMPEONATO_SUCESSO);
		} catch (Exception e) {
			messagerErro(e.getMessage());
		} finally {
			limparCampos();
		}
		return VOLTAR_CONFIGURACOES;
		
	}
	
	private void limparCampos() {
		this.identificadorCampeonato = 0;
		this.nomeCampeonato = null;
	}

	public String gravarJogoPontosCorridos() {
		
		 jogosRN.criarTabelaDeJogos();
		 timeCampeonatoRN.montarClassificacao();
		 
		 return "navegar-pontosCorridos";
	}
	
	public String gravarJogoMataMata() {
		return null;
	}
	
	public String gravarFaseDeGrupos() {
		return null;
	}

	public int getIdentificadorCampeonato() {
		return identificadorCampeonato;
	}

	public void setIdentificadorCampeonato(int identificadorCampeonato) {
		this.identificadorCampeonato = identificadorCampeonato;
	}

	public String getNomeCampeonato() {
		return nomeCampeonato;
	}

	public void setNomeCampeonato(String nomeCampeonato) {
		this.nomeCampeonato = nomeCampeonato;
	}
	
	
	
}
