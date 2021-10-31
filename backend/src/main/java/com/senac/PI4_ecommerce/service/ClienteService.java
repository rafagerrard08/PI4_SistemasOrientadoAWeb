package com.senac.PI4_ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.senac.PI4_ecommerce.dto.EnderecoDTO;
import com.senac.PI4_ecommerce.dto.NovoClienteDTO;
import com.senac.PI4_ecommerce.model.Cidade;
import com.senac.PI4_ecommerce.model.Cliente;
import com.senac.PI4_ecommerce.model.Endereco;
import com.senac.PI4_ecommerce.model.UF;
import com.senac.PI4_ecommerce.model.enums.EstadoCadastro;
import com.senac.PI4_ecommerce.repository.ClienteRepository;
import com.senac.PI4_ecommerce.repository.EnderecoRepository;
import com.senac.PI4_ecommerce.service.exception.ObjectAlreadyExistsException;

@Service
public class ClienteService {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private UFService ufService;

	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private EnderecoRepository ufRepository;

	public ResponseEntity<Cliente> validarLogin(String email, String senha, HttpServletRequest req) {
		Optional<Cliente> cliente = clienteRepository.findByEmail(email);

		if (cliente.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		boolean valido = encoder.matches(senha, cliente.get().getSenha());

		if (cliente.get().getEstado().equals(EstadoCadastro.INATIVO)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		if (!valido) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		ServletContext session = req.getServletContext();
		session.setAttribute("cliente", cliente.get());

		return ResponseEntity.status(HttpStatus.OK).body(cliente.get());
	}

	public Cliente save(NovoClienteDTO clienteDTO) {
		Optional<Cliente> verificaExistente = clienteRepository.findByEmail(clienteDTO.getEmail());
		if (verificaExistente.isEmpty()) {
						
			Cliente cliente = new Cliente(null, clienteDTO.getNomeCompleto(), clienteDTO.getEmail(), clienteDTO.getCpf(),
					clienteDTO.getSenha(), EstadoCadastro.ATIVO, clienteDTO.getGenero(), clienteDTO.getDataNascimento());
			
			List<Endereco> enderecos = new ArrayList<>();
			
			for(EnderecoDTO enderecoDTO:  clienteDTO.getEnderecos()) {
				UF estado = null;
				Optional<UF> verificaEstado = ufService.getEstado(enderecoDTO.getUf());
				if (verificaEstado.isEmpty()) {
					estado = ufService.save(enderecoDTO.getUf());
				} else {
					estado = verificaEstado.get();
				}
				
				Cidade cidade = null;
				Optional<Cidade> verificaCidade = cidadeService.getCidade(enderecoDTO.getCidade(), estado);
				if (verificaCidade.isEmpty()) {
					cidade = cidadeService.save(enderecoDTO.getCidade(), estado);
				} else {
					cidade = verificaCidade.get();
				}
				
				Endereco end =  new Endereco(enderecoDTO, cidade, cliente);
				enderecos.add(end);
			}
			
			cliente.setEnderecos(enderecos);
			
			Cliente clienteSalvo = clienteRepository.save(cliente);
			
			ufRepository.saveAll(enderecos);

			return clienteSalvo;
		} else {
			throw new ObjectAlreadyExistsException(
					"Ja existe uma conta com e-mail [ " + clienteDTO.getEmail() + " ] cadastrado!");
		}
	}

}
