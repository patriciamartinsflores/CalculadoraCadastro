package br.com.unknown.cadmed.controller.dto;

import java.util.List;
import java.util.stream.Collectors;
import br.com.unknown.cadmed.modelo.Laboratorio;


public class LaboratorioDto 
{
	//private Long id;
	private String nome;
	
	public LaboratorioDto(Laboratorio laboratorio) 
	{
		//this.id   = laboratorio.getId();
		this.nome = laboratorio.getNome();
	}
	
	/*public Long getId() 
	{
		return id;
	}*/

	public String getNome() 
	{
		return nome;
	}
	
	public static List<LaboratorioDto> converter(List<Laboratorio> laboratorios) 
	{
		return laboratorios.stream().map(LaboratorioDto::new).collect(Collectors.toList());		
	}
}
