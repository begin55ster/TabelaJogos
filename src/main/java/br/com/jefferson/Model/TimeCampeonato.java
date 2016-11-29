package br.com.jefferson.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TIME_CAMPEONATO")
public class TimeCampeonato implements Serializable {

	private static final long serialVersionUID = -5133366043170647967L;
	
	private Long seq;
	private Campeonato campeonato;
	private Integer pontos;
	private Time time;
	private Jogo jogo;
	private Integer vitorias;
	private Integer derrotas;
	private Integer empates;
	private Integer golsPro;
	private Integer golsContra;
	private Integer saldoGols;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "CODIGO", nullable = false)
	public Long getSeq() {
		return seq;
	}
	
	public void setSeq(Long seq) {
		this.seq = seq;
	}

	@OneToOne
	@JoinColumn(name="CAMPEONATO_SEQ")
	public Campeonato getCampeonato() {
		return campeonato;
	}
	
	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}
	
	@OneToOne
	@JoinColumn(name="TIME_SEQ")
	public Time getTime() {
		return time;
	}
	
	public void setTime(Time time) {
		this.time = time;
	}
	
	@OneToOne
	@JoinColumn(name="JOGO_SEQ")
	public Jogo getJogo() {
		return jogo;
	}
	
	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}
	
	@Column(name = "VITORIAS", length = 2)
	public Integer getVitorias() {
		return vitorias;
	}
	
	public void setVitorias(Integer vitorias) {
		this.vitorias = vitorias;
	}
	
	@Column(name = "DERROTAS", length = 2)
	public Integer getDerrotas() {
		return derrotas;
	}
	
	public void setDerrotas(Integer derrotas) {
		this.derrotas = derrotas;
	}
	
	@Column(name = "EMPATES", length = 2)
	public Integer getEmpates() {
		return empates;
	}
	
	public void setEmpates(Integer empates) {
		this.empates = empates;
	}
	
	@Column(name = "GOLS_PRO", length = 3)
	public Integer getGolsPro() {
		return golsPro;
	}
	
	public void setGolsPro(Integer golsPro) {
		this.golsPro = golsPro;
	}
	
	@Column(name = "GOLS_CONTRA", length = 3)
	public Integer getGolsContra() {
		return golsContra;
	}
	
	public void setGolsContra(Integer golsContra) {
		this.golsContra = golsContra;
	}
	
	@Column(name = "PONTOS", length = 3)
	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}
	
	@Column(name = "SALDO_GOLS", length = 3)
	public Integer getSaldoGols() {
		return saldoGols;
	}

	public void setSaldoGols(Integer saldoGols) {
		this.saldoGols = saldoGols;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((seq == null) ? 0 : seq.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof TimeCampeonato)) {
			return false;
		}
		TimeCampeonato other = (TimeCampeonato) obj;
		if (seq == null) {
			if (other.seq != null) {
				return false;
			}
		} else if (!seq.equals(other.seq)) {
			return false;
		}
		return true;
	}

}
