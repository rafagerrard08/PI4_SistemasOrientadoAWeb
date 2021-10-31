package com.senac.PI4_ecommerce;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.senac.PI4_ecommerce.model.Categoria;
import com.senac.PI4_ecommerce.model.Usuario;
import com.senac.PI4_ecommerce.repository.CategoriaRepository;
import com.senac.PI4_ecommerce.repository.CidadeRepository;
import com.senac.PI4_ecommerce.repository.ClienteRepository;
import com.senac.PI4_ecommerce.repository.EnderecoRepository;
import com.senac.PI4_ecommerce.repository.ProdutoRepository;
import com.senac.PI4_ecommerce.repository.UFRepository;
import com.senac.PI4_ecommerce.repository.UsuarioRepository;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Pi4EcommerceApplication implements CommandLineRunner {

	@Bean
	public static PasswordEncoder getPasswordEnconder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private UFRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
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
		

//		UF est1 = new UF(1, "São Paulo");
//		estadoRepository.saveAll(Arrays.asList(est1));
//
//		Cidade cid1 = new Cidade(1, "São Paulo", est1);
//		Cidade cid2 = new Cidade(2, "Mongagua", est1);
//
//		cidadeRepository.saveAll(Arrays.asList(cid1, cid2));
//		
//		//(Integer id, String nome, @Email String email, String cpf, String senha, Endereco enderecoCobranca, EstadoCadastro estado
//		
//
//		Cliente cli1 = new Cliente(1, "Victor", "victor@eloja.com", "00000000000",
//				"$2a$10$bcRmD4S44LY3bgR6Piia4Oe6W0OE8edW0wCtAd9znb540iEB8j3Wq", EstadoCadastro.ATIVO, "Masculno", sdf.parse("20/10/2017"));
//		
//		System.out.println(cli1.getNomeCompleto());
//		
//		// (Integer id, TipoEndereco tipo, String logradouro, String numero, String complemento, String bairro, String cep, Cidade cidade
//		
//		Endereco end1 = new Endereco(1, TipoEndereco.FATURAMENTO, "Rua Miguel Yunes", "485", "Torre 3 Apto 175", "Usina Piratininga", "04444000", cid1, cli1);
//		Endereco end2 = new Endereco(2, TipoEndereco.ENTREGA, "Rua Saint Germain", "120", null, "Jardim Edda", "04844010", cid1, cli1);
//		Endereco end3 = new Endereco(3, TipoEndereco.ENTREGA, "Rua Dos Bagres", "293", null, "Parque Marinho", "11730-000", cid2, cli1);
//
//
//		cli1.setEnderecos(Arrays.asList(end1, end2, end3));
//		
//		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3));
//		clienteRepository.save(cli1);
	
	}

}
