package com.rodrigo.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rodrigo.cursomc.domain.Categoria;
import com.rodrigo.cursomc.dto.CategoriaDTO;
import com.rodrigo.cursomc.repositories.CategoriaRepository;
import com.rodrigo.cursomc.services.exception.DataIntegrityException;
import com.rodrigo.cursomc.services.exception.ObjectNotFoundException;


@Service
public class CategoriaService {
	
	
	@Autowired //Faz a dependencia ser automaticamente instanciada pelo mecanismo de injeção de dependencia
	private CategoriaRepository repo; //Chamando um Objeto que é dependente
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id); 											//Buscando um objeto por ID 
		return obj.orElseThrow(() -> new ObjectNotFoundException( 								//Atualmente usando o Optional a partir do sts 2.xx em diante
			"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName())); // chamando o findbyid para retornar o optional e mostrar a saida.					 
													
	}//throw lança uma excessão
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId());	
		updateData(newObj,obj);
		return repo.save(newObj);
	}

	
	public void delete(Integer id) { //DELETA A CATEGORIA CRIADA
		try {
		find(id);
		repo.deleteById(id);
		} 
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produto");
		}
	}
	
	public List<Categoria> findAll(){ // APRESENTA TODAS AS CATEGORIAS
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){ //o find all vai considerar o pagerequest como uma sobrecarga e envia a pagina
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);	
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome()); //metodo auxiliar para instancia uma categoria a partir de um DTO.
	}
	
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}
}