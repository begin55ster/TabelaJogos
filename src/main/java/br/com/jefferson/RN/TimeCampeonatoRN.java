package br.com.jefferson.RN;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.jefferson.DAO.CampeonatoDao;
import br.com.jefferson.DAO.TimeCampeonatoDao;
import br.com.jefferson.Model.CampeonatoPontosCorridos;
import br.com.jefferson.Model.Jogo;
import br.com.jefferson.Model.Time;
import br.com.jefferson.Model.TimeCampeonato;

@Stateless
public class TimeCampeonatoRN {
	
	@Inject
	private TimeCampeonatoDao timeCampeonatoDao;
	
	@Inject
	private CampeonatoDao campeonatoDao;
	
	@EJB
	private JogosRN jogosRN;
	
	@EJB
	private TimeRN timeRN;

	public void montarClassificacao() {
		List<Time> times = timeRN.recuperarTime();
		CampeonatoPontosCorridos novoCampeonato = new CampeonatoPontosCorridos();
		List<CampeonatoPontosCorridos> campeonatos = campeonatoDao.listar();
		
		novoCampeonato = buscarTipoCampeonatoPontosCorridos(campeonatos, novoCampeonato);
		
		for(Time time : times) {
			TimeCampeonato timeCampeonato = new TimeCampeonato();
			
				int saldoGols = 0;
			
				timeCampeonato.setTime(time);
				timeCampeonato.setPontos(0);
				timeCampeonato.setVitorias(0);
				timeCampeonato.setEmpates(0);
				timeCampeonato.setDerrotas(0);
				timeCampeonato.setGolsPro(0);
				timeCampeonato.setGolsContra(0);
				saldoGols = timeCampeonato.getGolsPro() - timeCampeonato.getGolsContra();
				timeCampeonato.setSaldoGols(saldoGols);
				timeCampeonato.setCampeonato(novoCampeonato);
				
				timeCampeonatoDao.persistir(timeCampeonato);
		}
		
	}
	
	private CampeonatoPontosCorridos buscarTipoCampeonatoPontosCorridos(List<CampeonatoPontosCorridos> campeonatos, CampeonatoPontosCorridos novoCampeonato) {
		for(CampeonatoPontosCorridos campeonato : campeonatos) {
			if(campeonato.getIdentificador() == 3) {
				novoCampeonato = campeonato;
			}
		}
		return novoCampeonato;
	}

	public List<TimeCampeonato> buscarClassificacao() {
		return timeCampeonatoDao.listar();
	}
	
	public void atualizarTabelaDeClassificacao(Jogo jogo) {
		TimeCampeonato timeCampeonato = new TimeCampeonato();
		timeCampeonato = atualizarTimeA(jogo);
		timeCampeonatoDao.atualizar(timeCampeonato);
		timeCampeonato = new TimeCampeonato();
		timeCampeonato = atualizarTimeB(jogo);
		timeCampeonatoDao.atualizar(timeCampeonato);
	}

	private TimeCampeonato atualizarTimeB(Jogo jogoAtualizado) {
		Time time = timeRN.recuperarTimePorNomeTime(jogoAtualizado.getTimeB());
		TimeCampeonato timeCampeonatoAtualizado = timeCampeonatoDao.obterPorId(time.getSeq());
		
		 timeCampeonatoAtualizado.setTime(time);
		 calcularPontosTimeB(jogoAtualizado,timeCampeonatoAtualizado);
		 calcularVitoriaTimeB(jogoAtualizado,timeCampeonatoAtualizado);
		 calcularDerrotaTimeB(jogoAtualizado,timeCampeonatoAtualizado);
		 calcularEmpateTimeB(jogoAtualizado,timeCampeonatoAtualizado);
		 calcularGolsProTimeB(jogoAtualizado,timeCampeonatoAtualizado);
		 calcularGolsContraTimeB(jogoAtualizado,timeCampeonatoAtualizado);
		 calcularSaldoGolsTimeB(jogoAtualizado,timeCampeonatoAtualizado);
		
		
		return timeCampeonatoAtualizado;
	}

