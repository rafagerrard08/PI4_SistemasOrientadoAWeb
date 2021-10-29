package com.senac.PI4_ecommerce.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.senac.PI4_ecommerce.dto.ClienteDTO;
import com.senac.PI4_ecommerce.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	@Query("SELECT DISTINCT c FROM Cliente AS c WHERE c.nome LIKE %:nome%")
	public Page<ClienteDTO> searchAll(@Param("nome") String nome, Pageable pageRequest);
	
	@Query("SELECT DISTINCT c FROM Cliente AS c WHERE c.id = :id")
	public Page<ClienteDTO> searchById(@Param("id") Integer id, Pageable pageRequest);
	
	public Optional<Cliente> findByEmail(String email);
}
