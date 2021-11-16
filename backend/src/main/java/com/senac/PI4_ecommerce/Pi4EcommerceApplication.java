package com.senac.PI4_ecommerce;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.senac.PI4_ecommerce.model.Categoria;
import com.senac.PI4_ecommerce.model.Cliente;
import com.senac.PI4_ecommerce.model.Endereco;
import com.senac.PI4_ecommerce.model.ItemPedido;
import com.senac.PI4_ecommerce.model.Pagamento;
import com.senac.PI4_ecommerce.model.PagamentoComBoleto;
import com.senac.PI4_ecommerce.model.Pedido;
import com.senac.PI4_ecommerce.model.Produto;
import com.senac.PI4_ecommerce.model.Usuario;
import com.senac.PI4_ecommerce.model.enums.EstadoCadastro;
import com.senac.PI4_ecommerce.repository.CategoriaRepository;
import com.senac.PI4_ecommerce.repository.ClienteRepository;
import com.senac.PI4_ecommerce.repository.EnderecoRepository;
import com.senac.PI4_ecommerce.repository.ItemPedidoRepository;
import com.senac.PI4_ecommerce.repository.PagamentoRepository;
import com.senac.PI4_ecommerce.repository.PedidoRepository;
import com.senac.PI4_ecommerce.repository.ProdutoRepository;
import com.senac.PI4_ecommerce.repository.UsuarioRepository;
import com.senac.PI4_ecommerce.service.ProdutoService;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Pi4EcommerceApplication implements CommandLineRunner {

	@Bean
	public static PasswordEncoder getPasswordEnconder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private ProdutoService produtoService;
	
	
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public static void main(String[] args) {
		SpringApplication.run(Pi4EcommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(1, "Eletrodomésticos");
		Categoria cat2 = new Categoria(2, "Celulares e Smartphones");
		Categoria cat3 = new Categoria(3, "TV e Vídeo");
		Categoria cat4 = new Categoria(4, "Esporte e Lazer");
		Categoria cat5 = new Categoria(5, "Móveis");
		Categoria cat6 = new Categoria(6, "Informática");
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6));
		

		Usuario user1 = new Usuario(1, "admin", "admin@eloja.com", "00000000000",
				"$2a$10$$2a$10$WRHMAoesxstlv8dAyOgRFuyfEJ93LIxuwZYjNVMizHgUSC0JCNh12", 1, 1);
		usuarioRepository.saveAll(Arrays.asList(user1));
		
		Cliente cli = clienteRepository.findById(1).get();	
		Endereco end = enderecoRepository.findById(2).get();	


		//public Pedido(Integer id, Cliente cliente, Endereco enderecoDeEntrega, Double valorFrete, Date data) {
		Pedido ped1 =  new Pedido(null, cli, end, 5.99, 123.99);
		
		Pagamento pgto = new PagamentoComBoleto(null, ped1, sdf.parse("16/11/2021"), "10499.71201 22517.701235 45678.901617 1 69800000012345");
		ped1.setPagamento(pgto);
		
		Produto p2 = produtoService.getProduto(1);
		Produto p3 = produtoService.getProduto(2);
		

		//public ItemPedido(Pedido pedido, Integer produto, Integer quantidade, Double preco) {
		ItemPedido ip2 = new ItemPedido(ped1, p2, 2);
		ItemPedido ip3 = new ItemPedido(ped1, p3, 1);
		ped1.getItens().add(ip2);
		ped1.getItens().add(ip2);

		ped1.getItens().addAll(Arrays.asList(ip2, ip3));
		
		
		pedidoRepository.save(ped1);
		pagamentoRepository.save(pgto);
		produtoRepository.saveAll(Arrays.asList(p2, p3));
		itemPedidoRepository.saveAll(Arrays.asList(ip2, ip3));
		

		
		
		

		

		
		
		

		
		//	public PagamentoComBoleto(Integer id, Pedido pedido, Date dataVencimento, String numero) {

	
	}

}
