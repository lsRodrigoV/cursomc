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
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
		
	}
		
		
}