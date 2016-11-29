package br.com.jefferson.RN;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.jefferson.DAO.JogadorDao;
import br.com.jefferson.Model.Jogador;
import br.com.jefferson.Model.Time;

@Stateless
public class JogadorRN {
	
	@Inject
	private JogadorDao jogadorDao;
	
	@EJB
	private TimeRN timeRN;
	
	public void removerJogador(Jogador jogador) throws Exception {
		
		List<Time> times = timeRN.recuperarTime();
		for(Time time : times) {
			if(time.getJogador().equals(jogador)) {
				throw new Exception("Jogador não pode ser Removido. Remova primeiro o time que esta relacionado com o Jogador .");
			}
		}
		jogadorDao.remover(jogador);
	}

	public void gravar(Jogador jogador) {
		jogadorDao.persistir(jogador);
	}

	public void atualizarJogador(Jogador jogador) {
		jogadorDao.atualizar(jogador);
	}

	public List<Jogador> recuperarJogadores() {
		return jogadorDao.listar();
	}

	public Jogador recuperarJogador(Jogador selectJogador) {
		return jogadorDao.obterPorId(selectJogador.getSeq());
	}

	public Jogador findById(Long jogadorId) {
		return jogadorDao.obterPorId(jogadorId);
	}

}
