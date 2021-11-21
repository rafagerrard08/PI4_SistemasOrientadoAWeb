package com.senac.PI4_ecommerce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.senac.PI4_ecommerce.Utils.SessaoUtils;
import com.senac.PI4_ecommerce.dto.PedidoDTO;
import com.senac.PI4_ecommerce.model.Cliente;
import com.senac.PI4_ecommerce.model.Pedido;
import com.senac.PI4_ecommerce.service.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/{idCliente}")
	public ResponseEntity<?> getPedidos(@PathVariable Integer idCliente) {
		List<Pedido> pedidos = pedidoService.getPedidos(idCliente);

		return ResponseEntity.ok().body(pedidos);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> salvar(@RequestBody PedidoDTO dto, HttpServletRequest req) {
		
		// validar se cliente esta na sessão e pegar o ID dele
		//Cliente clienteDaSessao = SessaoUtils.getClienteDaSessao(req);
		
		// validar se endereço possui id, se nao existir primeiro o ideal é criar esse endereço
//		if(dto.getEnderecoEntrega().getId() == null) {
//			// criar endereço
//		} 
		// se  o endereço tiver ID, melhor fazer um select na base pelo ID p/ trazer os IDs da cidade e UF
		// else {
//			enderecoRepository.findById(dto.getEnderecoEntrega().getId());
//		}	
		
		// depois salva os dados de pagamento usando o Id cliente de foreign key
		
		// TABELA PEDIDO: idPedido(auto-increment) ; idCliente ; idPagamento ; idEndereco ; valorFrete
		
		// TABELA ITEM: idItem(auto-increment) ; idPedido ; idProduto ; quantidade

		return ResponseEntity.ok().body(dto);
	}
}
