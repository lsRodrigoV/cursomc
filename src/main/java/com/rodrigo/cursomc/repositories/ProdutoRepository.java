/*
 * OBJETO DO TIPO REPOSITORY PODE FAZER DIVERSAS OPERAÇOES DE 
 * ACESSO A DADO REFERENTES AO OBJETO DESTINADO.
 * 
 * */

package com.rodrigo.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rodrigo.cursomc.domain.Categoria;
import com.rodrigo.cursomc.domain.Produto;

@Repository 
public interface ProdutoRepository extends JpaRepository<Produto, Integer> { 
	
	@Transactional(readOnly=true) // springwork importation
	//@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome like %:nome% AND cat IN :categorias")
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);

} // Repository é Padronizado
