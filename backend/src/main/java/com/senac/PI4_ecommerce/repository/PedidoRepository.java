package com.senac.PI4_ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.senac.PI4_ecommerce.model.Cliente;
import com.senac.PI4_ecommerce.model.Pedido;
import com.senac.PI4_ecommerce.model.Produto;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	@Query("SELECT DISTINCT p FROM Pedido AS p WHERE p.cliente = :cliente")
	public List<Pedido> findByCliente(@Param("cliente") Cliente cliente);
	
	

}
