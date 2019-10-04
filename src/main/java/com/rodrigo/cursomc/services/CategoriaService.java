package com.rodrigo.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigo.cursomc.domain.Categoria;
import com.rodrigo.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	
	@Autowired //Faz a dependencia ser automaticamente instanciada pelo mecanismo de injeção de dependencia
	private CategoriaRepository repo; //Chamando um Objeto que é dependente
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id); //Buscando um objeto por ID
		return obj.orElse(null);					 //Atualmente usando o Optional a partir do sts 2.xx em diante
													// chamando o findbyid para retornar o optional e mostrar a saida.
	}
		
		
}