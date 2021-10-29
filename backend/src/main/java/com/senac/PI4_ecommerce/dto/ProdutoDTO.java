package com.senac.PI4_ecommerce.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.senac.PI4_ecommerce.model.Categoria;
import com.senac.PI4_ecommerce.model.enums.EstadoCadastro;

public class ProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotBlank(message = "Nome nao pode estar vazio")
	private String nome;
	@NotBlank(message = "Marca nao pode estar vazio")
	private String marca;
	@NotNull(message = "Quantidade nao pode estar vazio")
	private Integer quantidade;
	@NotNull(message = "Preco nao pode estar vazio")
	@Min(value = 0)
	private BigDecimal preco;
	@NotBlank(message = "Descricao nao pode estar vazio")
	private String descricao;
	
	private Integer estado;
	
	private Double avaliacao;

	private Categoria categoria;
	
	@NotNull(message = "CategoriaId nao pode estar vazio")
	private Integer categoriaId;
	
	private String imagemPrincipal;

	private Set<String> imagens = new HashSet<>();

	public ProdutoDTO() {
		super();
	}

	public ProdutoDTO(Integer id, String nome, String marca, Integer quantidade, Double preco, String descricao,
			EstadoCadastro estado, Integer categoriaId,  String imagemPrincipal, Double avaliacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.marca = marca;
		this.quantidade = quantidade;
		this.preco = BigDecimal.valueOf(preco);
		this.descricao = descricao;
		this.estado = estado.getId();
		this.categoriaId = categoriaId;
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

	public EstadoCadastro getEstado() {
		return EstadoCadastro.toEnum(estado);
	}

	public void setEstado(EstadoCadastro estado) {
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public String getImagemPrincipal() {
		return imagemPrincipal;
	}

	public void setImagemPrincipal(String imagemPrincipal) {
		this.imagemPrincipal = imagemPrincipal;
	}

}
