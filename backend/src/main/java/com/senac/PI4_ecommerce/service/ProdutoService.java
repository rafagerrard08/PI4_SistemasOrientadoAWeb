package com.senac.PI4_ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.senac.PI4_ecommerce.model.Produto;
import com.senac.PI4_ecommerce.model.enums.EstadoProduto;
import com.senac.PI4_ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Page<Produto> searchProdutos(String nome, Integer pagina, Integer itensPorPagina, String ordenarPor,
			String direcao) {
		if (pagina < 0) {
			// Implementar erro
		}
		PageRequest pr = PageRequest.of(pagina, itensPorPagina, Direction.valueOf(direcao), ordenarPor);
		return produtoRepository.search(nome, pr);
	}

	public Produto getProduto(Integer id) {
		Optional<Produto> produto = produtoRepository.findById(id);

		if (!produto.isEmpty()) {
			return produto.get();
		}
		// Implementar erro
		return produto.get();
	}

	public Produto postProduto(Produto produto) {
		produto.setId(null);
		produto.setEstado(EstadoProduto.ATIVO);

		return produtoRepository.save(produto);
	}

	public Produto putProduto(Produto produtoAtualizado) {

		Produto produtoAtual = getProduto(produtoAtualizado.getId());
		
		produtoAtualizado = atualizaProduto(produtoAtual, produtoAtualizado);
		return produtoRepository.save(produtoAtualizado);
	}

	public Produto atualizaProduto(Produto produtoAtual, Produto produtoAtualizado) {
		produtoAtual.setNome(produtoAtualizado.getNome());
		produtoAtual.setMarca(produtoAtualizado.getMarca());
		produtoAtual.setQuantidade(produtoAtualizado.getQuantidade());
		produtoAtual.setPreco(produtoAtualizado.getPreco());
		produtoAtual.setDescricao(produtoAtualizado.getDescricao());
		produtoAtual.setEstado(produtoAtualizado.getEstado());
		produtoAtual.setCategoria(produtoAtualizado.getCategoria());
		produtoAtual.getImagens().addAll(produtoAtualizado.getImagens());
		return produtoAtual;
	}
}
