package com.rodrigo.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rodrigo.cursomc.domain.Cidade;
import com.rodrigo.cursomc.domain.Cliente;
import com.rodrigo.cursomc.domain.Endereco;
import com.rodrigo.cursomc.domain.enums.TipoCliente;
import com.rodrigo.cursomc.dto.ClienteDTO;
import com.rodrigo.cursomc.dto.ClienteNewDTO;
import com.rodrigo.cursomc.repositories.ClienteRepository;
import com.rodrigo.cursomc.repositories.EnderecoRepository;
import com.rodrigo.cursomc.services.exception.DataIntegrityException;
import com.rodrigo.cursomc.services.exception.ObjectNotFoundException;


@Service
public class ClienteService {
	
	
	@Autowired //Faz a dependencia ser automaticamente instanciada pelo mecanismo de injeção de dependencia
	private ClienteRepository repo; //Chamando um Objeto que é dependente
	
	@Autowired
	private EnderecoRepository enderecoRepository;
		
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id); 											//Buscando um objeto por ID 
		return obj.orElseThrow(() -> new ObjectNotFoundException( 								//Atualmente usando o Optional a partir do sts 2.xx em diante
			"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())); // chamando o findbyid para retornar o optional e mostrar a saida.					 
													
	}//throw lança uma excessão
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
		
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());	
		updateData(newObj,obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) { //DELETA A CATEGORIA CRIADA
		try {
		find(id);
		repo.deleteById(id);
		} 
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma Cliente que possui pedidos relacionadas");
		}
	}
	
	public List<Cliente> findAll(){ // APRESENTA TODAS AS CATEGORIAS
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){ //o find all vai considerar o pagerequest como uma sobrecarga e envia a pagina
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);	
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(),objDto.getNome(),objDto.getEmail(),null, null);			
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfouCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if(objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		
		if(objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
		
}