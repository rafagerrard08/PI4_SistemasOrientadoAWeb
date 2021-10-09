package com.senac.PI4_ecommerce.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.senac.PI4_ecommerce.Utils.Converts;
import com.senac.PI4_ecommerce.controller.utils.Util;
import com.senac.PI4_ecommerce.dto.ProdutoDTO;
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

	@Value("${ecommerce.dir.raiz}")
	String raiz = null;

	@Value("${ecommerce.dir.diretorio-imagens}")
	String dirImagens = null;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Produto>> getProdutosPage(@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@RequestParam(value = "itensPorPagina", defaultValue = "9") Integer itensPorPagina,
			@RequestParam(value = "ordenarPor", defaultValue = "id") String ordenarPor,
			@RequestParam(value = "direcao", defaultValue = "DESC") String direcao,
			@RequestParam(value = "estado", defaultValue = "ativo") String estado) {

		String nomeDecode = Util.decodeParam(nome);

		Page<Produto> produtos = produtoService.searchProdutos(nomeDecode, pagina, itensPorPagina, ordenarPor, direcao,
				estado);

		return ResponseEntity.ok().body(produtos);
	}

	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getProduto(@PathVariable Integer id) {
		Produto produto = produtoService.getProduto(id);

		ProdutoDTO produtoDTO = Converts.toProdutoDTO(produto);

		return ResponseEntity.ok().body(produtoDTO);
	}

	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> postProdutoData(@RequestBody @Valid ProdutoDTO produtoDTO) {

		produtoDTO.setCategoria(categoriaService.getCategoria(produtoDTO.getCategoriaId()));
		
		Produto produto = Converts.toProduto(produtoDTO);
		
		produto.setCategoria(categoriaService.getCategoria(produtoDTO.getCategoriaId()));
		produto = produtoService.postProduto(produto);


		return ResponseEntity.ok().body(produto);
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
//		produtoDTO.setCategoria(categoriaService.getCategoria(produtoDTO.getCategoriaId()));

		System.out.println("ID DTO:" + produtoDTO.getId());

		Produto produto = Converts.toProduto(produtoDTO);

		System.out.println("ID Domain:" + produto.getId());

		produtoService.putProduto(produto);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/uploadImages/{id}", method = RequestMethod.POST)
	public String submit(@RequestParam("file") MultipartFile file, @PathVariable Integer id) {
		System.out.println(id);

		produtoService.saveImg(file, id);

		return "fileUploadView";
	}

	@RequestMapping(value = "/resetImages/{id}", method = RequestMethod.DELETE)
	public void resetImages(@PathVariable Integer id) {
		File folder = new File(this.raiz + this.dirImagens + id);
		if (folder.isDirectory()) {
			File[] sun = folder.listFiles();
			for (File toDelete : sun) {
				toDelete.delete();
			}
		}
	}

	@RequestMapping(value = "/img/{id}/{nomeImagem}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public void getImage(@PathVariable("nomeImagem") String nomeImagem, @PathVariable("id") String id,
			HttpServletResponse response) throws IOException {
		ClassPathResource imgFile = null;

		try {
			imgFile = new ClassPathResource("/imagens/" + id + "/" + nomeImagem);
		} catch (Exception e) {
			imgFile = new ClassPathResource("imagens/" + id + "/" + nomeImagem);
		}

		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
	}

	@RequestMapping(value = "/imagens/{nomeImagem}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public void getImage(@PathVariable("nomeImagem") String nomeImagem, HttpServletResponse response)
			throws IOException {

		var imgFile = new ClassPathResource("images/" + nomeImagem);

		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
	}
}
