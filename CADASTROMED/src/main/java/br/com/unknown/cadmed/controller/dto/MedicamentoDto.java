package br.com.unknown.cadmed.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.unknown.cadmed.modelo.Medicamento;
public class MedicamentoDto 
{

	//private Long id;
	private String nome;
	private String nomeGrupo;
	private String nomeLaboratorio;
	
	public MedicamentoDto(Medicamento medicamento) 
	{
		//this.id       = medicamento.getId();
		this.nome      = medicamento.getNome();
		this.nomeGrupo = medicamento.getGrupoMedicamento().getNome();
		this.nomeLaboratorio      = medicamento.getLaboratorio().getNome();
	}

	/*public Long getId() 
	{
		return id;
	}*/

	public String getNome() 
	{
		return nome;
	}
	public String getNomeGrupo() 
	{
		return nomeGrupo;
	}
	public String getNomeLaboratorio() 
	{
		return nomeLaboratorio;
	}

	public static List<MedicamentoDto> converter(List<Medicamento> medicamentos) 
	{
		return medicamentos.stream().map(MedicamentoDto::new).collect(Collectors.toList());
	}

}
