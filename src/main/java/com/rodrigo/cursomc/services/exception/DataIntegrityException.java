package com.rodrigo.cursomc.services.exception;

public class DataIntegrityException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DataIntegrityException(String msg) {
		super(msg); //Repassa a mensagem
	}

	public DataIntegrityException(String msg, Throwable cause) {
		super(msg, cause); //Recebe a mensagem e fala a causa do erro.
	}
	
}
