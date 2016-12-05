package br.com.jefferson.Model;

public enum TipoCampeonato {
	
	PONTOS_CORRIDO("Pontos Corridos"),
	MATA_MATA("Mata-Mata"),
	COPA("Copa");
	
	private String label;
	
	TipoCampeonato(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
	
	

}
