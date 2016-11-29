package br.com.jefferson.DAO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.jefferson.Model.Jogo;

public class JogoDao extends GenericDao<Long ,Jogo> implements Serializable {

	private static final long serialVersionUID = -2506632001195997166L;
	  
		public List<Jogo> listar(){
		    CriteriaBuilder builder = manager.getCriteriaBuilder();
			CriteriaQuery<Jogo> criteria =  builder.createQuery(Jogo.class);
		    criteria.from(Jogo.class);
		    return manager.createQuery(criteria).getResultList();
		}
	  
		public Jogo obterPorId(Long jogoID) {
			Query q = manager.createQuery("select j from Jogo j where j.seq = :seq ");
			q.setParameter("seq", jogoID);
			Jogo j = (Jogo) q.getSingleResult();
			return j;
		}
	
}
