package com.senac.PI4_ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senac.PI4_ecommerce.model.Estado;
import com.senac.PI4_ecommerce.model.Produto;
import com.senac.PI4_ecommerce.model.enums.EstadoCadastro;
import com.senac.PI4_ecommerce.repository.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	public Optional<Estado> getEstado(String nome) {
		Optional<Estado> estado = estadoRepository.findByNome(nome);
		
		return estado;
	}
	
	
	public Estado save(String nome) {
		
		Estado estado = new Estado(null, nome);

		return estadoRepository.save(estado);
	}
	

}

