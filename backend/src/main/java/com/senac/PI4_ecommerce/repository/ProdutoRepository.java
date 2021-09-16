package com.senac.PI4_ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.senac.PI4_ecommerce.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	
    @Query("SELECT p FROM Produto AS p ORDER BY p.id DESC")
    List<Produto> findAllDesc();


}
