package com.senac.PI4_ecommerce.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class NovoClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotEmpty(message = "O preencimento do campo 'nomeCompleto' é obrigatorio.")
	@Length(min = 5, max = 80, message = "Este campo 'nomeCompleto' ter entre 5 e 80 caracteres")
	private String nomeCompleto;
	private String genero;
	@NotEmpty(message = "O preencimento do campo 'email' é obrigatorio.")
	@Email(message = "O email é invalido")
	private String email;
	@NotEmpty(message = "O preencimento do campo 'cpf' é obrigatorio.")
	private String cpf;	
	// deixar sem validacao aqui, no atualizar da tela a senha é opcional
	private String senha;	
	
	private Date dataNascimento;
	
	private Set<EnderecoDTO> enderecos = new HashSet<>();

	public NovoClienteDTO() {
		super();
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	
	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Set<EnderecoDTO> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Set<EnderecoDTO> enderecos) {
		this.enderecos = enderecos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	

}
