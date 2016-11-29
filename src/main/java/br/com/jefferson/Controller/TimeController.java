package br.com.jefferson.Controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.jefferson.Abstrata.ActionMessager;
import br.com.jefferson.Model.Jogador;
import br.com.jefferson.Model.Time;
import br.com.jefferson.RN.JogadorRN;
import br.com.jefferson.RN.TimeRN;

@ManagedBean(name="timeController")
@ViewScoped
public class TimeController extends ActionMessager {
	
	private static final String INCLUSAO_TIME_SUCESSO = "Time cadastrado !";
	private static final String ALTERACAO_TIME_SUCESSO = "Time atulizado !";
	private static final String TIPO_SUCESSO = "Sucesso !";
	private static final String CAMPOS_OBRIGATORIO_NAO_PREENCHIDOS = "É obrigatório que os campos Nome do Time e Jogador estejam preenchidos.";
	private static final String TIME_REMOVIDO = "Time Exluído !";
	private Time time = new Time();
	private Long jogadorId = 0l;
	private String nomeTime;
	private List<Jogador> listaJogadores;
	private List<Time> times;
	private Time selectTime;
	private Boolean isEdicao = Boolean.FALSE;
	
	@EJB
	private TimeRN timeRN;
	
	@EJB
	private JogadorRN jogadorRN;
	
	public void gravar() {
		if(jogadorId == null || nomeTime.isEmpty() || nomeTime == null) {
			messagerErro(CAMPOS_OBRIGATORIO_NAO_PREENCHIDOS);
		} else {
			gravarTime();
		}
	}
	
	public void gravarTime() {
		
		Jogador jogador = jogadorRN.findById(jogadorId);
		try {
			if (!timeRN.verificaSeTimeJogadorEstaCadastrado(isEdicao, nomeTime, jogador)) {
				if (!isEdicao) {
					time.setNomeTime(nomeTime);
					time.setJogador(jogador);
					this.timeRN.gravarTime(time);
					messagerInformativa(TIPO_SUCESSO, INCLUSAO_TIME_SUCESSO);
				} else {
					time.setNomeTime(nomeTime);
					time.setJogador(jogador);
					this.timeRN.atualizarTime(time);
					messagerInformativa(TIPO_SUCESSO, ALTERACAO_TIME_SUCESSO);
				}
			}
		} catch (Exception e) {
			messagerErro(e.getMessage());
		} finally {
			limparCampos();
		}
	}
	
	public void limparCampos() {
		jogadorId = null;
		nomeTime = "";
		time = new Time();
		selectTime = null;
		isEdicao = Boolean.FALSE;
	}

	public List<Jogador> buscarJogadores() {
		listaJogadores = jogadorRN.recuperarJogadores();
		return listaJogadores;	
	}
	
	public List<Time> buscarTimes() {
		times = timeRN.recuperarTime();
		return times;	
	}
	
	public void editarTime() {
		isEdicao = Boolean.TRUE;
		time = timeRN.recuperaTime(selectTime);
		nomeTime = time.getNomeTime();
		jogadorId = time.getJogador().getSeq();
	}
	
	public void removerTime() throws Exception {
		try {
			timeRN.removerTime(selectTime);
			messagerInformativa(TIPO_SUCESSO, TIME_REMOVIDO);
		} catch (Exception e) {
			messagerErro(e.getMessage());
		}
	}

	public String voltarParaHome() {
		return "navegar-CadastroTimehome";	
	}

	public Long getJogadorId() {
		return jogadorId;
	}

	public void setJogadorId(Long jogadorId) {
		this.jogadorId = jogadorId;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}
	
	public List<Jogador> getListaJogadores() {
		return listaJogadores;
	}

	public void setListaJogadores(List<Jogador> listaJogadores) {
		this.listaJogadores = listaJogadores;
	}

	public List<Time> getTimes() {
		return times;
	}

	public void setTimes(List<Time> times) {
		this.times = times;
	}

	public Time getSelectTime() {
		return selectTime;
	}

	public void setSelectTime(Time selectTime) {
		this.selectTime = selectTime;
	}

	public Boolean getIsEdicao() {
		return isEdicao;
	}

	public void setIsEdicao(Boolean isEdicao) {
		this.isEdicao = isEdicao;
	}

	public String getNomeTime() {
		return nomeTime;
	}

	public void setNomeTime(String nomeTime) {
		this.nomeTime = nomeTime;
	}

}
