package br.com.compasso.calculadoraDiluicao.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="diluicao_configuracao")
public class DiluicaoConfiguracaoEntity {
	
	@EmbeddedId
	private DiluicaoConfiguracaoId id;
	
	//id composto
	@Column(name="quantidade_aspirada")
	private BigDecimal quantidadeAspirada;
	
	@Column(name="quantidade_adicionada")
	private BigDecimal quantidadeAdicionada;
	
	private BigDecimal concentracao;
	
	private String diluente;
	
	@Column(name="modo_preparo")
	private String modoPreparo;

	public DiluicaoConfiguracaoId getId() {
		return id;
	}

	public void setId(DiluicaoConfiguracaoId id) {
		this.id = id;
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

	public String getModoPreparo() {
		return modoPreparo;
	}

	public void setModoPreparo(String modoPreparo) {
		this.modoPreparo = modoPreparo;
	}
	
	public MedicamentoEntity getMedicamento() {
		return id.getMedicamento();
	}
	
	public ViaAdministracaoEntity getViaAdministracao() {
		return id.getViaAdministracao();
	}

	public DiluicaoConfiguracaoEntity() {}
	public DiluicaoConfiguracaoEntity(BigDecimal quantidadeAspirada, BigDecimal quantidadeAdicionada,
			BigDecimal concentracao, String diluente, String modoPreparo, DiluicaoConfiguracaoId id) {
		this.id                   = id;
		this.quantidadeAspirada   = quantidadeAspirada;
		this.quantidadeAdicionada = quantidadeAdicionada;
		this.concentracao         = concentracao;
		this.diluente             = diluente;
		this.modoPreparo          = modoPreparo;
	}
	
	
	

}
