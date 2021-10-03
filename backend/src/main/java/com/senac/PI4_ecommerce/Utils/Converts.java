package com.senac.PI4_ecommerce.Utils;

import com.senac.PI4_ecommerce.dto.ProdutoDTO;
import com.senac.PI4_ecommerce.model.Produto;
import com.senac.PI4_ecommerce.model.enums.EstadoProduto;

public class Converts {

	public static ProdutoDTO toProdutoDTO(Produto produto) {
		ProdutoDTO produtoDTO = new ProdutoDTO();

		produtoDTO.setId(produto.getId());
		produtoDTO.setNome(produto.getNome());
		produtoDTO.setMarca(produto.getMarca());
		produtoDTO.setQuantidade(produto.getQuantidade());
		produtoDTO.setPreco(produto.getPreco());
		produtoDTO.setDescricao(produto.getDescricao());
		produtoDTO.setEstado(produto.getEstado());
		produtoDTO.setCategoriaId(produto.getCategoria().getId());
		produtoDTO.setCategoria(produto.getCategoria());
		produtoDTO.getImagens().addAll(produto.getImagens());
		produtoDTO.setAvaliacao(produto.getAvaliacao());
		produtoDTO.setImagemPrincipal(produto.getImagemPrincipal());

		return produtoDTO;
	}

	public static Produto toProduto(ProdutoDTO produtoDTO) {
		Produto produto = new Produto();

		produto.setId(produtoDTO.getId());
		produto.setNome(produtoDTO.getNome());
		produto.setMarca(produtoDTO.getMarca());
		produto.setQuantidade(produtoDTO.getQuantidade());
		produto.setPreco(produtoDTO.getPreco());
		produto.setDescricao(produtoDTO.getDescricao());
		if (produtoDTO.getEstado() == null) {
			produto.setEstado(EstadoProduto.ATIVO);
		} else {
			produto.setEstado(produtoDTO.getEstado());
		}
		produto.setCategoria(produtoDTO.getCategoria());
		produto.getImagens().addAll(produto.getImagens());
		produto.setAvaliacao(produtoDTO.getAvaliacao());
		produto.setImagemPrincipal(produtoDTO.getImagemPrincipal());

		return produto;
	}

}
