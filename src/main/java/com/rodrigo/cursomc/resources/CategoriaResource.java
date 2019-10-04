package com.rodrigo.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigo.cursomc.domain.Categoria;

@RestController
@RequestMapping(value="/categorias") /* nome do endpoint rest 
									    (normalmente usado o nome do conceito no plural)
									 */
public class CategoriaResource {
	
	@RequestMapping(method=RequestMethod.GET) /* Importante atribuir o verbo HTTP correto no REST,
	 										     GET usado para obter dados, já POST salva um novo 
	 										     dado, DELETE deleta assim por diante 
	 										  */
	
	public List<Categoria> listar() { //metodo de retorna uma lista de categoria
		
		Categoria cat1 = new Categoria(1, "Informática");
		Categoria cat2 = new Categoria(2, "Escritório");
		
		
		List<Categoria> lista = new ArrayList<>();  //Lista de Categoria, "List" -> interface, "Categoria" -> Tipo e lista -> "Nome"
		lista.add(cat1);
		lista.add(cat2);
		
		return lista;
	}

}

