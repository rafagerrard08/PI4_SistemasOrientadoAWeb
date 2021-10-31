package com.senac.PI4_ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senac.PI4_ecommerce.model.UF;
import com.senac.PI4_ecommerce.model.Produto;
import com.senac.PI4_ecommerce.model.enums.EstadoCadastro;
import com.senac.PI4_ecommerce.repository.UFRepository;

@Service
public class UFService {

	@Autowired
	private UFRepository ufRepository;
	
	public Optional<UF> getEstado(String nome) {
		Optional<UF> estado = ufRepository.findByNome(nome);
		
		return estado;
	}
	
	
	public UF save(String nome) {
		
		UF estado = new UF(null, nome);

		return ufRepository.save(estado);
	}
	

}

	