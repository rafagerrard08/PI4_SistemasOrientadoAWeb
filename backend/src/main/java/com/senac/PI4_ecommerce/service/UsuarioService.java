package com.senac.PI4_ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.senac.PI4_ecommerce.dto.UsuarioDTO;
import com.senac.PI4_ecommerce.model.Usuario;
import com.senac.PI4_ecommerce.model.enums.EstadoProduto;
import com.senac.PI4_ecommerce.repository.UsuarioRepository;
import com.senac.PI4_ecommerce.service.exception.InvalidDataException;
import com.senac.PI4_ecommerce.service.exception.ObjectAlreadyExistsException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder encoder;

	public Page<UsuarioDTO> search(String nome, Integer pagina, Integer itensPorPagina, String ordenarPor,
			String direcao, String tipo) {
		if (pagina < 0) {
			// Implementar erro
		}
		PageRequest pr = PageRequest.of(pagina, itensPorPagina, Direction.valueOf(direcao), ordenarPor);
		Page<UsuarioDTO> usuarios = null;

		if (tipo.equals("administrador")) {
			usuarios = usuarioRepository.search(nome, pr, EstadoProduto.ATIVO.getId());
		} else if (tipo.equals("estoquista")) {
			usuarios = usuarioRepository.search(nome, pr, EstadoProduto.INATIVO.getId());
		} else {
			usuarios = usuarioRepository.searchAll(nome, pr);
		}

		return usuarios;
	}

	public Usuario save(Usuario usuario) {
		Optional<Usuario> verificaExistente = usuarioRepository.findByEmail(usuario.getEmail());

		if ((verificaExistente.isEmpty())) {
			Usuario usuarioSalvo = usuarioRepository.save(usuario);
			return usuarioSalvo;
		} else {
			throw new ObjectAlreadyExistsException(
					"Ja existe uma conta com e-mail [ " + usuario.getEmail() + " ] cadastrado!");
		}
	}

	public Usuario update(Usuario usuario) {
		Optional<Usuario> usuarioAtual = usuarioRepository.findById(usuario.getId());

		System.out.println(usuarioAtual.get().getEmail());
		System.out.println(usuario.getEmail());

		if (usuarioAtual.get().getEmail().equals(usuario.getEmail())) {

			if (!usuarioAtual.isEmpty()) {
				
				Usuario usuarioSalvo = usuarioRepository.save(usuario);
				return usuarioSalvo;
				
			} else
				throw new ObjectAlreadyExistsException(
						"Ja existe uma conta com e-mail [ " + usuario.getEmail() + " ] cadastrado!");
		} else
			throw new InvalidDataException("O e-mail informado nao pode ser diferente do email cadastrado.");

	}

	public ResponseEntity<Boolean> validarLogin(String email, String senha) {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

		if (usuario.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
		}

		boolean valido = encoder.matches(senha, usuario.get().getSenha());

		HttpStatus status = (valido) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;

		return ResponseEntity.status(status).body(valido);
	}
}
