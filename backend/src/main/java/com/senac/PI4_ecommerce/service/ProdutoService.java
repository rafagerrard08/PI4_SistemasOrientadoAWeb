package com.senac.PI4_ecommerce.service;

import java.io.File;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${ecommerce.dir.raiz}")
	String raiz = null;

	@Value("${ecommerce.dir.diretorio-imagens}")
	String dirImagens = null;

	public Page<Produto> searchProdutos(String nome, Integer pagina, Integer itensPorPagina, String ordenarPor,
			String direcao, String estado) {
		System.out.println("Iniciou Service");

		if (pagina < 0) {
			// Implementar erro
		}
		
		PageRequest pr = PageRequest.of(pagina, itensPorPagina, Direction.valueOf(direcao), ordenarPor);
		Page<Produto> produtos = null;

		if (estado.equals("ativo")) {
			produtos = produtoRepository.search(nome, pr, EstadoProduto.ATIVO.getId());
		} else if (estado.equals("inativo")) {
			produtos = produtoRepository.search(nome, pr, EstadoProduto.INATIVO.getId());
		} else {
			produtos = produtoRepository.searchAll(nome, pr);
		}
		
		return produtos;
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

	public void saveImg(MultipartFile arquivo, Integer idProduto) {
		Produto produto = this.getProduto(idProduto);
		
		SaveFile sf = new SaveFile();

		File diretorio = new File(this.raiz + this.dirImagens + idProduto + "/principal.jpg");
		
		if(!diretorio.exists()) {
			sf.salvarImg(arquivo, idProduto, "principal.jpg");
			produto.setImagemPrincipal("/produtos/img/" + idProduto + "/principal.jpg");
		} else {
			sf.salvarImg(arquivo, idProduto, arquivo.getOriginalFilename());
			produto.getImagens().add("/produtos/img/" + idProduto + "/" + arquivo.getOriginalFilename());
		}

		produtoRepository.save(produto);
	}
}
