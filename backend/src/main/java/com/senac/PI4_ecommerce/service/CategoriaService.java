package com.senac.PI4_ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senac.PI4_ecommerce.model.Categoria;
import com.senac.PI4_ecommerce.repository.CategoriaRepository;

@Service
public class CategoriaService {

	// indica que essa dependencia vai ser instanciada automaticamente pelo spring
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria getCategoria(Integer id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		if(!categoria.isEmpty()) {
			return categoria.get();

		}
		// Implementar erro
		return categoria.get();
	}
	

//	public Categoria buscar(Integer id) {
//		Optional<Categoria> categoria = categoriaRepository.findById(id);
//		return categoria.orElseThrow(() -> new ObjectNotFoundException(
//				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
//	}
//
//	public List<Categoria> listar() {
//		List<Categoria> categorias = categoriaRepository.findAll();
//		if (categorias.isEmpty())
//			throw new ObjectNotFoundException("Nenhum objeto encontrado!");
//		return categorias;
//	}
//
//	public Categoria inserir(Categoria categoria) {
//		categoria.setId(null); // Garantindo que o objeto seja uma nova categoria
//		return categoriaRepository.save(categoria);
//	}
//
//	public Categoria atualizar(Categoria categoria) {
//		Categoria novaCategoria = buscar(categoria.getId());
//		atualizaDados(categoria, novaCategoria);
//		return categoriaRepository.save(novaCategoria);
//	}
//
//	public void deletar(Integer id) {
//		this.buscar(id);
//		try {
//			categoriaRepository.deleteById(id);
//		} catch (DataIntegrityViolationException e) {
//			throw new DataIntegrityException(
//					"Não é possivel excluir uma categoria que tenha produtos cadastrados. Tente remover os produtos antes.");
//		}
//	}
//
//	public Page<Categoria> listarPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
//		PageRequest pr = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
//		return categoriaRepository.findAll(pr);
//	}
//
//	public Categoria fromDTO(CategoriaDTO categoriaDTO) {
//		return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
//	}
//	
//	private void atualizaDados (Categoria categoria, Categoria novaCategoria) {
//		novaCategoria.setNome(categoria.getNome());
//	}

}
