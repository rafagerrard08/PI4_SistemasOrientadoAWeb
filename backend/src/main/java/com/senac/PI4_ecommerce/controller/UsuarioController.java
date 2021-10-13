package com.senac.PI4_ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.senac.PI4_ecommerce.controller.utils.Util;
import com.senac.PI4_ecommerce.dto.UsuarioDTO;
import com.senac.PI4_ecommerce.model.Usuario;
import com.senac.PI4_ecommerce.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

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

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
		usuario.setId(null);
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		return ResponseEntity.ok(usuarioService.save(usuario));
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Usuario> update(@RequestBody Usuario usuario) {
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		return ResponseEntity.ok(usuarioService.save(usuario));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/validarLogin")
	public ResponseEntity<Boolean> validarLogin(@RequestParam String email, @RequestParam String senha) {
		ResponseEntity<Boolean> valido = usuarioService.validarLogin(email, senha);

		return valido;
	}

}
