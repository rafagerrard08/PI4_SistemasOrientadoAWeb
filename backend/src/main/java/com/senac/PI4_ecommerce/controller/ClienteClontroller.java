package com.senac.PI4_ecommerce.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.senac.PI4_ecommerce.model.Cliente;
import com.senac.PI4_ecommerce.service.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteClontroller {
	
	@Autowired
	private ClienteService clienteService;

	
	@RequestMapping(method = RequestMethod.GET, value = "/validarLogin")
	public ResponseEntity<Cliente> validarLogin(@RequestParam String email, @RequestParam String senha, HttpServletRequest req) {
		ResponseEntity<Cliente> resp = clienteService.validarLogin(email, senha, req);

		return resp;
	}

	

}
