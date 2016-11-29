package br.com.jefferson.DAO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.jefferson.Model.TimeCampeonato;

public class TimeCampeonatoDao extends GenericDao<Long ,TimeCampeonato> implements Serializable {

	private static final long serialVersionUID = 8626779692695781876L;
	
	public void clear() {
		manager.clear();
	}
	
	public List<TimeCampeonato> listar(){
	    CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<TimeCampeonato> criteria =  builder.createQuery(TimeCampeonato.class);
	    criteria.from(TimeCampeonato.class);
	    return manager.createQuery(criteria).getResultList();
	}
	
	public TimeCampeonato obterPorId(Long timeCampeonatoID) {
		Query q = manager.createQuery("select tc from TimeCampeonato tc where tc.seq = :seq ");
		q.setParameter("seq", timeCampeonatoID);
		TimeCampeonato tc = (TimeCampeonato) q.getSingleResult();
		return tc;
	}

}
