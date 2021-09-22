package br.com.compasso.calculadoraDiluicao.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.compasso.calculadoraDiluicao.modelo.HistoricoEntity;

public class HistoricoDto 
{	
	private LocalDateTime dataCalculo;
	private Long id;
	private String nomeMedicamento;
	private String nomeUsuario;
	private String quantidadeApresentada;
	private String quantidadePrescrita;
	private String resultadosJson;
	private String viaAdministracao;
	
	public HistoricoDto(HistoricoEntity historico) 
	{
		this.id                    = historico.getId();
		this.dataCalculo           = historico.getDataCalculo();
		this.nomeMedicamento       = historico.getNomeMedicamento();
		this.nomeUsuario           = historico.getNomeUsuario();
		this.quantidadeApresentada = historico.getQuantidadeApresentacao();
		this.quantidadePrescrita   = historico.getQuantidadePrescrita();
		this.resultadosJson        = historico.getResultadosJson();
		this.viaAdministracao      = historico.getViaAdministracao();
	}
	
	
	public LocalDateTime getDataCalculo() {
		return dataCalculo;
	}


	public Long getId() {
		return id;
	}


	public String getNomeMedicamento() {
		return nomeMedicamento;
	}


	public String getNomeUsuario() {
		return nomeUsuario;
	}


	public String getQuantidadeApresentada() {
		return quantidadeApresentada;
	}


	public String getQuantidadePrescrita() {
		return quantidadePrescrita;
	}


	public String getResultadosJson() {
		return resultadosJson;
	}


	public String getViaAdministracao() {
		return viaAdministracao;
	}


	public static List<HistoricoDto> converter(List<HistoricoEntity> historicoCalculos) 
	{
		return historicoCalculos.stream().map(HistoricoDto::new).collect(Collectors.toList());		
	}
}
