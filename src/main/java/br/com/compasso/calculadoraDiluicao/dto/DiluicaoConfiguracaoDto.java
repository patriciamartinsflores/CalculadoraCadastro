package br.com.compasso.calculadoraDiluicao.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.com.compasso.calculadoraDiluicao.modelo.DiluicaoConfiguracaoEntity;
import br.com.compasso.calculadoraDiluicao.modelo.LaboratorioEntity;

public class DiluicaoConfiguracaoDto 
{
	private String nomeMedicamento;
	private String apresentacaoMedicamento;
	private String nomeViaAdministracao;
	private Long sequencia;
	private BigDecimal quantidadeAspirada;
	private BigDecimal quantidadeAdicionada;
	private BigDecimal concentracao;
	private String modoPreparo;
	private String diluente;
	
	public DiluicaoConfiguracaoDto(DiluicaoConfiguracaoEntity diluicaoConfiguracao) 
	{
		this.nomeMedicamento         = diluicaoConfiguracao.getId().getMedicamento().getNome();
		this.apresentacaoMedicamento = diluicaoConfiguracao.getId().getMedicamento().getEmbalagemApresentada() + diluicaoConfiguracao.getId().getMedicamento().getQuantidadeApresentacao();
		this.nomeViaAdministracao    = diluicaoConfiguracao.getId().getViaAdministracao().getNome();
		this.sequencia               = diluicaoConfiguracao.getId().getSequencia();
		this.quantidadeAspirada      = diluicaoConfiguracao.getQuantidadeAspirada();
		this.quantidadeAdicionada    = diluicaoConfiguracao.getQuantidadeAdicionada();
		this.concentracao            = diluicaoConfiguracao.getConcentracao();
		this.modoPreparo             = diluicaoConfiguracao.getModoPreparo();
		this.diluente                = diluicaoConfiguracao.getDiluente();
	}
	
	public static List<DiluicaoConfiguracaoDto> converter(List<DiluicaoConfiguracaoEntity> diluicoes) 
	{
		return diluicoes.stream().map(DiluicaoConfiguracaoDto::new).collect(Collectors.toList());		
	}

	public String getNomeMedicamento() {
		return nomeMedicamento;
	}

	public String getApresentacaoMedicamento() {
		return apresentacaoMedicamento;
	}

	public String getNomeViaAdministracao() {
		return nomeViaAdministracao;
	}

	public Long getSequencia() {
		return sequencia;
	}

	public BigDecimal getQuantidadeAspirada() {
		return quantidadeAspirada;
	}

	public BigDecimal getQuantidadeAdicionada() {
		return quantidadeAdicionada;
	}

	public BigDecimal getConcentracao() {
		return concentracao;
	}

	public String getModoPreparo() {
		return modoPreparo;
	}

	public String getDiluente() {
		return diluente;
	}
}
