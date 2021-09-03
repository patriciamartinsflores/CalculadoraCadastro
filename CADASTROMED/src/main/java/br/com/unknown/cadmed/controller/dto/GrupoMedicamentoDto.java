package br.com.unknown.cadmed.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.unknown.cadmed.modelo.GrupoMedicamento;

public class GrupoMedicamentoDto 
{
	//private Long id;
	private String nome;
	
	public GrupoMedicamentoDto(GrupoMedicamento grupoMedicamento) 
	{
		//this.id   = grupoMedicamento.getId();
		this.nome = grupoMedicamento.getNome();
	}
	
	/*public Long getId() 
	{
		return id;
	}*/

	public String getNome() 
	{
		return nome;
	}
	
	public static List<GrupoMedicamentoDto> converter(List<GrupoMedicamento> gruposMedicamento) 
	{
		return gruposMedicamento.stream().map(GrupoMedicamentoDto::new).collect(Collectors.toList());		
	}
}
