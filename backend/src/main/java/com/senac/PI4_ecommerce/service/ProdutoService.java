package com.senac.PI4_ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.senac.PI4_ecommerce.Utils.SaveFile;
import com.senac.PI4_ecommerce.model.Produto;
import com.senac.PI4_ecommerce.model.enums.EstadoProduto;
import com.senac.PI4_ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Page<Produto> searchProdutos(String nome, Integer pagina, Integer itensPorPagina, String ordenarPor,
			String direcao, EstadoProduto estado) {
		if (pagina < 0) {
			// Implementar erro
		}
		PageRequest pr = PageRequest.of(pagina, itensPorPagina, Direction.valueOf(direcao), ordenarPor);
		System.out.println(estado.getId());

		return produtoRepository.search(nome, pr, estado.getId());
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

	public void saveImg(MultipartFile[] arquivos, Integer idProduto) {
		Produto produto = this.getProduto(idProduto);
		
		SaveFile sf = new SaveFile();
		Boolean principal = true;
		Integer count = 1;

		for (MultipartFile arquivo : arquivos) {
			if(principal.equals(true)) {
				sf.salvarImg(arquivo, idProduto, "principal.jpg");
				produto.setImagemPrincipal("/produtos/img/" + idProduto + "/principal.jpg");
				principal = false;
			} else {
				sf.salvarImg(arquivo, idProduto, "img" + count + ".jpg");
				produto.getImagens().add("/produtos/img/" + idProduto + "/" + "img" + count + ".jpg");
				count++;
			}
		}
		
		
		produtoRepository.save(produto);
	}
}
