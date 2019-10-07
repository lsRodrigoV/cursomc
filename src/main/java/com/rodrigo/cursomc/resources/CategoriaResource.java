package com.rodrigo.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rodrigo.cursomc.domain.Categoria;
import com.rodrigo.cursomc.dto.CategoriaDTO;
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
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto){ 
		Categoria obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri(); // pega a url usada para inserir e acrescenta o novo id criado
		return ResponseEntity.created(uri).build(); //chama o metodo created para gerar o codigo 201		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT) //apresentao value =id, e usa o metodo post para alterar
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id){ //tem tanto o request body quanto o pathvariable (recebe e envia).
		Categoria obj = service.fromDTO(objDto); // especifica o que ira receber e daonde vai vir o recebimento
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findPage() { 
		List<Categoria> list = service.findAll();
		List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList()); 
		return ResponseEntity.ok().body(listDto);
	}
	
	
	@RequestMapping(value="/page", method=RequestMethod.GET) //Parametros para busca por pagina das categorias.
	public ResponseEntity<Page<CategoriaDTO>> findPage(	
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome")String orderBy,
			@RequestParam(value="direction", defaultValue="ASC")String direction) { // asc = ascendente ou desc = descendente
		Page<Categoria> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj)); 
		return ResponseEntity.ok().body(listDto);
	}

}

