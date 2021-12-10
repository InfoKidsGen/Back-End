package com.generation.infokids.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Este campo não pode estar vazio.")
	@Size(max = 255, message = "Limite máximo de 255 caracteres.")
	private String nomeCompleto;
	
	@NotBlank(message = "Este campo não pode estar vazio.")
	@Size(max = 255, message = "Limite máximo de 255 caracteres.")
	private String email;
	
	@NotBlank(message = "Este campo não pode estar vazio.")
	@Size(max = 5000, message = "Limite máximo de 5000 caracteres.")
	private String senha;
	
	@NotBlank(message = "Este campo não pode estar vazio.")
	@Size(max = 100, message = "Limite máximo de 100 caracteres.")
	private String perfis;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("usuario")
	private List <Postagem> postagem;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPerfis() {
		return perfis;
	}

	public void setPerfis(String perfis) {
		this.perfis = perfis;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	} 

}