	private void calcularSaldoGolsTimeB(Jogo jogoAtualizado, TimeCampeonato timeCampeonatoAtualizado) {
		int saldoGolsAnterior = timeCampeonatoAtualizado.getSaldoGols();
		int saldoGolsDoJogo = 0;
		if(jogoAtualizado.getGolsTimeB() > jogoAtualizado.getGolsTimeA()) {
			saldoGolsDoJogo = jogoAtualizado.getGolsTimeB() - jogoAtualizado.getGolsTimeA();
			timeCampeonatoAtualizado.setSaldoGols(saldoGolsAnterior + saldoGolsDoJogo);
		} else {
			saldoGolsDoJogo = jogoAtualizado.getGolsTimeA() - jogoAtualizado.getGolsTimeB();
			timeCampeonatoAtualizado.setSaldoGols(saldoGolsAnterior - saldoGolsDoJogo);
		}
	}

	private void calcularGolsContraTimeB(Jogo jogoAtualizado, TimeCampeonato timeCampeonatoAtualizado) {
		int golsContraAntigos = timeCampeonatoAtualizado.getGolsContra();
		int novosGolsContra = jogoAtualizado.getGolsTimeA();
		timeCampeonatoAtualizado.setGolsContra(golsContraAntigos + novosGolsContra);
	}

	private void calcularGolsProTimeB(Jogo jogoAtualizado, TimeCampeonato timeCampeonatoAtualizado) {
		int golsProAntigos = timeCampeonatoAtualizado.getGolsPro();
		int novoGolsPro = jogoAtualizado.getGolsTimeB();
		timeCampeonatoAtualizado.setGolsPro(golsProAntigos + novoGolsPro);
	}

	private void calcularEmpateTimeB(Jogo jogoAtualizado, TimeCampeonato timeCampeonatoAtualizado) {
		int saldoGolsAnterior = timeCampeonatoAtualizado.getSaldoGols();
		int empateAntigo = timeCampeonatoAtualizado.getEmpates();
		int novoEmpate = 1;
		if(jogoAtualizado.getGolsTimeB().equals(jogoAtualizado.getGolsTimeA())) {
			timeCampeonatoAtualizado.setEmpates(empateAntigo + novoEmpate);
			timeCampeonatoAtualizado.setPontos(saldoGolsAnterior + novoEmpate);
		}
	}

	private void calcularDerrotaTimeB(Jogo jogoAtualizado, TimeCampeonato timeCampeonatoAtualizado) {
		int derrotasAntigas = timeCampeonatoAtualizado.getDerrotas();
		int novaDerrota = 1;
		if(jogoAtualizado.getGolsTimeB() < jogoAtualizado.getGolsTimeA()) {
			timeCampeonatoAtualizado.setDerrotas(derrotasAntigas + novaDerrota);
		}
	}

	private void calcularVitoriaTimeB(Jogo jogoAtualizado, TimeCampeonato timeCampeonatoAtualizado) {
		int vitoriasAntigas = timeCampeonatoAtualizado.getVitorias();
		int novaVitoria = 1;
		if(jogoAtualizado.getGolsTimeB() > jogoAtualizado.getGolsTimeA()) {
			timeCampeonatoAtualizado.setVitorias(vitoriasAntigas + novaVitoria);
		}
		
	}

	private void calcularPontosTimeB(Jogo jogoAtualizado, TimeCampeonato timeCampeonatoAtualizado) {
		int pontuacaoAntiga = timeCampeonatoAtualizado.getPontos();
		int novaPontuacao = 3;
		if(jogoAtualizado.getGolsTimeB() > jogoAtualizado.getGolsTimeA()) {
			timeCampeonatoAtualizado.setPontos(pontuacaoAntiga + novaPontuacao);
		}
		
	}

	private TimeCampeonato atualizarTimeA(Jogo jogoAtualizado) {
		Time time = timeRN.recuperarTimePorNomeTime(jogoAtualizado.getTimeA());
		TimeCampeonato timeCampeonatoAtualizado = timeCampeonatoDao.obterPorId(time.getSeq());
		
		 timeCampeonatoAtualizado.setTime(time);
		 calcularPontosTimeA(jogoAtualizado,timeCampeonatoAtualizado);
		 calcularVitoriaTimeA(jogoAtualizado,timeCampeonatoAtualizado);
		 calcularDerrotaTimeA(jogoAtualizado,timeCampeonatoAtualizado);
		 calcularEmpateTimeA(jogoAtualizado,timeCampeonatoAtualizado);
		 calcularGolsProTimeA(jogoAtualizado,timeCampeonatoAtualizado);
		 calcularGolsContraTimeA(jogoAtualizado,timeCampeonatoAtualizado);
		 calcularSaldoGolsTimeA(jogoAtualizado,timeCampeonatoAtualizado);
		
		return timeCampeonatoAtualizado;
	}

