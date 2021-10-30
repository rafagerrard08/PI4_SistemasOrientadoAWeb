package com.senac.PI4_ecommerce.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class NovoClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "O preencimento do campo 'nome' é obrigatorio.")
	@Length(min = 5, max = 80, message = "Este campo 'nome' ter entre 5 e 80 caracteres")
	private String nome;
	private String genero;
	@NotEmpty(message = "O preencimento do campo 'email' é obrigatorio.")
	@Email(message = "O email é invalido")
	private String email;
	@NotEmpty(message = "O preencimento do campo 'cpf' é obrigatorio.")
	private String cpf;	
	@NotEmpty(message = "O preencimento do campo 'senha' é obrigatorio.")
	@Length(min = 5, max = 80, message = "Este campo 'nome' ter entre 5 e 80 caracteres")
	private String senha;	
	
	@NotEmpty(message = "O preencimento do campo 'logradouro' é obrigatorio.")
	private String logradouro;
	@NotEmpty(message = "O preencimento do campo 'numero' é obrigatorio.")
	private String numero;
	private String complemento;
	private String bairro;
	@NotEmpty(message = "O preencimento do campo 'cep' é obrigatorio.")
	private String cep;
	@NotEmpty(message = "O preencimento do campo 'cidade' é obrigatorio.")
	private String cidade;
	@NotEmpty(message = "O preencimento do campo 'estado' é obrigatorio.")
	private String Estado;

	public NovoClienteDTO() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}



	

}
