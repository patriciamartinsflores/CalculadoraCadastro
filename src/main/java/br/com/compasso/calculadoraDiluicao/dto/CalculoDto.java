package br.com.compasso.calculadoraDiluicao.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.compasso.calculadoraDiluicao.modelo.HistoricoEntity;

public class CalculoDto 
{	

	private String infoAdministracao;
	private List<String> passosAdministracao;
	private List<String> infoList;
	
	public CalculoDto(String infoAdministracao, List<String> passosAdministracao,List<String> infoList) 
	{
		this.infoAdministracao = infoAdministracao;
		this.passosAdministracao = passosAdministracao;
		this.infoList = infoList;
	}

	public String getInfoAdministracao() {
		return infoAdministracao;
	}

	public List<String> getPassosAdministracao() {
		return passosAdministracao;
	}

	public List<String> getInfoList() {
		return infoList;
	}
	

}