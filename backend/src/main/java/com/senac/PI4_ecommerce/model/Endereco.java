package com.senac.PI4_ecommerce.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.senac.PI4_ecommerce.dto.EnderecoDTO;
import com.senac.PI4_ecommerce.model.enums.TipoEndereco;

@Entity
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer tipo;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	private Boolean padrao;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;

	public Endereco() {
		super();
	}

	public Endereco(Integer id, TipoEndereco tipo, String logradouro, String numero, String complemento, String bairro, String cep, Cidade cidade, Cliente cliente, Boolean padrao) {
		super();
		this.id = id;
		this.tipo=tipo.getId();
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.setCidade(cidade);
		this.cliente = cliente;
		this.setPadrao(padrao);
	}
	
	public Endereco(EnderecoDTO enderecoDTO, Cidade cidade, Cliente cliente) {
		this.id = enderecoDTO.getId();
		this.tipo=enderecoDTO.getTipo().getId();
		this.logradouro = enderecoDTO.getLogradouro();
		this.numero = enderecoDTO.getNumero();
		this.complemento = enderecoDTO.getComplemento();
		this.bairro = enderecoDTO.getBairro();
		this.cep = enderecoDTO.getCep();
		this.setCidade(cidade);
		this.cliente = cliente;
		this.padrao=enderecoDTO.getPadrao();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public TipoEndereco getTipo() {
		return TipoEndereco.toEnum(tipo);
	}

	public void setTipo(TipoEndereco tipo) {
		this.tipo = tipo.getId();
	}

	public Boolean getPadrao() {
		return padrao;
	}

	public void setPadrao(Boolean padrao) {
		this.padrao = padrao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		return Objects.equals(id, other.id);
	}

}
