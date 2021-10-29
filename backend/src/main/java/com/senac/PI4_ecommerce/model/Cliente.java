package com.senac.PI4_ecommerce.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.senac.PI4_ecommerce.model.enums.EstadoCadastro;

@Entity
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	@Email
	private String email;
	// @CPF
	private String cpf;
	private String senha;
	private String enderecoFaturamento;
	
	private Integer estado;

	@JsonManagedReference
	@OneToMany(mappedBy = "cliente")
	private List<Endereco> enderecosEntrega = new ArrayList<>();
	
	public Cliente() {
	}

	public Cliente(Integer id, String nome, @Email String email, String cpf, String senha, String enderecoFaturamento, EstadoCadastro estado) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.senha = senha;
		this.enderecoFaturamento = enderecoFaturamento;
		this.estado = estado.getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getEnderecoFaturamento() {
		return enderecoFaturamento;
	}

	public void setEnderecoFaturamento(String enderecoFaturamento) {
		this.enderecoFaturamento = enderecoFaturamento;
	}

	public List<Endereco> getEnderecosEntrega() {
		return enderecosEntrega;
	}

	public void setEnderecosEntrega(List<Endereco> enderecosEntrega) {
		this.enderecosEntrega = enderecosEntrega;
	}

	public EstadoCadastro getEstado() {
		return EstadoCadastro.toEnum(estado);
	}

	public void setEstado(EstadoCadastro estado) {
		this.estado = estado.getId();
	}
	
	
	
	
	
	

}
