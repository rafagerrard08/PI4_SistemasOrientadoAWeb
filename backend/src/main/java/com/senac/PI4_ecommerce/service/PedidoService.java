package com.senac.PI4_ecommerce.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senac.PI4_ecommerce.model.Cliente;
import com.senac.PI4_ecommerce.model.Pedido;
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
		Optional<Cliente> cliente = clienteRepository.findById(idCliente);

		if (!cliente.isEmpty()) {
			List<Pedido> pedidos = pedidoRepository.findByCliente(cliente.get());
			if (!pedidos.isEmpty()) {
				return pedidos;
			} else
				throw new ObjectNotFoundException(
						"Nenhum pedido cadastrado para o clilente [" + cliente.get().getNomeCompleto() + "]!");
		} else
			throw new ObjectNotFoundException("Nenhum cliente com id [" + idCliente + "] cadastrado!");

	}

	public List<Pedido> getPedidos(String direcao) {
		List<Pedido> pedidos = null;
		if (direcao.equals("DESC")) {
			pedidos = pedidoRepository.findAll();
			Collections.reverse(pedidos);
		} else {
			pedidos = pedidoRepository.findAll();
		}

		if (!pedidos.isEmpty()) {
			return pedidos;
		} else
			throw new ObjectNotFoundException("Nenhum pedido cadastrado!");
	}

	public Pedido getPedido(Integer nroPedido) {
		Optional<Pedido> pedido = pedidoRepository.findById(nroPedido);
		if (!pedido.isEmpty()) {
			return pedido.get();
		} else
			throw new ObjectNotFoundException("Nenhum pedido cadastrado com id [" + nroPedido + "]!");
	}
}
