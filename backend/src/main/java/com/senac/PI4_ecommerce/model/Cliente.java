package com.senac.PI4_ecommerce.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.senac.PI4_ecommerce.model.enums.EstadoCadastro;

@Entity
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nomeCompleto;
	@Email
	private String email;
	// @CPF
	private String cpf;
	private String senha;
	
	private Integer estado;
	private String genero;
	private Date dataNascimento;


	@JsonManagedReference
	@OneToMany(mappedBy = "cliente")
	private List<Endereco> enderecos = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="cliente")
	private List<Pedido> pedidos = new ArrayList<>();
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Cliente() {
	}

	public Cliente(Integer id, String nomeCompleto, @Email String email, String cpf, String senha, EstadoCadastro estado, String genero, Date dataNascimento) {
		super();
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.cpf = cpf;
		this.senha = senha;
		this.estado = estado.getId();
		this.genero = genero;
		this.dataNascimento = dataNascimento;
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

//	public EnderecoCobranca getEnderecoCobranca() {
//		return enderecoCobranca;
//	}
//
//	public void setEnderecoCobranca(EnderecoCobranca enderecoCobranca) {
//		this.enderecoCobranca = enderecoCobranca;
//	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecosEntrega) {
		this.enderecos = enderecosEntrega;
	}

	public EstadoCadastro getEstado() {
		return EstadoCadastro.toEnum(estado);
	}

	public void setEstado(EstadoCadastro estado) {
		this.estado = estado.getId();
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

//	@Override
//	public String toString() {
//		return "Cliente [id=" + id + ", nomeCompleto=" + nomeCompleto + ", email=" + email + ", cpf=" + cpf + ", senha=" + senha
//				+ ", estado=" + estado + ", enderecoCobranca=" + enderecoCobranca + ", enderecosEntrega="
//				+ enderecosEntrega + "]";
//	}
	
	
	
	
	
	

}
