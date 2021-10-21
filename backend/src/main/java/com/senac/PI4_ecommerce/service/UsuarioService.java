package com.senac.PI4_ecommerce.service;

import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

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
import com.senac.PI4_ecommerce.model.enums.EstadoUsuario;
import com.senac.PI4_ecommerce.repository.UsuarioRepository;
import com.senac.PI4_ecommerce.service.exception.InvalidDataException;
import com.senac.PI4_ecommerce.service.exception.ObjectAlreadyExistsException;
import com.senac.PI4_ecommerce.service.exception.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder encoder;

	public Page<UsuarioDTO> search(String nome, Integer pagina, Integer itensPorPagina, String ordenarPor,
			String direcao, String tipo, Usuario userSessao) {
		
		if (pagina < 0) {
			// Implementar erro
		}
		PageRequest pr = PageRequest.of(pagina, itensPorPagina, Direction.valueOf(direcao), ordenarPor);
		Page<UsuarioDTO> usuarios = null;
		
		if(!"Administrador".equalsIgnoreCase(userSessao.getTipoUsuario().getDescricao())) {
			return usuarioRepository.searchById(userSessao.getId(), pr);
		}

		if ("ativo".equalsIgnoreCase(tipo)) {
			usuarios = usuarioRepository.search(nome, pr, EstadoProduto.ATIVO.getId());
		} else if ("inativo".equalsIgnoreCase(tipo)) {
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

	public ResponseEntity<Usuario> validarLogin(String email, String senha, HttpServletRequest req) {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

		if (usuario.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		boolean valido = encoder.matches(senha, usuario.get().getSenha());
		
		if(usuario.get().getEstadoUsuario().equals(EstadoUsuario.INATIVO)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
		if(!valido) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
		ServletContext session = req.getServletContext();
		session.setAttribute("usuario", usuario.get());

		return ResponseEntity.status(HttpStatus.OK).body(usuario.get());
	}

	public UsuarioDTO getUsuario(String email) {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
		
		if(usuario.isPresent()) {
			return new UsuarioDTO(usuario.get());
		} else
			throw new ObjectNotFoundException("Nenhum usuario cadastrado com o email [ " + email + " ]");
		
		
	}
	
	public UsuarioDTO getUsuarioById(Integer id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		if(usuario.isPresent()) {
			return new UsuarioDTO(usuario.get());
		} else
			throw new ObjectNotFoundException("Nenhum usuario cadastrado com o id [ " + id + " ]");
		
		
	}
}
