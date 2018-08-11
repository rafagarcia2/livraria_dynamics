package br.com.livraria.bean;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class LivroBean {

	private Livro livro;

	public Livro getLivro() {
		return livro;
	}

	public void gravar() {
		System.out.println("Gravando livro!" + this.livro.getTitulo());
	}

}
