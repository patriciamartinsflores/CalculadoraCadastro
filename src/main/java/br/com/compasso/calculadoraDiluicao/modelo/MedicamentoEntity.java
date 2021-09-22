package br.com.compasso.calculadoraDiluicao.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="medicamento")
public class MedicamentoEntity 
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private GrupoMedicamentoEntity grupoMedicamento;
	
	@ManyToOne
	private LaboratorioEntity laboratorio;
		
	private String nome;
	
	@Column(name="quantidade_apresentacao")
	private BigDecimal quantidadeApresentacao;
	
	@Column(name="concentracao_inicial")
	private BigDecimal concentracaoInicial;
	
	@Column(name="info_sobra")
	private String infoSobra;
	
	@Column(name="info_observacao")
	private String infoObservacao;
	
	@Column(name="info_tempo_administracao")
	private String infoTempoAdministracao;
	
	@Column(name="unidade_medida")
	private String unidadeMedida;
	
	@Column(name="embalagem_apresentada")
	private String embalagemApresentada;
	
	public BigDecimal getQuantidadeApresentacao() {
		return quantidadeApresentacao;
	}
	
	public MedicamentoEntity(){}
	


	public MedicamentoEntity(GrupoMedicamentoEntity grupoMedicamento, LaboratorioEntity laboratorio,
			String nome, BigDecimal quantidadeApresentacao, BigDecimal concentracaoInicial, String infoSobra,
			String infoObservacao, String infoTempoAdministracao, String unidadeMedida, String embalagemApresentada) {
		super();
		this.grupoMedicamento = grupoMedicamento;
		this.laboratorio = laboratorio;
		this.nome = nome;
		this.quantidadeApresentacao = quantidadeApresentacao;
		this.concentracaoInicial = concentracaoInicial;
		this.infoSobra = infoSobra;
		this.infoObservacao = infoObservacao;
		this.infoTempoAdministracao = infoTempoAdministracao;
		this.unidadeMedida = unidadeMedida;
		this.embalagemApresentada = embalagemApresentada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GrupoMedicamentoEntity getGrupoMedicamento() {
		return grupoMedicamento;
	}

	public void setGrupoMedicamento(GrupoMedicamentoEntity grupoMedicamento) {
		this.grupoMedicamento = grupoMedicamento;
	}

	public LaboratorioEntity getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(LaboratorioEntity laboratorio) {
		this.laboratorio = laboratorio;
	}

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

	public String getInfoSobra() {
		return infoSobra;
	}

	public void setInfoSobra(String infoSobra) {
		this.infoSobra = infoSobra;
	}

	public String getInfoObservacao() {
		return infoObservacao;
	}

	public void setInfoObservacao(String infoObservacao) {
		this.infoObservacao = infoObservacao;
	}

	public String getInfoTempoAdministracao() {
		return infoTempoAdministracao;
	}

	public void setInfoTempoAdministracao(String infoTempoAdministracao) {
		this.infoTempoAdministracao = infoTempoAdministracao;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public String getEmbalagemApresentada() {
		return embalagemApresentada;
	}

	public void setEmbalagemApresentada(String embalagemApresentada) {
		this.embalagemApresentada = embalagemApresentada;
	}

	public void setQuantidadeApresentacao(BigDecimal quantidadeApresentacao) {
		this.quantidadeApresentacao = quantidadeApresentacao;
	}


}