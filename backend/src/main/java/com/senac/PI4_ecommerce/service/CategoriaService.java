package com.senac.PI4_ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senac.PI4_ecommerce.model.Categoria;
import com.senac.PI4_ecommerce.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria getCategoria(Integer id) {
		System.out.println("Entrou em getCategoria id: " + id);
		Optional<Categoria> categoria = categoriaRepository.getCategoria(id);
		if(!categoria.isEmpty()) {
			return categoria.get();

		}
		// Implementar erro
		return categoria.get();
	}

	public List<Categoria> getCategorias() {
		List<Categoria> categorias = categoriaRepository.findAll();
		
			// Implementar erro
		return categorias;
	}

}

