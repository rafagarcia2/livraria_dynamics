package br.com.livraria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.livraria.dao.UsuarioDAO;
import br.com.livraria.modelo.Usuario;

@ManagedBean
@ViewScoped
public class LoginBean {

	private Usuario usuario = new Usuario();

	public String efetuarLogin() {
		System.out.println("Logando com usuário " + this.getUsuario().getEmail());
		
		boolean existe = new UsuarioDAO().existe(this.usuario);
		
		if (existe) {
			return "index?faces-redirect=true";			
		}
		
		return null;
	}

	public Usuario getUsuario() {
		return usuario;
	}
}
