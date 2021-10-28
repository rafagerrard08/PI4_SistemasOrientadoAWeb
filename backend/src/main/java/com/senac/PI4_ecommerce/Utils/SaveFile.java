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
	String raiz = "/Users/victor/git/PI4_SistemasOrientadoAWeb";

	@Value("${ecommerce.dir.diretorio-imagens}")
	String dirImagens = "/imagens/";
	
	public String salvarImg(MultipartFile foto, Integer idProduto, String nomeArquivo) {
		String diretorio = this.dirImagens + idProduto;
		String path = this.salvar(diretorio, foto, nomeArquivo);
		System.out.println( "Foto salva no diretorio " + diretorio );
		return path;
	}
	
	public String salvar(String diretorio, MultipartFile arquivo, String nomeArquivo) {
		Path diretorioPath = Paths.get(this.raiz + diretorio);
		Path arquivoPath = diretorioPath.resolve(nomeArquivo);
		
		try {
			System.out.println(diretorioPath);
			Files.createDirectories(diretorioPath);
			arquivo.transferTo(arquivoPath.toFile());	
		} catch (IOException e) {
			throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
		}
		return diretorio + "/" + arquivo.getOriginalFilename();
	}
	
}
