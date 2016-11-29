package br.com.jefferson.Controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.jefferson.Model.TimeCampeonato;
import br.com.jefferson.RN.TimeCampeonatoRN;

@ManagedBean(name="timeCampeonatoController")
@RequestScoped
public class TimeCampeonatoController {
	
	@EJB
	TimeCampeonatoRN timeCampeonatoRN;
	
	public List<TimeCampeonato> buscarClassificacao() {
		return timeCampeonatoRN.buscarClassificacao();
		
	}

}
