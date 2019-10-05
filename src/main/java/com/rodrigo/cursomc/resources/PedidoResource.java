package com.rodrigo.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigo.cursomc.domain.Pedido;
import com.rodrigo.cursomc.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos") /* nome do endpoint rest 
									    (normalmente usado o nome do conceito no plural)
									 */

public class PedidoResource {
	
	@Autowired
	private PedidoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) /* Importante atribuir o verbo HTTP correto no REST,
	 										     				GET usado para obter dados, já POST salva um novo 
	 										     				dado, DELETE deleta assim por diante 
	 														*/
	public ResponseEntity<Pedido> find(@PathVariable Integer id) { //metodo de retorna uma lista de categoria
											  				//PathVariable indica que o id deve ser herdado pelo ID do a url.
		
		Pedido obj = service.find(id);
		return ResponseEntity.ok().body(obj);//retorna objeto do tipo responseEntity
		
	}

}

