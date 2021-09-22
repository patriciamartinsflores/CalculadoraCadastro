package br.com.compasso.calculadoraDiluicao.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.compasso.calculadoraDiluicao.modelo.LaboratorioEntity;
import br.com.compasso.calculadoraDiluicao.modelo.ViaAdministracaoEntity;

public class ViaAdministracaoDto {

	private String nome;
	private Long id;
	
	public ViaAdministracaoDto(ViaAdministracaoEntity viaAdministracao) 
	{   
		this.id   = viaAdministracao.getId();
		this.nome = viaAdministracao.getNome();
	}
	
	
	public static List<ViaAdministracaoDto> converter(List<ViaAdministracaoEntity> viasAdministracao) 
	{
		return viasAdministracao.stream().map(ViaAdministracaoDto::new).collect(Collectors.toList());		
	}


	public String getNome() {
		return nome;
	}


	public Long getId() {
		return id;
	}
	
	
	
}
