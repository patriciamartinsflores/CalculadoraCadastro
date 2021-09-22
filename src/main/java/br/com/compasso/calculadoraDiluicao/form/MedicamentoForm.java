package br.com.compasso.calculadoraDiluicao.form;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

public class MedicamentoForm 
{
	@NotBlank @Length(min=3)@Length(max=255)
    private String nome;
	
	 @Max(99999999)
	private BigDecimal concentracaoInicial;

	@Length(max=255)
	private String embalagemApresentada;
	
	@NotNull @Max(99999999)
	private Long idGrupoMedicamento;
	
	@NotNull @Max(99999999)
	private Long idLaboratorio;
	
	@Length(max=255)
	private String infoObservacao;
	
	@NotBlank@Length(max=255)
	private String infoSobra;
	
	@NotBlank@Length(max=255)
	private String infoTempoAdministracao;
	
	@NotNull  @PositiveOrZero @Max(99999999)
	private BigDecimal quantidadeApresentacao;
		
	@NotBlank@Length(max=255)
	private String unidadeMedida;
	
	private List<DiluicaoConfiguracaoForm> diluicoes;



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getConcentracaoInicial() {
		return concentracaoInicial;
	}

	public void setConcentracaoInicial(BigDecimal concentracaoInicial) {
		this.concentracaoInicial = concentracaoInicial;
	}

	public String getEmbalagemApresentada() {
		return embalagemApresentada;
	}

	public void setEmbalagemApresentada(String embalagemApresentada) {
		this.embalagemApresentada = embalagemApresentada;
	}

	public Long getIdGrupoMedicamento() {
		return idGrupoMedicamento;
	}

	public void setIdGrupoMedicamento(Long idGrupoMedicamento) {
		this.idGrupoMedicamento = idGrupoMedicamento;
	}

	public Long getIdLaboratorio() {
		return idLaboratorio;
	}

	public void setIdLaboratorio(Long idLaboratorio) {
		this.idLaboratorio = idLaboratorio;
	}

	public String getInfoObservacao() {
		return infoObservacao;
	}

	public void setInfoObservacao(String infoObservacao) {
		this.infoObservacao = infoObservacao;
	}

	public String getInfoSobra() {
		return infoSobra;
	}

	public void setInfoSobra(String infoSobra) {
		this.infoSobra = infoSobra;
	}

	public String getInfoTempoAdministracao() {
		return infoTempoAdministracao;
	}

	public void setInfoTempoAdministracao(String infoTempoAdministracao) {
		this.infoTempoAdministracao = infoTempoAdministracao;
	}

	public BigDecimal getQuantidadeApresentacao() {
		return quantidadeApresentacao;
	}

	public void setQuantidadeApresentacao(BigDecimal quantidadeApresentacao) {
		this.quantidadeApresentacao = quantidadeApresentacao;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public List<DiluicaoConfiguracaoForm> getDiluicoes() {
		return diluicoes;
	}

	public void setDiluicoes(List<DiluicaoConfiguracaoForm> diluicoes) {
		this.diluicoes = diluicoes;
	}
	
	

}