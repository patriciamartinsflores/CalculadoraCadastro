package br.com.compasso.calculadoraDiluicao.exceptions;


public class RegistroNaoEncontradoException extends RuntimeException{
	
	public RegistroNaoEncontradoException() 
	{
		super("Já existe registro com estes dados!");
	}
	
	public RegistroNaoEncontradoException(String mensagem) 
	{
		super(mensagem);
	}
}


