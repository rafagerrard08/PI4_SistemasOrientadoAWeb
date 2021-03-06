package com.senac.PI4_ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senac.PI4_ecommerce.model.Cidade;
import com.senac.PI4_ecommerce.model.UF;
import com.senac.PI4_ecommerce.repository.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	public Optional<Cidade> getCidade(String nome, UF estado) {
		Optional<Cidade> cidade = cidadeRepository.find(nome, estado);
		
		return cidade;
	}
	
	
	public Cidade save(String nome, UF estado) {
		
		Cidade cidade = new Cidade(null, nome, estado);

		return cidadeRepository.save(cidade);
	}
	

}

