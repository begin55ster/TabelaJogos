package br.com.jefferson.DAO;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.com.jefferson.Model.EntidadeBase;

@Stateless
public class GenericDao<PK, T>  implements EntidadeBase<PK, T>, Serializable {
	
	private static final long serialVersionUID = 1900143150420494066L;
	
	@PersistenceContext(unitName = "jogos")
	protected EntityManager manager;
	
	@SuppressWarnings("unused")
	private Class<?> getTypeClass() {
		Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[1];
		return clazz;
	}
	
	private Class<T> entityClass;
	
	public GenericDao(Class<T> entity) {
		this.entityClass = entity;
	}
	
	public GenericDao() {
		
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
	
	
	public TypedQuery<T> createNamedQuery(String query) {
		return manager.createNamedQuery(query, entityClass);
	}
	
	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

}
