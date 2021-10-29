package com.senac.PI4_ecommerce.service;

import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.senac.PI4_ecommerce.model.Cliente;
import com.senac.PI4_ecommerce.model.enums.EstadoCadastro;
import com.senac.PI4_ecommerce.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private ClienteRepository clienteRepository;

	public ResponseEntity<Cliente> validarLogin(String email, String senha, HttpServletRequest req) {
		Optional<Cliente> cliente = clienteRepository.findByEmail(email);

		if (cliente.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		boolean valido = encoder.matches(senha, cliente.get().getSenha());
		
		if(cliente.get().getEstado().equals(EstadoCadastro.INATIVO)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
		if(!valido) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
		ServletContext session = req.getServletContext();
		session.setAttribute("usuario", cliente.get());

		return ResponseEntity.status(HttpStatus.OK).body(cliente.get());
	}
}
