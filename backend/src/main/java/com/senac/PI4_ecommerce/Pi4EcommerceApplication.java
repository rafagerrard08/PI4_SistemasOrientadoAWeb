package com.senac.PI4_ecommerce;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.senac.PI4_ecommerce.model.Categoria;
import com.senac.PI4_ecommerce.model.Produto;
import com.senac.PI4_ecommerce.model.enums.EstadoProduto;
import com.senac.PI4_ecommerce.repository.CategoriaRepository;
import com.senac.PI4_ecommerce.repository.ProdutoRepository;

@SpringBootApplication
public class Pi4EcommerceApplication implements CommandLineRunner {
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(Pi4EcommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Eletrodomésticos");
		Categoria cat2 = new Categoria(null, "Celulares e Smartphones");
		Categoria cat3 = new Categoria(null, "TV e Vídeo");
		Categoria cat4 = new Categoria(null, "Esporte e Lazer");
		Categoria cat5 = new Categoria(null, "Móveis");
		Categoria cat6 = new Categoria(null, "Informática");

		Set<String> img1 = new HashSet<String>();
		img1.add("/assets/images/eletrodomesticos/consul_micro-ondas_20L_CM020_I1.jpg");
		img1.add("/assets/images/eletrodomesticos/consul_micro-ondas_20L_CM020_I2.jpg");
		img1.add("/assets/images/eletrodomesticos/consul_micro-ondas_20L_CM020_I3.jpg");

		Set<String> img2 = new HashSet<String>();
		img2.add("/assets/images/eletrodomesticos/apple_iphone_12_pro_128gb_azul_pacifico_61P_I1.jpg");
		img2.add("/assets/images/eletrodomesticos/apple_iphone_12_pro_128gb_azul_pacifico_61P_I2.jpg");
		img2.add("/assets/images/eletrodomesticos/apple_iphone_12_pro_128gb_azul_pacifico_61P_I3.jpg");

		Set<String> img3 = new HashSet<String>();
		img3.add("/assets/images/eletrodomesticos/lg_smart_tv_led_pro_32_I1.jpg");
		img3.add("/assets/images/eletrodomesticos/lg_smart_tv_led_pro_32_I1_I2.jpg");
		img3.add("/assets/images/eletrodomesticos/lg_smart_tv_led_pro_32_I1_I3.jpg");

		Set<String> img4 = new HashSet<String>();
		img4.add("/assets/images/eletrodomesticos/Electrolux_Lavadora_Tecnologia_Jet_&_Clean_13_Kg_I1.jpg");
		img4.add("/assets/images/eletrodomesticos/Electrolux_Lavadora_Tecnologia_Jet_&_Clean_13_Kg_I2.jpg");
		img4.add("/assets/images/eletrodomesticos/Electrolux_Lavadora_Tecnologia_Jet_&_Clean_13_Kg_I3.jpg");

		Produto p1 = new Produto(null, "Micro-ondas 20L CM020", "Consul", 42, 678.30,
				"Micro-ondas CM020 Consul Espelhado 20 litros. Compacto e moderno, ele possui o Menu Uso Fácil e receitas pré-programadas que descomplicam o seu dia-a-dia. Descongelar a carne do almoço, preparar o lanche das crianças, aquecer o jantar. Com ele você faz tudo isso e muito mais em questão de minutos. É tão fácil que até parece mágica: com apenas um toque você consegue preparar massas, pipoca e até vegetais. Possui ainda porta espelhada com puxador, 10 opções de potência para você preparar sua refeição na medida certa, opção desliga visor que proporciona uma economia de até 12% de energia desligando o visor luminoso e comando cancelar que além de interromper o preparo, também serve para travar o teclado e deixar sua cozinha mais segura.",
				EstadoProduto.ATIVO, cat1, img1, 4.0);
		Produto p2 = new Produto(null, "iPhone 12 Pro 128GB Azul-Pacífico 6,1”", "Apple", 50, 7199.10,
				"iPhone 12 Pro. Tela Super Retina XDR brilhante de 6,1 polegadas*. Ceramic Shield, que é quatro vezes mais resistente a quedas**. Fotos surpreendentes em pouca luz com o novo sistema de câmera Pro e alcance de zoom óptico de 4x. Reprodução, edição e gravação de vídeos em Dolby Vision com qualidade de cinema. Retratos com modo Noite e experiências ainda melhores em realidade aumentada com o Scanner LiDAR. Poderoso chip A14 Bionic. Compatível com 5G***.",
				EstadoProduto.ATIVO, cat2, img2, 4.5);
		Produto p3 = new Produto(null, "Smart TV LED PRO 32'' HD", "LG", 73, 1431.27,
				"Experimente o melhor da tecnologia com a Smart PRO 32 polegadas HD da LG, oferece tela de LED e resolução HD de 1366x768 pixels obtendo uma incrível experiência de imagem, possui três entradas HDMI e duas USB, proporcionando mais possibilidades de conexão. Acompanha compatibilidade com o sistema de Inteligência Artificial ThinQ Al, conta com Bluetooth, sistema operacional webOS 4.5, Music Player, LG Content Store, Modo Hotel, painel de controle, galeria, conteúdos VR 360, miracast overlay, HDR e potência de som de 10W. Acesse aos aplicativos Netflix e Youtube, graças a conexão Smart, navegue na internet conectando-se diretamente a rede Wi-Fi. Além disso, seu design arrojado e diversos modos predefinidos de imagem fazem da Smart 32LM621CBSB.AWZ uma TV elegante e eficiente.",
				EstadoProduto.ATIVO, cat3, img3, 3.5);
		Produto p4 = new Produto(null, "Lavadora Tecnologia Jet & Clean 13 Kg", "Electrolux", 7, 1456.27,
				"Não precisa mais lavar suas roupas favoritas à mão para que elas não fiquem com fiapos. A máquina de lavar LAC13 possui um volume de filtragem seis vezes maior do que as outras lavadoras, o que evita que você passe o fim de semana tirando pelos das peças.A tecnologia Jet & Clean te livra de ter que ficar limpando o dispenser após cada ciclo e você ainda fica seguro com a garantia de dez anos do cesto de propileno da sua máquina de lavar Electrolux.",
				EstadoProduto.ATIVO, cat1, img4, 5.0);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p4));
		cat2.getProdutos().add(p2);
		cat3.getProdutos().add(p3);


		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4));

	}

}
