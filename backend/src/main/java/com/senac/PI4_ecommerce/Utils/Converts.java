package com.senac.PI4_ecommerce.Utils;

import org.springframework.beans.factory.annotation.Autowired;

import com.senac.PI4_ecommerce.dto.ProdutoDTO;
import com.senac.PI4_ecommerce.model.Produto;
import com.senac.PI4_ecommerce.service.CategoriaService;

public class Converts {
	
	@Autowired
	private static CategoriaService categoriaService;
	
	
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
		produtoDTO.setImagens(produto.getImagens());
		produtoDTO.setAvaliacao(produto.getAvaliacao());
		
		return produtoDTO;
	}
	
	public static Produto toProduto(ProdutoDTO produtoDTO) {
		Produto produto = new Produto();
		
		produto.setNome(produtoDTO.getNome());
		produto.setMarca(produtoDTO.getMarca());
		produto.setQuantidade(produtoDTO.getQuantidade());
		produto.setPreco(produtoDTO.getPreco());
		produto.setDescricao(produtoDTO.getDescricao());
		produto.setEstado(produtoDTO.getEstado());
		produto.setCategoria(categoriaService.getCategoria(produtoDTO.getCategoriaId()));
		produto.setImagens(produtoDTO.getImagens());
		produto.setAvaliacao(produtoDTO.getAvaliacao());
		
		return produto;
	}
	
}
