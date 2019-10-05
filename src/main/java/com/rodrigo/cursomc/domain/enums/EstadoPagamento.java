package com.rodrigo.cursomc.domain.enums;

public enum EstadoPagamento {
	
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int cod;
	private String descricao;
	
	private EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao; //nao retorna apos declarar no tipo enum
	}
	
	public static EstadoPagamento toEnum(Integer cod) { //converte para o enum
		if (cod == null) { //verifica se o cod e nulo
			return null;
		}
		
		for(EstadoPagamento x : EstadoPagamento.values()) { //faz uma busca entre os objetos x a os valores do tipo cliente
			if(cod.equals(x.getCod())){ //se o codigo retorna igual a valor de cod retorna o valor de cod como x 
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	

}
