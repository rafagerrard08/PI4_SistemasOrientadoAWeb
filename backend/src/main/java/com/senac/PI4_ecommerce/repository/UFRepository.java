package com.senac.PI4_ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.senac.PI4_ecommerce.model.UF;

@Repository
public interface UFRepository extends JpaRepository<UF, Integer> {
	
	@Query("SELECT DISTINCT u FROM UF AS u WHERE u.nome = :nome")
	public Optional<UF> findByNome(String nome);
}
