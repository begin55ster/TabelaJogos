package br.com.jefferson.RN;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.jefferson.DAO.CampeonatoDao;
import br.com.jefferson.DAO.JogoDao;
import br.com.jefferson.DAO.TimeDao;
import br.com.jefferson.Model.CampeonatoPontosCorridos;
import br.com.jefferson.Model.Jogo;
import br.com.jefferson.Model.Time;
import br.com.jefferson.Model.TipoCampeonato;

@Stateless
public class JogosRN {
	
	@Inject
	private TimeDao timeDao;
	
	@Inject
	private JogoDao jogoDao;
	
	@Inject
	private CampeonatoDao campeonatoDao;
	
	
	public Map<Integer, List<Jogo>> criarTabelaDeJogos() {
		
		List<Time> lista = timeDao.listar();
		Map<Integer, List<Jogo>> rodadas = new HashMap<Integer, List<Jogo>>();
		Map<Integer, List<Jogo>> rodadas1 = new HashMap<Integer, List<Jogo>>();
		List<Jogo> listaJogos = new ArrayList<Jogo>();
		List<Jogo> listaJogos1 = new ArrayList<Jogo>();
		List<String> times = new ArrayList<String>();
		
		for (Time cadastro: lista) {
			times.add(cadastro.getNomeTime());
			Collections.shuffle(times);
		}
		
	    if (times.size() % 2 == 1) {
	    	times.add(0, "");
	    }

	    int t = times.size();
	    int m = times.size() / 2;
	    for (int i = 1; i <= t - 1; i++) {
	    	listaJogos = new ArrayList<Jogo>();
	    	listaJogos1 = new ArrayList<Jogo>();
	        for (int j = 0; j < m; j++) {
	        	Jogo jogo = new Jogo(); 
	        	Jogo jogo1 = new Jogo();
	            if (times.get(j).isEmpty())
	                continue;

	            //Teste para ajustar o mando de campo
	            if (j % 2 == 1 || i % 2 == 1 && j == 0){
	            	jogo.setTimeA(times.get(t - j - 1));
	            	jogo.setTimeB(times.get(j));
	            	jogo1.setTimeA(times.get(j));
	            	jogo1.setTimeB(times.get(t - j - 1));
	            }
	            else{
	            	jogo.setTimeB(times.get(j));
	            	jogo.setTimeA(times.get(t - j - 1));
	            	jogo1.setTimeB(times.get(t - j - 1));
	            	jogo1.setTimeA(times.get(j));
	            }
	            jogo.setRodada(i);
	            jogo1.setRodada(i);
	            listaJogos.add(jogo);
	            listaJogos1.add(jogo1);
	        }
	        //Gira os clubes no sentido horário, mantendo o primeiro no lugar
	        times.add(1, times.remove(times.size()-1));
	        rodadas.put(i, listaJogos);
	        rodadas1.put(i, listaJogos1);
	    }
	    Map<Integer, List<Jogo>> rodadasFinal = montarRodada(rodadas,rodadas1);
	    
	    gravarTabelaDeJogos(rodadasFinal);

		return rodadasFinal;
	}
	
	private void gravarTabelaDeJogos(Map<Integer, List<Jogo>> rodadasFinal) {
		List<Jogo> listaJogos = new ArrayList<Jogo>();
		CampeonatoPontosCorridos novoCampeonato = campeonatoDao.recuperarCampeonatoPontosCorridos(TipoCampeonato.PONTOS_CORRIDO.getLabel());
		
	   for(Map.Entry<Integer, List<Jogo>> vetorValor : rodadasFinal.entrySet()) {
		   listaJogos = vetorValor.getValue();
		   for(Jogo jogo : listaJogos) {
			   jogo.setCampeonato(novoCampeonato);
			   jogoDao.persistir(jogo);
		   }
        }
		
	}

	private Map<Integer, List<Jogo>> montarRodada(Map<Integer, List<Jogo>> rodadas, Map<Integer, List<Jogo>> rodadas1) {
		
		Map<Integer, List<Jogo>> resultado = new HashMap<Integer, List<Jogo>>();
		Integer rod = 1;
		
		   for(Map.Entry<Integer, List<Jogo>> vetorValor : rodadas.entrySet()) {
			   List<Jogo> listaJogos = new ArrayList<Jogo>();
			   listaJogos = vetorValor.getValue();
			   resultado.put(rod, listaJogos);
			   rod ++;
	        }
		   for(Map.Entry<Integer, List<Jogo>> vetorValor : rodadas1.entrySet()) {
			   List<Jogo> listaJogos = new ArrayList<Jogo>();
			   listaJogos = vetorValor.getValue();
			   listaJogos = atualizarValorRodadaVolta(listaJogos,rod);
			   resultado.put(rod, listaJogos);
			   rod ++;
	        }
		
		return resultado;
	}
		
	private List<Jogo> atualizarValorRodadaVolta(List<Jogo> listaJogos, Integer rod) {
		List<Jogo> listaAtualizada = new ArrayList<Jogo>();
		for(Jogo jogo : listaJogos) {
			jogo.setRodada(rod);
			listaAtualizada.add(jogo);
		}
		return listaAtualizada;
	}

	public List<Jogo> buscarJogos() {
		return jogoDao.listar();
	}

	public void atualizarJogo(Jogo jogo) {
		jogoDao.atualizar(jogo);
	}

	public Jogo buscarJogoAtualizado(Jogo jogo) {
		return jogoDao.obterPorId(jogo.getSeq());
	}
	
}
