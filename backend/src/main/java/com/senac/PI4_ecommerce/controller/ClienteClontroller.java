package com.senac.PI4_ecommerce.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.senac.PI4_ecommerce.Utils.SessaoUtils;
import com.senac.PI4_ecommerce.controller.utils.Util;
import com.senac.PI4_ecommerce.dto.ClienteDTO;
import com.senac.PI4_ecommerce.dto.EnderecoDTO;
import com.senac.PI4_ecommerce.dto.NovoClienteDTO;
import com.senac.PI4_ecommerce.model.Cliente;
import com.senac.PI4_ecommerce.model.Usuario;
import com.senac.PI4_ecommerce.repository.ClienteRepository;
import com.senac.PI4_ecommerce.service.ClienteService;
import com.senac.PI4_ecommerce.service.exception.InvalidDataException;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteClontroller {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteRepository clienteRepository;

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
			String senha = novoCliente.getSenha();
			if(Objects.isNull(senha) || senha.length() < 5 || senha.length() > 80) {
				throw new InvalidDataException("Este campo 'senha' é obrigatorio e deve ter entre 5 e 80 caracteres");
			}
			
			novoCliente.setSenha(encoder.encode(novoCliente.getSenha()));
			Cliente clienteSalvo = clienteService.save(novoCliente);
			return ResponseEntity.ok(clienteSalvo);
		} else {
			throw new InvalidDataException("CPF Invalido: [ " + novoCliente.getCpf() + " ]");
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody NovoClienteDTO cliente, @PathVariable("id") Integer id, HttpServletRequest req) {
		// se nao tiver sessao já sera lançado exception
		SessaoUtils.getClienteDaSessao(req);
		
		cliente.setId(id);
		
		// se tiver valor na senha é para atualizar, se nao mantem a mesma
		if(cliente.getSenha() != null) {
			clienteService.updateSenha(id, cliente.getSenha());
		}
		
		// atualiza dados comuns, menos os endereços
		Cliente clienteAtualizado = clienteService.update(cliente);
		
		// remove todos endereços atuais e atualiza com os enviados
		clienteService.updateEnderecos(clienteAtualizado.getId(), new ArrayList<>(cliente.getEnderecos()));

		return ResponseEntity.ok().build();

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable("id") Integer id, HttpServletRequest req) {
		
		SessaoUtils.getClienteDaSessao(req);
		
		Optional<Cliente> cliente = clienteRepository.findById(id);
		
		return ResponseEntity.ok(cliente.get());
	}
	
}
