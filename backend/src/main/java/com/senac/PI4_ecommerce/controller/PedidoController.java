package com.senac.PI4_ecommerce.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.senac.PI4_ecommerce.Utils.SessaoUtils;
import com.senac.PI4_ecommerce.dto.EnderecoDTO;
import com.senac.PI4_ecommerce.dto.ItensDTO;
import com.senac.PI4_ecommerce.dto.PedidoDTO;
import com.senac.PI4_ecommerce.model.Cidade;
import com.senac.PI4_ecommerce.model.Cliente;
import com.senac.PI4_ecommerce.model.Endereco;
import com.senac.PI4_ecommerce.model.ItemPedido;
import com.senac.PI4_ecommerce.model.Pagamento;
import com.senac.PI4_ecommerce.model.PagamentoComBoleto;
import com.senac.PI4_ecommerce.model.PagamentoComCartao;
import com.senac.PI4_ecommerce.model.Pedido;
import com.senac.PI4_ecommerce.model.Produto;
import com.senac.PI4_ecommerce.model.UF;
import com.senac.PI4_ecommerce.repository.ClienteRepository;
import com.senac.PI4_ecommerce.repository.EnderecoRepository;
import com.senac.PI4_ecommerce.repository.ItemPedidoRepository;
import com.senac.PI4_ecommerce.repository.PagamentoRepository;
import com.senac.PI4_ecommerce.repository.PedidoRepository;
import com.senac.PI4_ecommerce.repository.ProdutoRepository;
import com.senac.PI4_ecommerce.service.CidadeService;
import com.senac.PI4_ecommerce.service.PedidoService;
import com.senac.PI4_ecommerce.service.UFService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private UFService ufService;
	@Autowired
	private CidadeService cidadeService;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@RequestMapping(method = RequestMethod.GET, value = "/{idCliente}")
	public ResponseEntity<?> getPedidos(@PathVariable Integer idCliente) {
		List<Pedido> pedidos = pedidoService.getPedidos(idCliente);

		return ResponseEntity.ok().body(pedidos);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> salvar(@RequestBody PedidoDTO dto, HttpServletRequest req) throws ParseException {

		// Validar se cliente esta na sessão e pegar o ID dele
		Cliente clienteDaSessao = SessaoUtils.getClienteDaSessao(req);

		// Definido Pedido
		Pedido pedido = new Pedido();
		pedido.setCliente(clienteDaSessao);

		// validar se endereço possui id, se nao existir primeiro o ideal é criar esse
		// endereço
		EnderecoDTO enderecoinformado = dto.getEnderecoEntrega();

		Endereco endereco = null;
		if (enderecoinformado.getId() == null) {
			UF estado = null;
			Optional<UF> verificaEstado = ufService.getEstado(enderecoinformado.getUf());
			if (verificaEstado.isEmpty()) {
				estado = ufService.save(enderecoinformado.getUf());
			} else {
				estado = verificaEstado.get();
			}

			Cidade cidade = null;
			Optional<Cidade> verificaCidade = cidadeService.getCidade(enderecoinformado.getCidade(), estado);
			if (verificaCidade.isEmpty()) {
				cidade = cidadeService.save(enderecoinformado.getCidade(), estado);
			} else {
				cidade = verificaCidade.get();
			}

			endereco = new Endereco(enderecoinformado, cidade, clienteDaSessao);
			clienteDaSessao.getEnderecos().add(endereco);

			clienteDaSessao = clienteRepository.save(clienteDaSessao);
			endereco = enderecoRepository.save(endereco);

		} else {
			Optional<Endereco> enderecoJaCadastrado = enderecoRepository.findById(enderecoinformado.getId());
			endereco = enderecoJaCadastrado.get();

		}

		pedido.setEnderecoDeEntrega(endereco);

		Set<ItemPedido> listaProdutos = new HashSet<>();
		for (ItensDTO item : dto.getItens()) {
			Produto produto = produtoRepository.getById(item.getIdProduto());
			ItemPedido ip = new ItemPedido(pedido, produto, item.getQuantidade());
			listaProdutos.add(ip);
		}

		pedido.setItens(listaProdutos);

		Pagamento pgto = null;
		System.out.println(dto.getDadosPagamento().getForma());
		if (dto.getDadosPagamento().getForma() == "boleto") {
			pgto = new PagamentoComBoleto(null, pedido, sdf.parse("16/11/2021"),
					"10499.71201 22517.701235 45678.901617 1 69800000012345");
			pedido.setPagamento(pgto);
			pagamentoRepository.save(pgto);

		} else if (dto.getDadosPagamento().getForma() == "credito") {
			Date dataVencimento = Date
					.from(dto.getDadosPagamento().getDataVencimento().atStartOfDay(ZoneId.systemDefault()).toInstant());
			pgto = new PagamentoComCartao(null, pedido, dto.getDadosPagamento().getQtdParcelas(),
					dto.getDadosPagamento().getNumeroCartao(), dto.getDadosPagamento().getCodVerificador(),
					dataVencimento);
			pedido.setPagamento(pgto);
			pagamentoRepository.save(pgto);
		}
		pedido.setPagamento(pgto);

		pedido.setValorFrete(dto.getValorFrete());
		pedido.calculaValorTotal();

		pedido = pedidoRepository.save(pedido);
		itemPedidoRepository.saveAll(listaProdutos);

		return ResponseEntity.ok().body(pedido.getId());
	}
}
