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

import com.senac.PI4_ecommerce.dto.ClienteDTO;
import com.senac.PI4_ecommerce.dto.EnderecoDTO;
import com.senac.PI4_ecommerce.dto.NovoClienteDTO;
import com.senac.PI4_ecommerce.model.Cidade;
import com.senac.PI4_ecommerce.model.Cliente;
import com.senac.PI4_ecommerce.model.Endereco;
import com.senac.PI4_ecommerce.model.UF;
import com.senac.PI4_ecommerce.model.enums.EstadoCadastro;
import com.senac.PI4_ecommerce.repository.ClienteRepository;
import com.senac.PI4_ecommerce.repository.EnderecoRepository;
import com.senac.PI4_ecommerce.service.exception.InvalidDataException;
import com.senac.PI4_ecommerce.service.exception.ObjectAlreadyExistsException;
import com.senac.PI4_ecommerce.service.exception.ObjectNotFoundException;

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
			throw new InvalidDataException("Usuario ou Senha Invlaido!");
		}

		boolean valido = encoder.matches(senha, cliente.get().getSenha());

		if (cliente.get().getEstado().equals(EstadoCadastro.INATIVO)) {
			throw new InvalidDataException("Usuario ou Senha Invlaido!");
		}

		if (!valido) {
			throw new InvalidDataException("Usuario ou Senha Invlaido!");
		}

		ServletContext session = req.getServletContext();
		session.setAttribute("cliente", cliente.get());

		return ResponseEntity.status(HttpStatus.OK).body(cliente.get());
	}

	public Cliente save(NovoClienteDTO clienteDTO) {
		Optional<Cliente> verificaEmailExistente = clienteRepository.findByEmail(clienteDTO.getEmail());
		Optional<Cliente> verificaCpfExistente = clienteRepository.findByCpf(clienteDTO.getCpf());

		if (verificaEmailExistente.isEmpty()) {
			if (verificaCpfExistente.isEmpty()) {

				Cliente cliente = new Cliente(null, clienteDTO.getNomeCompleto(), clienteDTO.getEmail(),
						clienteDTO.getCpf(), clienteDTO.getSenha(), EstadoCadastro.ATIVO, clienteDTO.getGenero(),
						clienteDTO.getDataNascimento());

				List<Endereco> enderecos = new ArrayList<>();

				for (EnderecoDTO enderecoDTO : clienteDTO.getEnderecos()) {
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

					Endereco end = new Endereco(enderecoDTO, cidade, cliente);
					enderecos.add(end);
				}

				cliente.setEnderecos(enderecos);

				Cliente clienteSalvo = clienteRepository.save(cliente);

				ufRepository.saveAll(enderecos);

				return clienteSalvo;
			} else {
				throw new ObjectAlreadyExistsException(
						"Ja existe uma conta com o cpf [ " + clienteDTO.getCpf() + " ] cadastrado!");
			}
		} else {
			throw new ObjectAlreadyExistsException(
					"Ja existe uma conta com o e-mail [ " + clienteDTO.getEmail() + " ] cadastrado!");
		}
	}

	public Cliente update(ClienteDTO cliente) {
		Optional<Cliente> clienteAtual = clienteRepository.findById(cliente.getId());

		if (!clienteAtual.isEmpty()) {

			Cliente novoCliente = clienteAtual.get();
			novoCliente.setNomeCompleto(cliente.getNomeCompleto());
			novoCliente.setDataNascimento(cliente.getDataNascimento());
			novoCliente.setGenero(cliente.getGenero());

			clienteRepository.save(novoCliente);
			return novoCliente;

		} else {
			throw new ObjectNotFoundException("Nao foi encontrado um cliente com este ID.");
		}
	}

	public void updateSenha(Integer id, String senhaAtual, String senhaNova) {
		if (senhaAtual == null || senhaNova == null)
			throw new InvalidDataException("As senhas informadas nao podem estar nulas!");

		Optional<Cliente> clienteAtual = clienteRepository.findById(id);
		if (!clienteAtual.isEmpty()) {

			boolean valido = encoder.matches(senhaAtual, clienteAtual.get().getSenha());
			if (valido) {
				Cliente clienteNovo = clienteAtual.get();
				clienteNovo.setSenha(encoder.encode(senhaNova));
				clienteRepository.save(clienteNovo);

			} else
				throw new InvalidDataException("A senha atual informada nao corresponde a senha cadastrada!");

		} else
			throw new ObjectNotFoundException("Nao foi encontrado cliente com id [ " + id + " ]");

	}
}
