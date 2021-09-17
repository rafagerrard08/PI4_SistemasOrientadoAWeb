package com.senac.PI4_ecommerce.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.senac.PI4_ecommerce.model.enums.EstadoProduto;

public class ProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String marca;
	private Integer quantidade;
	private BigDecimal preco;
	private String descricao;
	private Integer estado;
	private Double avaliacao;


	private Integer categoriaId;

	private Set<String> imagens = new HashSet<>();

	public ProdutoDTO() {
		super();
	}

	public ProdutoDTO(Integer id, String nome, String marca, Integer quantidade, Double preco, String descricao,
			EstadoProduto estado, Integer categoriaId, Set<String> imagens, Double avaliacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.marca = marca;
		this.quantidade = quantidade;
		this.preco = BigDecimal.valueOf(preco);
		this.descricao = descricao;
		this.estado = estado.getId();
		this.categoriaId = categoriaId;
		this.imagens = imagens;
		this.avaliacao = avaliacao;
	}
	
	// Getters e Setters
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

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public EstadoProduto getEstado() {
		return EstadoProduto.toEnum(estado);
	}

	public void setEstado(EstadoProduto estado) {
		this.estado = estado.getId();
	}

	public Set<String> getImagens() {
		return imagens;
	}

	public void setImagens(Set<String> imagens) {
		this.imagens = imagens;
	}

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer catgoriaId) {
		this.categoriaId = catgoriaId;
	}

	public Double getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Double avaliacao) {
		this.avaliacao = avaliacao;
	}

}
