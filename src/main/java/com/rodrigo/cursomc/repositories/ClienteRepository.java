/*
 * OBJETO DO TIPO REPOSITORY PODE FAZER DIVERSAS OPERAÇOES DE 
 * ACESSO A DADO REFERENTES AO OBJETO DESTINADO.
 * 
 * */

package com.rodrigo.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rodrigo.cursomc.domain.Cliente;

@Repository 
public interface ClienteRepository extends JpaRepository<Cliente, Integer> { 
	
	@Transactional(readOnly=true) // springwork importation
	Cliente findByEmail(String email); //procura o email no banco o cliente com argumento email.

} // Repository é Padronizado
