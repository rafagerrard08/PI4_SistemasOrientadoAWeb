package com.senac.PI4_ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.senac.PI4_ecommerce.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
	@Query("SELECT DISTINCT c FROM Categoria AS c WHERE c.id LIKE :id")
	public Optional<Categoria> getCategoria(@Param("id") Integer id);

}
