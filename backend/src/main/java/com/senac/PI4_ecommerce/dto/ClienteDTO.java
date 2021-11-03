package com.senac.PI4_ecommerce.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.senac.PI4_ecommerce.model.Endereco;
import com.senac.PI4_ecommerce.model.enums.EstadoCadastro;

public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotEmpty(message = "O preencimento do campo 'nomeCompleto' é obrigatorio.")
	@Length(min = 6, max = 80, message = "Este campo 'nomeCompleto' ter entre 6 e 80 caracteres")
	private String nomeCompleto;
	@NotEmpty(message = "O preencimento do campo 'dataNascimento' é obrigatorio.")
	private Date dataNascimento;
	@NotEmpty(message = "O preencimento do campo 'dataNascimento' é obrigatorio.")
	private String genero;

	public ClienteDTO() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

}
