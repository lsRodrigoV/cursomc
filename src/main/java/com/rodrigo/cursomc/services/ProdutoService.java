package com.rodrigo.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rodrigo.cursomc.domain.Categoria;
import com.rodrigo.cursomc.domain.Produto;
import com.rodrigo.cursomc.repositories.CategoriaRepository;
import com.rodrigo.cursomc.repositories.ProdutoRepository;
import com.rodrigo.cursomc.services.exception.ObjectNotFoundException;



@Service
public class ProdutoService {	
	
	@Autowired //Faz a dependencia ser automaticamente instanciada pelo mecanismo de injeção de dependencia
	private ProdutoRepository repo; //Chamando um Objeto que é dependente
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id); 											//Buscando um objeto por ID 
		return obj.orElseThrow(() -> new ObjectNotFoundException( 								//Atualmente usando o Optional a partir do sts 2.xx em diante
			"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName())); // chamando o findbyid para retornar o optional e mostrar a saida.					 
													
	}//throw lança uma excessão
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){ //o find all vai considerar o pagerequest como uma sobrecarga e envia a pagina
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);	
		List<Categoria> categorias = categoriaRepository.findAllById(ids); 
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
		
	}
}