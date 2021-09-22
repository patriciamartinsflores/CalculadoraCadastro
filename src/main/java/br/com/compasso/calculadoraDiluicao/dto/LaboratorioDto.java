package br.com.compasso.calculadoraDiluicao.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.compasso.calculadoraDiluicao.modelo.LaboratorioEntity;


public class LaboratorioDto 
{
	private Long id;
	private String nome;
	
	public LaboratorioDto(LaboratorioEntity laboratorio) 
	{
		this.id   = laboratorio.getId();
		this.nome = laboratorio.getNome();
	}
	
	
	public static List<LaboratorioDto> converter(List<LaboratorioEntity> laboratorios) 
	{
		return laboratorios.stream().map(LaboratorioDto::new).collect(Collectors.toList());		
	}


	public Long getId() {
		return id;
	}


	public String getNome() {
		return nome;
	}
	
}
