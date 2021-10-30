package com.senac.PI4_ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.senac.PI4_ecommerce.model.Cidade;
import com.senac.PI4_ecommerce.model.Estado;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
	
	@Query("SELECT DISTINCT c FROM Cidade AS c WHERE c.nome = :nome AND c.estado = :estado")
	public Optional<Cidade> find(String nome, Estado estado);
	

}
