package com.senac.PI4_ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senac.PI4_ecommerce.model.Categoria;
import com.senac.PI4_ecommerce.model.Produto;
import com.senac.PI4_ecommerce.repository.CategoriaRepository;
import com.senac.PI4_ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	
	public List<Produto> getAllProdutos(){
		List<Produto> produtos = produtoRepository.findAll();
		if(!produtos.isEmpty()) {
			return produtos;
		}
		// Implementar erro
		return produtos;
	}
	
	public Produto getProduto(Integer id){
		Optional<Produto> produto = produtoRepository.findById(id);
		if(!produto.isEmpty()) {
			return produto.get();
		} 
		// Implementar erro
		return produto.get();
	}
	
	public Produto postProduto(Produto produto) {
		produto.setId(null);
		produto = produtoRepository.save(produto);
		return produto;
	}
	
	public Produto putProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public Categoria getProdutoByCategoria(Integer id){
		Optional<Categoria> categoria = categoriaRepository.findById(id);

		if(!categoria.isEmpty()) {
			return categoria.get();
		} 
		// Implementar erro
		return categoria.get();	}
	
}
