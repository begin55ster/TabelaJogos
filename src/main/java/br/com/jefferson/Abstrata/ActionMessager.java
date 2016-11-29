package br.com.jefferson.Abstrata;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class ActionMessager {
    
    protected void messagerInformativa(String tipo , String message) {
    	FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(FacesMessage.SEVERITY_INFO, tipo , message));
    }
    
    protected void messagerAlerta(String message) {
    	FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Atenção !", message));
    }
    
    protected void messagerErro(String message) {
    	FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro !", message));
    }
    
    protected void messagerErroFatal(String message) {
    	FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Erro Fatal !", message));
    }

}
