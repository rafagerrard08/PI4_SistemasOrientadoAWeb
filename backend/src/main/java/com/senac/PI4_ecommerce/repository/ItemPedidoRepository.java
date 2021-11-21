package com.senac.PI4_ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senac.PI4_ecommerce.model.ItemPedido;
import com.senac.PI4_ecommerce.model.ItemPedidoPK;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoPK> {

}
