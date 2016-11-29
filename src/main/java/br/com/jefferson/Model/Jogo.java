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
@Table(name = "JOGO")
public class Jogo implements Serializable {

	private static final long serialVersionUID = 3600702127451489440L;
	
	private Long seq;
	private Campeonato campeonato;
	private Integer rodada;
	private String timeA;
	private String timeB;
	private Integer golsTimeA;
	private Integer golsTimeB;
	
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

	@Column(name = "RODADA", length = 2)
	public Integer getRodada() {
		return rodada;
	}
	
	public void setRodada(Integer rodada) {
		this.rodada = rodada;
	}
	
	@Column(name = "TIME_A")
	public String getTimeA() {
		return timeA;
	}
	
	public void setTimeA(String timeA) {
		this.timeA = timeA;
	}
	
	@Column(name = "TIME_B")
	public String getTimeB() {
		return timeB;
	}
	
	public void setTimeB(String timeB) {
		this.timeB = timeB;
	}

	@Column(name = "GOL_TIME_A", length = 2)
	public Integer getGolsTimeA() {
		return golsTimeA;
	}
	
	public void setGolsTimeA(Integer golsTimeA) {
		this.golsTimeA = golsTimeA;
	}
	
	@Column(name = "GOL_TIME_B", length = 2)
	public Integer getGolsTimeB() {
		return golsTimeB;
	}
	
	public void setGolsTimeB(Integer golsTimeB) {
		this.golsTimeB = golsTimeB;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rodada == null) ? 0 : rodada.hashCode());
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
		if (!(obj instanceof Jogo)) {
			return false;
		}
		Jogo other = (Jogo) obj;
		if (rodada == null) {
			if (other.rodada != null) {
				return false;
			}
		} else if (!rodada.equals(other.rodada)) {
			return false;
		}
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