	private void calcularSaldoGolsTimeA(Jogo jogoAtualizado, TimeCampeonato timeCampeonatoAtualizado) {
		int saldoGolsAnterior = timeCampeonatoAtualizado.getSaldoGols();
		int saldoGolsDoJogo = 0;
		if(jogoAtualizado.getGolsTimeA() > jogoAtualizado.getGolsTimeB()) {
			saldoGolsDoJogo = jogoAtualizado.getGolsTimeA() - jogoAtualizado.getGolsTimeB();
			timeCampeonatoAtualizado.setSaldoGols(saldoGolsAnterior + saldoGolsDoJogo);
		} else {
			saldoGolsDoJogo = jogoAtualizado.getGolsTimeB() - jogoAtualizado.getGolsTimeA();
			timeCampeonatoAtualizado.setSaldoGols(saldoGolsAnterior - saldoGolsDoJogo);
		}
	}

	private void calcularGolsContraTimeA(Jogo jogoAtualizado, TimeCampeonato timeCampeonatoAtualizado) {
		int golsContraAntigos = timeCampeonatoAtualizado.getGolsContra();
		int novosGolsContra = jogoAtualizado.getGolsTimeB();
		timeCampeonatoAtualizado.setGolsContra(golsContraAntigos + novosGolsContra);
	}

	private void calcularGolsProTimeA(Jogo jogoAtualizado, TimeCampeonato timeCampeonatoAtualizado) {
		int golsProAntigos = timeCampeonatoAtualizado.getGolsPro();
		int novoGolsPro = jogoAtualizado.getGolsTimeA();
		timeCampeonatoAtualizado.setGolsPro(golsProAntigos + novoGolsPro);
	}

	private void calcularEmpateTimeA(Jogo jogoAtualizado, TimeCampeonato timeCampeonatoAtualizado) {
		int saldoGolsAnterior = timeCampeonatoAtualizado.getSaldoGols();
		int empateAntigo = timeCampeonatoAtualizado.getEmpates();
		int novoEmpate = 1;
		if(jogoAtualizado.getGolsTimeA().equals(jogoAtualizado.getGolsTimeB())) {
			timeCampeonatoAtualizado.setEmpates(empateAntigo + novoEmpate);
			timeCampeonatoAtualizado.setPontos(saldoGolsAnterior + novoEmpate);
		}
	}

	private void calcularDerrotaTimeA(Jogo jogoAtualizado, TimeCampeonato timeCampeonatoAtualizado) {
		int derrotasAntigas = timeCampeonatoAtualizado.getDerrotas();
		int novaDerrota = 1;
		if(jogoAtualizado.getGolsTimeA() < jogoAtualizado.getGolsTimeB()) {
			timeCampeonatoAtualizado.setDerrotas(derrotasAntigas + novaDerrota);
		}
	}

	private void calcularVitoriaTimeA(Jogo jogoAtualizado, TimeCampeonato timeCampeonatoAtualizado) {
		int vitoriasAntigas = timeCampeonatoAtualizado.getVitorias();
		int novaVitoria = 1;
		if(jogoAtualizado.getGolsTimeA() > jogoAtualizado.getGolsTimeB()) {
			timeCampeonatoAtualizado.setVitorias(vitoriasAntigas + novaVitoria);
		}
		
	}

	private void calcularPontosTimeA(Jogo jogoAtualizado, TimeCampeonato timeCampeonatoAtualizado) {
		int pontuacaoAntiga = timeCampeonatoAtualizado.getPontos();
		int novaPontuacao = 3;
		if(jogoAtualizado.getGolsTimeA() > jogoAtualizado.getGolsTimeB()) {
			timeCampeonatoAtualizado.setPontos(pontuacaoAntiga + novaPontuacao);
		}
		
	}

}
