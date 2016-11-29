package br.com.jefferson.Model;

public interface EntidadeBase<PK, T> {
	
	public void persistir(T entidade);
	
	public void atualizar(T entidade);
	
	public void remover(T entidade);
	
}
