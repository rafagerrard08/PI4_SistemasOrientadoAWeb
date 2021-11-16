package com.senac.PI4_ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.senac.PI4_ecommerce.model.Pedido;
import com.senac.PI4_ecommerce.service.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getPedidos(@PathVariable Integer idCliente) {
		List<Pedido> pedidos = pedidoService.getPedidos(idCliente);

		return ResponseEntity.ok().body(pedidos);
	}
}
