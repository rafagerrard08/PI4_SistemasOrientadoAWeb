package com.senac.PI4_ecommerce.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.senac.PI4_ecommerce.controller.utils.Util;
import com.senac.PI4_ecommerce.dto.ProdutoDTO;
import com.senac.PI4_ecommerce.model.Categoria;
import com.senac.PI4_ecommerce.model.Produto;
import com.senac.PI4_ecommerce.service.CategoriaService;
import com.senac.PI4_ecommerce.service.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private CategoriaService categoriaService;

	/***
	 * Listar os produtos armazenados Por padrao em ordem decrescente de id (Produto
	 * mais novo primeiro) Pode receber parametro com "DESC"(padrao) ou "ASC" na
	 * requisicao para alterar a ordem
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Produto>> getProdutosPage(@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@RequestParam(value = "itensPorPagina", defaultValue = "10") Integer itensPorPagina,
			@RequestParam(value = "ordenarPor", defaultValue = "id") String ordenarPor,
			@RequestParam(value = "direcao", defaultValue = "DESC") String direcao) {

		String nomeDecode = Util.decodeParam(nome);

		Page<Produto> produtos = produtoService.searchProdutos(nomeDecode, pagina, itensPorPagina, ordenarPor,
				direcao);

		return ResponseEntity.ok().body(produtos);
	}

	/***
	 * Consultar produto como detalhe
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getProduto(@PathVariable Integer id) {
		Produto produto = produtoService.getProduto(id);
		return ResponseEntity.ok().body(produto);
	}

	/***
	 * Incluir um produto e suas imagens
	 * 
	 * @param produtoDTO
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> postProduto(@RequestBody ProdutoDTO produtoDTO) {
		Categoria categoria = categoriaService.getCategoria(produtoDTO.getCategoriaId());
		produtoDTO.setCategoria(categoria);

		Produto produto = new Produto(produtoDTO);
		produto = produtoService.postProduto(produto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	/***
	 * Alterar um produto e suas imagens e Inativar ou Reativar um produto
	 * 
	 * @param id
	 * @param produtoDTO
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> putProduto(@PathVariable Integer id, @RequestBody ProdutoDTO produtoDTO) {
		produtoDTO.setId(id);

		Categoria categoria = categoriaService.getCategoria(produtoDTO.getCategoriaId());
		produtoDTO.setCategoria(categoria);

		Produto produto = new Produto(produtoDTO);
		produto = produtoService.putProduto(produto);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/categoria/{id}")
	public ResponseEntity<?> getProdutoByCategoria(@PathVariable Integer id) {
		Categoria categoria = produtoService.getProdutoByCategoria(id);
		return ResponseEntity.ok().body(categoria);
	}

}
