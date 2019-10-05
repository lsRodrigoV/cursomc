package com.rodrigo.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigo.cursomc.domain.Pedido;
import com.rodrigo.cursomc.repositories.PedidoRepository;
import com.rodrigo.cursomc.services.exception.ObjectNotFoundException;


@Service
public class PedidoService {
	
	
	@Autowired //Faz a dependencia ser automaticamente instanciada pelo mecanismo de injeção de dependencia
	private PedidoRepository repo; //Chamando um Objeto que é dependente
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id); 											//Buscando um objeto por ID 
		return obj.orElseThrow(() -> new ObjectNotFoundException( 								//Atualmente usando o Optional a partir do sts 2.xx em diante
			"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName())); // chamando o findbyid para retornar o optional e mostrar a saida.					 
													
	}//throw lança uma excessão
		
}