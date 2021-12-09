package com.generation.infokids.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_tema")
public class Tema {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "O atributo assunto não pode estar vazio.")
	@Size(max = 155, message="Limite máximo de 155 caracteres" )
	private String assunto;
	
	@NotBlank(message = "O atributo público alvo não pode estar vazio.")
	@Size(max = 155, message="Limite máximo de 155 caracteres" )
	private String publicoAlvo;
	
	@NotBlank(message = "O atributo idioma não pode estar vazio.")
	@Size(max = 155, message="Limite máximo de 155 caracteres" )
	private String idioma;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getPublicoAlvo() {
		return publicoAlvo;
	}

	public void setPublicoAlvo(String publico_alvo) {
		this.publicoAlvo = publico_alvo;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	
	

}
