package com.senac.PI4_ecommerce.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class SaveFile {

	@Value("${ecommerce.dir.raiz}")
	private String raiz = "/Users/victor/tmp/pi4_ecommerce/";
	
	@Value("${ecommerce.dir.diretorio-imagens}")
	private String diretorioFotos = "imagens/";
	
	public void salvarImg(MultipartFile foto, Integer idProduto) {
		String diretorio = this.diretorioFotos + idProduto;
		this.salvar(diretorio, foto);
		System.out.println( "Foto salva no diretorio " + diretorio );
	}
	
	public void salvar(String diretorio, MultipartFile arquivo) {
		Path diretorioPath = Paths.get(this.raiz, diretorio);
		Path arquivoPath = diretorioPath.resolve(arquivo.getOriginalFilename());
		
		try {
			System.out.println(diretorioPath);
			Files.createDirectories(diretorioPath);
			arquivo.transferTo(arquivoPath.toFile());			
		} catch (IOException e) {
			throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
		}		
	}
	
}
