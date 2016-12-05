package br.com.jefferson.RN;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.jefferson.DAO.TimeDao;
import br.com.jefferson.Exception.CadastroInvalidoException;
import br.com.jefferson.Model.Jogador;
import br.com.jefferson.Model.Time;

@Stateless
public class TimeRN {
	
	@Inject
	private TimeDao timeDao;
	

	public boolean verificaSeTimeJogadorEstaCadastrado(Boolean isEdicao, String nomeTime, Jogador jogador) throws CadastroInvalidoException {
		Boolean timeCadastrado = Boolean.FALSE;
		
			List<Time> listaTime = timeDao.listar();
			if(!isEdicao) {
				for(Time verTime : listaTime) {
					if(verificaTimeJogadorJaCadastrado(nomeTime, jogador, verTime)) {
						throw new CadastroInvalidoException();
					} 
				}
			} else {
				for(Time verTime : listaTime) {
					if(verificaTimeJaCadastrado(nomeTime, verTime)) {
						throw new CadastroInvalidoException();
					} 
				}
			}
		
		return timeCadastrado;
		
	}

	private boolean verificaTimeJaCadastrado(String nomeTime, Time verTime) {
		return verTime.getNomeTime().equals(nomeTime);
	}

	private boolean verificaTimeJogadorJaCadastrado(String nomeTime, Jogador jogador, Time verTime) {
		return verTime.getNomeTime().equals(nomeTime) ||
				verTime.getJogador().getNomeJogador().equals(jogador.getNomeJogador());
	}

	public void gravarTime(Time time) {
			timeDao.persistir(time);
	}

	public void atualizarTime(Time time) {
			timeDao.atualizar(time);
	}

	public List<Time> recuperarTime() {
		return timeDao.listar();
	}
	
	public Time recuperaTime(Time time) {
		return timeDao.obterPorId(time.getSeq());
	}

	public void removerTime(Time time) throws Exception {
		if(time.getSeq() != null) {
			time = timeDao.obterPorId(time.getSeq());
			timeDao.remover(time);
			System.out.println("Chegou aqui");
		} else {
			throw new Exception("Não foi encontrado time para remover ");
		}
		
	}

	public Time recuperarTimePorNomeTime(String timeA) {
		return timeDao.recuperarTimePorNomeTime(timeA);
	}


}
