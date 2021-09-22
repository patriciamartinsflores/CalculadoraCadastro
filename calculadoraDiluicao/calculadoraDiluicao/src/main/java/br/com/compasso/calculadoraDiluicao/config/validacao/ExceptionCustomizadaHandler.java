package br.com.compasso.calculadoraDiluicao.config.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.compasso.calculadoraDiluicao.exceptions.ChaveEstrangeiraNaoExisteException;
import br.com.compasso.calculadoraDiluicao.exceptions.RegistroDuplicadoException;
import br.com.compasso.calculadoraDiluicao.exceptions.RegistroNaoEncontradoException;
import br.com.compasso.calculadoraDiluicao.config.validacao.ExceptionCustomizadaDto;

@RestControllerAdvice
public class ExceptionCustomizadaHandler {
			
		
		@ResponseStatus(code = HttpStatus.BAD_REQUEST)
		@ExceptionHandler(ChaveEstrangeiraNaoExisteException.class)
		public ExceptionCustomizadaDto handle(ChaveEstrangeiraNaoExisteException exception) 
		{	
			ExceptionCustomizadaDto dto = new ExceptionCustomizadaDto(exception.getMessage());
			return dto;
		}
		
		@ResponseStatus(code = HttpStatus.BAD_REQUEST)
		@ExceptionHandler(RegistroDuplicadoException.class)
		public ExceptionCustomizadaDto handle(RegistroDuplicadoException exception) 
		{	
			ExceptionCustomizadaDto dto = new ExceptionCustomizadaDto(exception.getMessage());
			return dto;
		}
		
		@ResponseStatus(code = HttpStatus.NOT_FOUND)
		@ExceptionHandler(RegistroNaoEncontradoException.class)
		public ExceptionCustomizadaDto handle(RegistroNaoEncontradoException exception) 
		{	
			ExceptionCustomizadaDto dto = new ExceptionCustomizadaDto(exception.getMessage());
			return dto;
		}

	
}
