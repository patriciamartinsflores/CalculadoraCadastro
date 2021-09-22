package br.com.compasso.calculadoraDiluicao.exceptions;

public class ChaveEstrangeiraNaoExisteException extends RuntimeException{
	
	public ChaveEstrangeiraNaoExisteException() 
	{
		super("Impossível inserir registro vinculado a chave estrangeira inválida!");
	}
	
	public ChaveEstrangeiraNaoExisteException(String mensagem) 
	{
		super(mensagem);
	}
	
}
