package com.rodrigo.cursomc.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/categorias") /* nome do endpoint rest 
									    (normalmente usado o nome do conceito no plural)
									 */
public class CategoriaResource {
	
	@RequestMapping(method=RequestMethod.GET) /* Importante atribuir o verbo HTTP correto no REST,
	 										     GET usado para obter dados, já POST salva um novo 
	 										     dado, DELETE deleta assim por diante 
	 										  */
	public String listar() {
		return "REST está funcionando";
	}

}
