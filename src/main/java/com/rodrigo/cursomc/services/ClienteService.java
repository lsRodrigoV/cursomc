package com.rodrigo.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigo.cursomc.domain.Cliente;
import com.rodrigo.cursomc.repositories.ClienteRepository;
import com.rodrigo.cursomc.services.exception.ObjectNotFoundException;


@Service
public class ClienteService {
	
	
	@Autowired //Faz a dependencia ser automaticamente instanciada pelo mecanismo de injeção de dependencia
	private ClienteRepository repo; //Chamando um Objeto que é dependente
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id); 											//Buscando um objeto por ID 
		return obj.orElseThrow(() -> new ObjectNotFoundException( 								//Atualmente usando o Optional a partir do sts 2.xx em diante
			"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())); // chamando o findbyid para retornar o optional e mostrar a saida.					 
													
	}//throw lança uma excessão
		
}