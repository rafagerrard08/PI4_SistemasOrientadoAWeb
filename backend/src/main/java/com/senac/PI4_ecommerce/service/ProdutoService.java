package com.senac.PI4_ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.senac.PI4_ecommerce.Utils.Converts;
import com.senac.PI4_ecommerce.dto.ProdutoDTO;
import com.senac.PI4_ecommerce.model.Produto;
import com.senac.PI4_ecommerce.model.enums.EstadoProduto;
import com.senac.PI4_ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CategoriaService categoriaService;

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

	public Produto postProduto(ProdutoDTO produtoDTO) {
		produtoDTO.setId(null);
		produtoDTO.setEstado(EstadoProduto.ATIVO);
		produtoDTO.setCategoria(categoriaService.getCategoria(produtoDTO.getCategoriaId()));
				
		Produto produto = Converts.toProduto(produtoDTO);
		
		return produtoRepository.save(produto);
	}

	public Produto putProduto(ProdutoDTO produtoDTO) {
		produtoDTO.setCategoria(categoriaService.getCategoria(produtoDTO.getCategoriaId()));
		Produto novoProduto = Converts.toProduto(produtoDTO);
		
		Produto produto = Converts.toProduto(getProduto(produtoDTO.getId()));
		
		novoProduto = atualizaProduto(novoProduto, produto);
		System.out.println("IDs: " + novoProduto.getId() + produto.getId());
		return produtoRepository.save(novoProduto);
	}
	
	public Produto atualizaProduto(Produto produto, Produto novoProduto) {
		novoProduto.setId(produto.getId());
		novoProduto.setNome(produto.getNome());
		novoProduto.setMarca(produto.getMarca());
		novoProduto.setQuantidade(produto.getQuantidade());
		novoProduto.setPreco(produto.getPreco());
		novoProduto.setDescricao(produto.getDescricao());
		novoProduto.setEstado(produto.getEstado());
		novoProduto.setCategoria(produto.getCategoria());
		novoProduto.getImagens().addAll(produto.getImagens());
		return novoProduto;
	}
}
