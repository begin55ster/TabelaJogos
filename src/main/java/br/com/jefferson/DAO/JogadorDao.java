package br.com.jefferson.DAO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.jefferson.Model.Jogador;

public class JogadorDao extends GenericDao<Long ,Jogador> implements Serializable  {

	private static final long serialVersionUID = 5898012785152026575L;
	
		public List<Jogador> listar(){
		    CriteriaBuilder builder = manager.getCriteriaBuilder();
			CriteriaQuery<Jogador> criteria =  builder.createQuery(Jogador.class);
		    criteria.from(Jogador.class);
		    return manager.createQuery(criteria).getResultList();
		}
		
		public Jogador obterPorId(Long jogadorID) {
			Query q = manager.createQuery("select j from Jogador j where j.seq = :seq ");
			q.setParameter("seq", jogadorID);
			Jogador j = (Jogador) q.getSingleResult();
			return j;
		}

}
