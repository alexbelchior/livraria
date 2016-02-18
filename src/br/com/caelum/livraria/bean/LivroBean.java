package br.com.caelum.livraria.bean;

import br.com.caelum.livraria.modelo.*;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.caelum.livraria.dao.DAO;

@ManagedBean
@ViewScoped
public class LivroBean {

	private Livro livro = new Livro();
	private Integer autorId;
	private Autor autor;
	
	
	public Integer getAutorId() {
		return autorId;
	}
	
	public void setAutorId(Integer autorId){
		this.autorId = autorId;
	}

	public Livro getLivro() {
		return livro;
	}
	
	public void gravaAutor(){
		
		autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);
		System.out.println("Livro escrito por: " + autor.getNome());
	}
	
	public List<Autor>getAutoresDoLivro(){
		return this.livro.getAutores();
	}
	
	public List<Autor> getAutores(){
		
		return new DAO<Autor>(Autor.class).listaTodos();
		
	}

	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			throw new RuntimeException("Livro deve ter pelo menos um Autor.");
		}

		new DAO<Livro>(Livro.class).adiciona(this.livro);
	}

}
