package com.senac.PI4_ecommerce.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.senac.PI4_ecommerce.dto.UsuarioDTO;
import com.senac.PI4_ecommerce.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	@Query("SELECT DISTINCT u FROM Usuario AS u WHERE u.nome LIKE %:nome% AND u.tipoUsuario LIKE :tipo")
	public Page<UsuarioDTO> search(@Param("nome") String nome, Pageable pageRequest, @Param("tipo") Integer tipo);
	
	@Query("SELECT DISTINCT u FROM Usuario AS u WHERE u.nome LIKE %:nome%")
	public Page<UsuarioDTO> searchAll(@Param("nome") String nome, Pageable pageRequest);
	
	public Optional<Usuario> findByEmail(String email);
}
