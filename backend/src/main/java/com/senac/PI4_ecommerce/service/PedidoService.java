package com.senac.PI4_ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senac.PI4_ecommerce.model.Cliente;
import com.senac.PI4_ecommerce.model.Pedido;
import com.senac.PI4_ecommerce.model.Produto;
import com.senac.PI4_ecommerce.repository.ClienteRepository;
import com.senac.PI4_ecommerce.repository.PedidoRepository;
import com.senac.PI4_ecommerce.service.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;

	public List<Pedido> getPedidos(Integer idCliente) {
		List<Pedido> pedidos = null;
		Optional<Cliente> cliente =  clienteRepository.findById(idCliente);
		
		if(!cliente.isEmpty()) {
//			pedidos = pedidoRepository.findByCliente(cliente.get());
		} else throw new ObjectNotFoundException("Nenhum pedido encontrado!");
		
		return pedidos;
	}

}
