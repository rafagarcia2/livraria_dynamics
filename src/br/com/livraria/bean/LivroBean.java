package br.com.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.livraria.dao.DAO;
import br.com.livraria.modelo.Autor;
import br.com.livraria.modelo.Livro;

@ManagedBean
@ViewScoped
public class LivroBean implements Serializable {

	private static final long serialVersionUID = -484534310830408874L;

	private Livro livro = new Livro();
	
	private Integer autorId;

	public Livro getLivro() {
		return livro;
	}
	
	public void setLivro(Livro livro){
		this.livro = livro;
	}

	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			//throw new RuntimeException("Livro deve ter pelo menos um Autor.");
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor."));
		}

		if (this.livro.getId() == null) {
			new DAO<Livro>(Livro.class).adiciona(this.livro);
		} else {
			new DAO<Livro>(Livro.class).atualiza(this.livro);
		}
		
		this.livro = new Livro();
	}
	
	public void editar(Livro livro) {
	    System.out.println("Editando livro " + livro.getTitulo());
	    this.livro = livro;
	}
	
	public void remover(Livro livro) {
	    System.out.println("Removendo livro " + livro.getTitulo());
	    new DAO<Livro>(Livro.class).remove(livro);
	    // this.livros.remove(livro); // removendo da lista
	}
	
	public void removerAutorDoLivro(Autor autor) {
		System.out.println("Nem chegou aqui!");
		this.livro.removeAutor(autor);
	}
	
	public String form() {
		return "autor?faces-redirect=true";
	}
	
	
	
	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}
	
	public List<Livro> getLivros() {
	  return new DAO<Livro>(Livro.class).listaTodos();
	}
	
	public void gravarAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);
	}
	
	public void ComecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		String valor = value.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("ISBN: deveria começar com 1."));
		}
	}
	
	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

}
