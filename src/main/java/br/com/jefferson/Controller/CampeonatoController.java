package br.com.jefferson.Controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.jefferson.Abstrata.ActionMessager;
import br.com.jefferson.Model.CampeonatoCopa;
import br.com.jefferson.Model.CampeonatoMataMata;
import br.com.jefferson.Model.CampeonatoPontosCorridos;
import br.com.jefferson.Model.TipoCampeonato;
import br.com.jefferson.RN.CampeonatoRN;
import br.com.jefferson.RN.JogosRN;
import br.com.jefferson.RN.TimeCampeonatoRN;

@ManagedBean(name="campeonatoController")
@SessionScoped
public class CampeonatoController extends ActionMessager {
	
	private static final String TIPO_SUCESSO = "Sucesso !";
	private static final String INCLUSAO_CAMPEONATO_SUCESSO = "Campeonato cadastrado !";
	private static final String VOLTAR_INDEX = "navegar-voltarConfiguracoes";
	
	
	@EJB
	private TimeCampeonatoRN timeCampeonatoRN;
	
	@EJB
	private JogosRN jogosRN;
	
	@EJB
	private CampeonatoRN campeonatoRN;
	
	private TipoCampeonato nomeCampeonato ;
	
	
	public void gravarCampeonato() {
		try {
			gravarCampeonatos();
			messagerInformativa(TIPO_SUCESSO, INCLUSAO_CAMPEONATO_SUCESSO);
		} catch (Exception e) {
			messagerErro(e.getMessage());
		} finally {
			limparCampos();
		}
	}

	private void gravarCampeonatos() throws Exception {
		if(nomeCampeonato.getLabel().equals(TipoCampeonato.PONTOS_CORRIDO.getLabel())) {
			gravarCampeonatoPontosCorridos();
		} else if(nomeCampeonato.getLabel().equals(TipoCampeonato.MATA_MATA.getLabel())) {
			gravarCampeonatoMataMata();
		} else if(nomeCampeonato.getLabel().equals(TipoCampeonato.COPA.getLabel())) {
			gravarCampeonatoCopa();
		}
	}

	private void gravarCampeonatoCopa() throws Exception {
		CampeonatoCopa campeonatoCopa = new CampeonatoCopa();
		campeonatoCopa.setIdentificador(3);
		campeonatoCopa.setNomeCampeonato(this.nomeCampeonato.getLabel());
		this.campeonatoRN.persistir(campeonatoCopa);
	}

	private void gravarCampeonatoMataMata() throws Exception {
		CampeonatoMataMata campeonatoMataMata = new CampeonatoMataMata();
		campeonatoMataMata.setIdentificador(2);
		campeonatoMataMata.setNomeCampeonato(this.nomeCampeonato.getLabel());
		this.campeonatoRN.persistir(campeonatoMataMata);
	}

	private void gravarCampeonatoPontosCorridos() throws Exception {
		CampeonatoPontosCorridos campeonato = new CampeonatoPontosCorridos();
		campeonato.setIdentificador(1);
		campeonato.setNomeCampeonato(this.nomeCampeonato.getLabel());
		this.campeonatoRN.persistir(campeonato);
	}
	
	public String voltarParaHome() {
		return VOLTAR_INDEX;
	}
	
	public void limparCampos() {
		this.nomeCampeonato = null;
	}
	
	public TipoCampeonato[] buscarTipoCampeonato() {
	        return TipoCampeonato.values();
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

	public TipoCampeonato getNomeCampeonato() {
		return nomeCampeonato;
	}

	public void setNomeCampeonato(TipoCampeonato nomeCampeonato) {
		this.nomeCampeonato = nomeCampeonato;
	}

	
	
	
	
}
