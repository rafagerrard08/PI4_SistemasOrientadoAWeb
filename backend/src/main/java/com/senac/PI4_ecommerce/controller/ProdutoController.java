package com.senac.PI4_ecommerce.controller;

import java.io.IOException;
import java.net.URI;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.senac.PI4_ecommerce.Utils.Converts;
import com.senac.PI4_ecommerce.controller.utils.Util;
import com.senac.PI4_ecommerce.dto.ProdutoDTO;
import com.senac.PI4_ecommerce.model.Produto;
import com.senac.PI4_ecommerce.model.enums.EstadoProduto;
import com.senac.PI4_ecommerce.service.CategoriaService;
import com.senac.PI4_ecommerce.service.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private CategoriaService categoriaService;
	
    @RequestMapping(value = "/imagens/{nomeImagem}", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImage(@PathVariable("nomeImagem") String nomeImagem, HttpServletResponse response) throws IOException {

        var imgFile = new ClassPathResource("images/"+nomeImagem);

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
    }

	/***
	 * Listar os produtos armazenados Por padrao em ordem decrescente de id (Produto
	 * mais novo primeiro) Pode receber parametro com "DESC"(padrao) ou "ASC" na
	 * requisicao para alterar a ordem e o estado de produto que, por padrao é
	 * ativo, mas pode ser tambem inativo ou todos
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Produto>> getProdutosPage(@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@RequestParam(value = "itensPorPagina", defaultValue = "9") Integer itensPorPagina,
			@RequestParam(value = "ordenarPor", defaultValue = "id") String ordenarPor,
			@RequestParam(value = "direcao", defaultValue = "DESC") String direcao,
			@RequestParam(value = "estado", defaultValue = "ativo") String status) {

		String nomeDecode = Util.decodeParam(nome);
		EstadoProduto estado = null;
		if (status.equals("ativo")) {
			estado = EstadoProduto.ATIVO;
		} else if (status.equals("inativo")) {
			estado = EstadoProduto.INATIVO;
			System.out.println("caiu no inativo");
		}
		System.out.println(estado.getId());

		Page<Produto> produtos = produtoService.searchProdutos(nomeDecode, pagina, itensPorPagina, ordenarPor, direcao,
				estado);

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

		ProdutoDTO produtoDTO = Converts.toProdutoDTO(produto);

		return ResponseEntity.ok().body(produtoDTO);
	}

	/***
	 * Incluir um produto e suas imagens
	 * 
	 * @param produtoDTO
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String postProduto(@RequestBody @Valid ProdutoDTO produtoDTO) {

		produtoDTO.setCategoria(categoriaService.getCategoria(produtoDTO.getCategoriaId()));
		Produto produto = Converts.toProduto(produtoDTO);
		produto.setCategoria(categoriaService.getCategoria(produtoDTO.getCategoriaId()));

		produto = produtoService.postProduto(produto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId())
				.toUri();

		return "Produto Criado: " + uri.toString();
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
		produtoDTO.setCategoria(categoriaService.getCategoria(produtoDTO.getCategoriaId()));

		System.out.println("ID DTO:" + produtoDTO.getId());

		Produto produto = Converts.toProduto(produtoDTO);

		System.out.println("ID Domain:" + produto.getId());

		produtoService.putProduto(produto);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/uploadImages/{id}", method = RequestMethod.POST)
	public String submit(@RequestParam("files") MultipartFile[] files, @PathVariable Integer id) {

		produtoService.saveImg(files, id);

		return "fileUploadView";
	}

	@RequestMapping(value = "/img/{id}/{nomeImagem}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public void getImage(@PathVariable("nomeImagem") String nomeImagem, @PathVariable("id") String id, HttpServletResponse response)
			throws IOException {

		var imgFile = new ClassPathResource("imagens/" + id + "/" + nomeImagem);

		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
	}
}
