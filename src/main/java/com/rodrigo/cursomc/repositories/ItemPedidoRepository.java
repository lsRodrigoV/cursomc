/*
 * OBJETO DO TIPO REPOSITORY PODE FAZER DIVERSAS OPERAÇOES DE 
 * ACESSO A DADO REFERENTES AO OBJETO DESTINADO.
 * 
 * */

package com.rodrigo.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rodrigo.cursomc.domain.ItemPedido;

@Repository 
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> { 
	//ESTE COMANDO TEM COMO OBJETIVO FAZER A MANIPULAÇÃO DE DADOS DO 
	//TIPO CATEGORIA IDENTIFICANDO ELES ATRAVES DO ATRIBUTO IDENTIFICADOR
	//ID(INTEGER (ID).
	

}
