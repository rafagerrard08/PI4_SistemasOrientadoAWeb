package com.senac.PI4_ecommerce.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.senac.PI4_ecommerce.model.enums.EstadoProduto;

@Entity
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(columnDefinition = "VARCHAR(200)")
	private String nome;
	@Column(columnDefinition = "VARCHAR(200)")
	private String marca;
	private Integer quantidade;
	private BigDecimal preco;
	@Column(columnDefinition = "VARCHAR(2000)")
	private String descricao;
	private Integer estado;
	private Double avaliacao;

	// Associaçoes
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	@ElementCollection
	@CollectionTable(name = "imagem")
	private Set<String> imagensLocation = new HashSet<>();

	// Construtores (Nao Incluir coleções)
	public Produto() {
		super();
	}

	public Produto(Integer id, String nome, String marca, Integer quantidade, Double preco, String descricao,
			EstadoProduto estado, Categoria categoria, Set<String> imagens, Double avaliacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.marca = marca;
		this.quantidade = quantidade;
		this.preco = BigDecimal.valueOf(preco);
		this.descricao = descricao;
		this.estado = estado.getId();
		this.categoria = categoria;
		this.imagensLocation = imagens;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Set<String> getImagens() {
		return imagensLocation;
	}

	public void setImagens(Set<String> imagens) {
		this.imagensLocation = imagens;
	}

	public Double getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Double avaliacao) {
		this.avaliacao = avaliacao;
	}

	// Hash Code e Equals Padrao (somente Id)
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
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}

}
