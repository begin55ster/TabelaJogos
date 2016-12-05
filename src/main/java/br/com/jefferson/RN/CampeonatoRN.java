package br.com.jefferson.RN;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.jefferson.DAO.CampeonatoDao;
import br.com.jefferson.Model.Campeonato;
import br.com.jefferson.Model.CampeonatoPontosCorridos;

@Stateless
public class CampeonatoRN {
	
	private static final String ERRO_CADASTRO_CAMPEONATO = "Já existe um campeonato com o indentificador cadastrado .";
	
	@Inject
	private CampeonatoDao campeonatoDao;

	public void persistir(Campeonato campeonato) throws Exception {
		List<CampeonatoPontosCorridos> campeonatos = campeonatoDao.listar();
			if(campeonatos != null && !campeonatos.isEmpty()) {
				campeonatoJaCadastrado(campeonato, campeonatos); 
				campeonatoDao.persistir(campeonato);
			} else {
				campeonatoDao.persistir(campeonato);
			}
	}

	private void campeonatoJaCadastrado(Campeonato campeonato, List<CampeonatoPontosCorridos> campeonatos) throws Exception {
		for(CampeonatoPontosCorridos campeonatoPC : campeonatos) {
			 verificaPersisteCampeonato(campeonato, campeonatoPC);
		}
	}

	private void verificaPersisteCampeonato(Campeonato campeonato,	CampeonatoPontosCorridos campeonatoPC) throws Exception {
		if(campeonato.getIdentificador().equals(campeonatoPC.getIdentificador())) {
			throw new Exception(ERRO_CADASTRO_CAMPEONATO);
		}
	}
}
