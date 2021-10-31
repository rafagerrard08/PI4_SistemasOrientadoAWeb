package com.senac.PI4_ecommerce.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.senac.PI4_ecommerce.controller.utils.Util;
import com.senac.PI4_ecommerce.dto.ClienteDTO;
import com.senac.PI4_ecommerce.dto.NovoClienteDTO;
import com.senac.PI4_ecommerce.model.Cliente;
import com.senac.PI4_ecommerce.service.ClienteService;
import com.senac.PI4_ecommerce.service.exception.InvalidDataException;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteClontroller {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private PasswordEncoder encoder;

	@RequestMapping(method = RequestMethod.GET, value = "/validarLogin")
	public ResponseEntity<Cliente> validarLogin(@RequestParam String email, @RequestParam String senha,
			HttpServletRequest req) {
		ResponseEntity<Cliente> resp = clienteService.validarLogin(email, senha, req);

		return resp;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Cliente> inserir(@Valid @RequestBody NovoClienteDTO novoCliente) {
		if (Util.isCPF(novoCliente.getCpf())) {
			novoCliente.setSenha(encoder.encode(novoCliente.getSenha()));
			Cliente clienteSalvo = clienteService.save(novoCliente);
			return ResponseEntity.ok(clienteSalvo);
		} else {
			throw new InvalidDataException("CPF Invalido: [ " + novoCliente.getCpf() + " ]");
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<Cliente> update(@RequestBody ClienteDTO cliente, @PathVariable("id") Integer id) {
		cliente.setId(id);

		Cliente clienteAtualizado = clienteService.update(cliente);

		return ResponseEntity.ok(clienteAtualizado);

	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "updateSenha/{id}")
	public ResponseEntity<String> updateSenha(@PathVariable("id") Integer id, @RequestParam String senhaAtual, @RequestParam String senhaNova) {
		clienteService.updateSenha(id, senhaAtual, senhaNova);

		return ResponseEntity.ok().body("Senha Atualizada com sucesso!");

	}

}
