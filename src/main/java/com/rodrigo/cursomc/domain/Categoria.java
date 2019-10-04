package com.rodrigo.cursomc.domain;

import java.io.Serializable;

public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	/* Declarando atributos Basicos de uma entidade (classe de dominio) */
	
	private Integer id;
	private String nome;
	
	/* Criando os Construtores da classe */
	
	public Categoria() {	
	}  //Criado um construtor vazio, instancia um objeto sem jogar nada aos atributos.
	
	
	
	public Categoria(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}  /* criado um construtor com atributos, para povoa os dados acima com o do
	    *construtor (criado automaticamente atraves do eclipse: "LMB -> SOURCE -> 
	    *GENERATE CONSTRUCTOR USING FILD..." */

	/* Getters and Setters:
 	 * São gerados atraves do comando: "LMB -> SOURCE -> GENERATE Getters and Setters */
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}


	/* hashCode e equals
	 * São gerado atraves do gerador de codigo do eclipse */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)                     //Apenas do ID, pois é o que é normalmente 
			return false;                   //utilizado para comparação padrão de objetos de um sistema.
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true; 
		
		
	} 
	

	
	
	
	 	
	
	
}
