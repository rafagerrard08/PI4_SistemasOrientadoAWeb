package com.senac.PI4_ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.senac.PI4_ecommerce.model.Cliente;
import com.senac.PI4_ecommerce.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
	
	@Query("SELECT DISTINCT e FROM Endereco AS e WHERE e.cliente LIKE :cliente ")
	public List<Endereco> getEnderecos(@Param("cliente") Cliente cliente);

}
