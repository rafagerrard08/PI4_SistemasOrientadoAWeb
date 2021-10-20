package com.senac.PI4_ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.senac.PI4_ecommerce.controller.utils.Util;
import com.senac.PI4_ecommerce.dto.UsuarioDTO;
import com.senac.PI4_ecommerce.model.Usuario;
import com.senac.PI4_ecommerce.model.enums.EstadoUsuario;
import com.senac.PI4_ecommerce.repository.UsuarioRepository;
import com.senac.PI4_ecommerce.service.UsuarioService;
import com.senac.PI4_ecommerce.service.exception.InvalidDataException;
import com.senac.PI4_ecommerce.service.exception.ObjectAlreadyExistsException;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder encoder;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<UsuarioDTO>> getUsuariosPage(
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@RequestParam(value = "itensPorPagina", defaultValue = "9") Integer itensPorPagina,
			@RequestParam(value = "ordenarPor", defaultValue = "id") String ordenarPor,
			@RequestParam(value = "direcao", defaultValue = "DESC") String direcao,
			@RequestParam(value = "tipo", defaultValue = "ativo") String tipo) {

		String nomeDecode = Util.decodeParam(nome);

		Page<UsuarioDTO> usuarios = usuarioService.search(nomeDecode, pagina, itensPorPagina, ordenarPor, direcao,
				tipo);

		return ResponseEntity.ok().body(usuarios);
	}
	
	@RequestMapping(value = "/{email}", method = RequestMethod.GET)
	public ResponseEntity<?> getUsuarioByEmail(@PathVariable String email) {
		
		UsuarioDTO usuario = usuarioService.getUsuario(email);
		
		return ResponseEntity.ok().body(usuario);
	}
	
	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUsuarioId(@PathVariable Integer id) {
		
		UsuarioDTO usuario = usuarioService.getUsuarioById(id);
		
		return ResponseEntity.ok().body(usuario);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UsuarioDTO> save(@RequestBody Usuario usuario) {
		if (Util.isCPF(usuario.getCpf())) {
			usuario.setId(null);
			usuario.setEstadoUsuario(EstadoUsuario.ATIVO);
			usuario.setSenha(encoder.encode(usuario.getSenha()));
			UsuarioDTO usuarioSalvo = new UsuarioDTO(usuarioService.save(usuario));
			return ResponseEntity.ok(usuarioSalvo);
		} else {
			throw new InvalidDataException("CPF Invalido: [ " + usuario.getCpf() + " ]");
		}

	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<UsuarioDTO> update(@RequestBody Usuario usuario, @PathVariable("id") Integer id) {
		usuario.setId(id);
		if (Util.isCPF(usuario.getCpf())) {
			usuario.setSenha(encoder.encode(usuario.getSenha()));
			UsuarioDTO usuarioAtualizado = new UsuarioDTO(usuarioService.update(usuario));
			return ResponseEntity.ok(usuarioAtualizado);
		} else {
			throw new InvalidDataException("CPF Invalido: [ " + usuario.getCpf() + " ]");
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "updateEstado/{id}/{novoEstado}")
	public ResponseEntity<UsuarioDTO> updateEstado(@PathVariable("id") Integer id, @PathVariable("novoEstado") String novoEstado) {
		
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));
		
		System.out.println(novoEstado);
		
		EstadoUsuario estado = null;
		if(novoEstado.equals("ATIVO")) {
			estado = EstadoUsuario.ATIVO;
		} else if (novoEstado.equals("INATIVO")) {
			estado = EstadoUsuario.INATIVO;
		}else {
			throw new InvalidDataException("Estado Invalido: [ " + novoEstado + " ]");
		}
		
		if(estado.equals(usuario.getEstadoUsuario())) {
			throw new ObjectAlreadyExistsException("O usuario ja tem o status [ " + novoEstado + " ].");
		}
		
		usuario.setEstadoUsuario(estado);
		
		return ResponseEntity.ok(new UsuarioDTO(usuarioService.update(usuario)));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/validarLogin")
	public ResponseEntity<Boolean> validarLogin(@RequestParam String email, @RequestParam String senha) {
		ResponseEntity<Boolean> valido = usuarioService.validarLogin(email, senha);

		return valido;
	}

}
