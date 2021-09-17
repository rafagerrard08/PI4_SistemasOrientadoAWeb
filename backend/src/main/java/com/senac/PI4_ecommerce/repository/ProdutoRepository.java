package com.senac.PI4_ecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.senac.PI4_ecommerce.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	@Query("SELECT DISTINCT p FROM Produto AS p WHERE p.nome LIKE %:nome%")
	public Page<Produto> search(@Param("nome") String nome, Pageable pageRequest);

}
