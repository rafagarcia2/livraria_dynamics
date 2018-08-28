package br.com.livraria.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.livraria.modelo.Usuario;

public class Autorizador implements PhaseListener {

	private static final long serialVersionUID = -6918470115341889294L;

	@Override
	public void afterPhase(PhaseEvent evento) {
		
		FacesContext context = evento.getFacesContext();
		
		String nomePagina = context.getViewRoot().getViewId();
		
		System.out.println(nomePagina);
		
		if (nomePagina.equals("/login.xhtml")){
			return;
		}

		Usuario usuarioLogado = (Usuario) context.getExternalContext()
	            .getSessionMap().get("usuarioLogado");

	    if(usuarioLogado != null) {
	        return;
	    }
	    
	    NavigationHandler handler = context.getApplication().getNavigationHandler();
	    handler.handleNavigation(context, null, "/login?faces-redirect=true");

	    context.renderResponse(); 
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
