package br.com.compasso.calculadoraDiluicao.form;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

public class DiluicaoConfiguracaoForm 
{	
	@NotNull @Max(99999999)
	private Long idViaAdministracao;
	
	@NotNull @Max(2)
	private Long sequencia;
		
	@NotBlank @Length(max=255)
	private String modoPreparo;
	
	@NotNull @PositiveOrZero @Max(99999999)
	private BigDecimal quantidadeAspirada;
	
	@NotNull @PositiveOrZero @Max(99999999)
	private BigDecimal quantidadeAdicionada;
	
	@NotNull @PositiveOrZero @Max(99999999)
	private BigDecimal concentracao;
	
	@NotBlank @Length(max=255)
	private String diluente;

	/*public DiluicaoConfiguracaoForm(@NotNull @Max(99999999) Long idViaAdministracao, @NotNull @Max(2) Long sequencia,
			@NotBlank @Length(max = 255) String modoPreparo,
			@NotNull @PositiveOrZero @Max(99999999) BigDecimal quantidadeAspirada,
			@NotNull @PositiveOrZero @Max(99999999) BigDecimal quantidadeAdicionada,
			@NotNull @PositiveOrZero @Max(99999999) BigDecimal concentracao,
			@NotBlank @Length(max = 255) String diluente) {
		super();
		this.idViaAdministracao = idViaAdministracao;
		this.sequencia = sequencia;
		this.modoPreparo = modoPreparo;
		this.quantidadeAspirada = quantidadeAspirada;
		this.quantidadeAdicionada = quantidadeAdicionada;
		this.concentracao = concentracao;
		this.diluente = diluente;
	}*/

	public Long getIdViaAdministracao() {
		return idViaAdministracao;
	}

	public void setIdViaAdministracao(Long idViaAdministracao) {
		this.idViaAdministracao = idViaAdministracao;
	}

	public Long getSequencia() {
		return sequencia;
	}

	public void setSequencia(Long sequencia) {
		this.sequencia = sequencia;
	}

	public String getModoPreparo() {
		return modoPreparo;
	}

	public void setModoPreparo(String modoPreparo) {
		this.modoPreparo = modoPreparo;
	}

	public BigDecimal getQuantidadeAspirada() {
		return quantidadeAspirada;
	}

	public void setQuantidadeAspirada(BigDecimal quantidadeAspirada) {
		this.quantidadeAspirada = quantidadeAspirada;
	}

	public BigDecimal getQuantidadeAdicionada() {
		return quantidadeAdicionada;
	}

	public void setQuantidadeAdicionada(BigDecimal quantidadeAdicionada) {
		this.quantidadeAdicionada = quantidadeAdicionada;
	}

	public BigDecimal getConcentracao() {
		return concentracao;
	}

	public void setConcentracao(BigDecimal concentracao) {
		this.concentracao = concentracao;
	}

	public String getDiluente() {
		return diluente;
	}

	public void setDiluente(String diluente) {
		this.diluente = diluente;
	}


		
}
