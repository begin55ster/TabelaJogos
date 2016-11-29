package br.com.jefferson.DAO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.jefferson.Model.Time;

public class TimeDao extends GenericDao<Long ,Time> implements Serializable {

	private static final long serialVersionUID = 2513900319095191824L;
	
	public void clear() {
		manager.clear();
	}
	
	public Time recuperarTimePorNomeTime(String timeA) {
		Query q = manager.createQuery("select t from Time t where t.nomeTime = :nomeTime ");
		q.setParameter("nomeTime", timeA);
		return (Time) q.getSingleResult();
	}
	
	public List<Time> listar(){
	    CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Time> criteria =  builder.createQuery(Time.class);
	    criteria.from(Time.class);
	    return manager.createQuery(criteria).getResultList();
	}

	
	public Time obterPorId(Long timeID) {
		Query q = manager.createQuery("select t from Time t where t.seq = :seq ");
		q.setParameter("seq", timeID);
		Time t = (Time) q.getSingleResult();
		return t;
	}


}
