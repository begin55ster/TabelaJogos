package br.com.jefferson.Exception;

public class CadastroInvalidoException extends Exception {
	
	private static final long serialVersionUID = -2977395007650832653L;

	public CadastroInvalidoException() {
		super("Cadastro Inválido. Verifique se o Time ou Jogador já estão cadastrados . ");
	}

}
