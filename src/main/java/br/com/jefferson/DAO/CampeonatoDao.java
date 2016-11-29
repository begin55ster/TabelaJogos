package br.com.jefferson.DAO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.jefferson.Model.Campeonato;
import br.com.jefferson.Model.CampeonatoPontosCorridos;

public class CampeonatoDao extends GenericDao<Long ,Campeonato> implements Serializable {

	private static final long serialVersionUID = -192758307923028947L;
	
	public List<CampeonatoPontosCorridos> listar(){
	    CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<CampeonatoPontosCorridos> criteria =  builder.createQuery(CampeonatoPontosCorridos.class);
	    criteria.from(CampeonatoPontosCorridos.class);
	    return manager.createQuery(criteria).getResultList();
	}
	
}
