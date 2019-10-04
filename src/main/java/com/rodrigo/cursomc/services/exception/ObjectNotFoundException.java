package com.rodrigo.cursomc.services.exception;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String msg) {
		super(msg); //Repassa a mensagem
	}

	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause); //Recebe a mensagem e fala a causa do erro.
	}
	
}
