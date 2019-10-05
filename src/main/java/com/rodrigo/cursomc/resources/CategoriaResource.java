package com.rodrigo.cursomc.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rodrigo.cursomc.domain.Categoria;
import com.rodrigo.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias") /* nome do endpoint rest 
									    (normalmente usado o nome do conceito no plural)
									 */

public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) /* Importante atribuir o verbo HTTP correto no REST,
	 										     				GET usado para obter dados, já POST salva um novo 
	 										     				dado, DELETE deleta assim por diante 
	 														*/
	public ResponseEntity<Categoria> find(@PathVariable Integer id) { //metodo de retorna uma lista de categoria
											  				//PathVariable indica que o id deve ser herdado pelo ID do a url.
		
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);//retorna objeto do tipo responseEntity
		
	}

	@RequestMapping(method=RequestMethod.POST) // usado para adicionar via post
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){ 
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri(); // pega a url usada para inserir e acrescenta o novo id criado
		return ResponseEntity.created(uri).build(); //chama o metodo created para gerar o codigo 201		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT) //apresentao value =id, e usa o metodo post para alterar
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id){ //tem tanto o request body quanto o pathvariable (recebe e envia).
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}


}

