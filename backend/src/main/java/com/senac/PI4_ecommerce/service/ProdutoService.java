package com.senac.PI4_ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.senac.PI4_ecommerce.Utils.Converts;
import com.senac.PI4_ecommerce.dto.ProdutoDTO;
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

	public Page<Produto> searchProdutos(String nome, Integer pagina, Integer itensPorPagina, String ordenarPor,
			String direcao) {
		if(pagina<0) {
			// Implementar erro
		}
		PageRequest pr = PageRequest.of(pagina, itensPorPagina, Direction.valueOf(direcao), ordenarPor);
		return produtoRepository.search(nome, pr);
	}

	public ProdutoDTO getProduto(Integer id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		
		if (!produto.isEmpty()) {
			return Converts.toProdutoDTO(produto.get());
		}
		// Implementar erro
		return Converts.toProdutoDTO(produto.get());
	}

	public Produto postProduto(Produto produto) {
		produto.setId(null);
		produto = produtoRepository.save(produto);
		return produto;
	}

	public Produto putProduto(Produto produto) {
		return produtoRepository.save(produto);
	}

	public Categoria getProdutoByCategoria(Integer id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);

		if (!categoria.isEmpty()) {
			return categoria.get();
		}
		// Implementar erro
		return categoria.get();
	}

}
