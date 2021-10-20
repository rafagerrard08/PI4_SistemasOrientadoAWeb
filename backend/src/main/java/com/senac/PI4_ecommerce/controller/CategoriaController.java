package com.senac.PI4_ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.senac.PI4_ecommerce.model.Categoria;
import com.senac.PI4_ecommerce.service.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCategoria(@PathVariable Integer id) {
		
		Categoria cat = categoriaService.getCategoria(id);
		
		return ResponseEntity.ok().body(cat);
	}
	
	@RequestMapping (method = RequestMethod.GET)
	public ResponseEntity<?> getCategorias() {
		
		List<Categoria> categorias = categoriaService.getCategorias();
		
		return ResponseEntity.ok().body(categorias);
	}
	

}
