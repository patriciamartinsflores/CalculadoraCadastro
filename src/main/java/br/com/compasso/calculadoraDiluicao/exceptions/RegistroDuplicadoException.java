package br.com.compasso.calculadoraDiluicao.exceptions;

public class RegistroDuplicadoException extends RuntimeException{
	public RegistroDuplicadoException() 
	{
		super("Já existe registro com estes dados!");
	}
	
	public RegistroDuplicadoException(String mensagem) 
	{
		super(mensagem);
	}
}
