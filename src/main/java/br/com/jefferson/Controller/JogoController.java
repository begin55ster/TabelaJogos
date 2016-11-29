package br.com.jefferson.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.jefferson.Model.Jogo;
import br.com.jefferson.RN.JogosRN;
import br.com.jefferson.RN.TimeCampeonatoRN;

@ManagedBean(name="jogoController")
@SessionScoped
public class JogoController {
	
	private Jogo jogo = new Jogo();
	private int golsTimeA = 0;
	private int golsTimeB = 0;
	
	@EJB
	private TimeCampeonatoRN timeCampeonatoRN;
	
	@EJB
	private JogosRN jogosRN;
	
	public List<Jogo> buscarJogos() {
		return jogosRN.buscarJogos();
		
	}
	
	public List<Integer> limitParaGrid() {
		List<Integer> limit = new ArrayList<Integer>();
		limit.add(1);
		return limit;
	}
	
	public void atualizarTimeA(Jogo jogo) {
		this.jogo = jogo;
		this.jogo.setGolsTimeA(golsTimeA);
	}
	
	public void atualizarTabelaDeClassificacao(Jogo jogo) {
		jogo = this.jogo;
		jogo.setGolsTimeB(golsTimeB);
		jogosRN.atualizarJogo(jogo);
		timeCampeonatoRN.atualizarTabelaDeClassificacao(jogo);
		limparGols();
	}

	private void limparGols() {
		this.golsTimeA = 0;	
		this.golsTimeB = 0;
	}

	public int getGolsTimeA() {
		return golsTimeA;
	}

	public void setGolsTimeA(int golsTimeA) {
		this.golsTimeA = golsTimeA;
	}

	public int getGolsTimeB() {
		return golsTimeB;
	}

	public void setGolsTimeB(int golsTimeB) {
		this.golsTimeB = golsTimeB;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}
	
	

}
