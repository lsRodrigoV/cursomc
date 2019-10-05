/*
 * OBJETO DO TIPO REPOSITORY PODE FAZER DIVERSAS OPERAÇOES DE 
 * ACESSO A DADO REFERENTES AO OBJETO DESTINADO.
 * 
 * */

package com.rodrigo.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rodrigo.cursomc.domain.Endereco;

@Repository 
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> { 

} // Repository é Padronizado
