package com.senac.PI4_ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.senac.PI4_ecommerce.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
	
	@Query("SELECT DISTINCT e FROM Estado AS e WHERE e.nome = :nome")
	public Optional<Estado> findByNome(String nome);
}
