package br.com.compasso.calculadoraDiluicao.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.compasso.calculadoraDiluicao.modelo.GrupoMedicamentoEntity;

public class GrupoMedicamentoDto {
	
	private Long id;
	private String nome;
	
	public GrupoMedicamentoDto(GrupoMedicamentoEntity grupoMedicamento) 
	{
		this.id   = grupoMedicamento.getId();
		this.nome = grupoMedicamento.getNome();
	}
	
	
	public static List<GrupoMedicamentoDto> converter(List<GrupoMedicamentoEntity> grupoMedicamento) 
	{
		return grupoMedicamento.stream().map(GrupoMedicamentoDto::new).collect(Collectors.toList());		
	}


	public Long getId() {
		return id;
	}


	public String getNome() {
		return nome;
	}
	
}
