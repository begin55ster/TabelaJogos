package br.com.jefferson.DAO;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.jefferson.Model.EntidadeBase;

@Stateless
public class GenericDao<PK, T>  implements EntidadeBase<PK, T>, Serializable {
	
	private static final long serialVersionUID = 1900143150420494066L;
	
	@PersistenceContext(unitName = "jogos")
	protected EntityManager manager;
	
	private Class<?> getTypeClass() {
		Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[1];
		return clazz;
	}
	
	@Transactional
	@Override
	public void persistir(T entidade) {
		manager.persist(entidade);
	}

	@Transactional
	@Override
	public void atualizar(T entidade) {
		manager.merge(entidade);
	}
	
	@Transactional
	@Override
	public void remover(T entidade) {
		manager.remove(entidade);
	}

	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

}
