package br.com.compasso.calculadoraDiluicao.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.compasso.calculadoraDiluicao.modelo.MedicamentoEntity;
import br.com.compasso.calculadoraDiluicao.repository.DiluicaoConfiguracaoRepository;
import br.com.compasso.calculadoraDiluicao.service.MedicamentoService;

public class MedicamentoDto 
{

	
	private Long id;
	private String nome;
	private BigDecimal concentracaoInicial;
	private String embalagemApresentada;
	private Long idGrupoMedicamento;
	private Long idLaboratorio;
	private String infoObservacao;
	private String infoSobra;
	private String infoTempoAdministracao;
	private BigDecimal quantidadeApresentacao;
	private String unidadeMedida;
	private List<DiluicaoConfiguracaoDto> diluicoes;
	
	public MedicamentoDto(MedicamentoEntity medicamento, List<DiluicaoConfiguracaoDto> diluicoes) 
	{
		this.id                     = medicamento.getId();
		this.nome                   = medicamento.getNome();
		this.concentracaoInicial    = medicamento.getConcentracaoInicial();
		this.embalagemApresentada   = medicamento.getEmbalagemApresentada();
		this.idGrupoMedicamento     = medicamento.getGrupoMedicamento().getId();
		this.idLaboratorio          = medicamento.getLaboratorio().getId();
		this.infoObservacao         = medicamento.getInfoObservacao();
		this.infoSobra              = medicamento.getInfoSobra();
		this.infoTempoAdministracao = medicamento.getInfoTempoAdministracao();
		this.quantidadeApresentacao = medicamento.getQuantidadeApresentacao();
		this.unidadeMedida          = medicamento.getUnidadeMedida();		
		this.diluicoes              = diluicoes;
	}
	
	public MedicamentoDto(MedicamentoEntity medicamento) 
	{
		this.id                     = medicamento.getId();
		this.nome                   = medicamento.getNome();
		this.concentracaoInicial    = medicamento.getConcentracaoInicial();
		this.embalagemApresentada   = medicamento.getEmbalagemApresentada();
		this.idGrupoMedicamento     = medicamento.getGrupoMedicamento().getId();
		this.idLaboratorio          = medicamento.getLaboratorio().getId();
		this.infoObservacao         = medicamento.getInfoObservacao();
		this.infoSobra              = medicamento.getInfoSobra();
		this.infoTempoAdministracao = medicamento.getInfoTempoAdministracao();
		this.quantidadeApresentacao = medicamento.getQuantidadeApresentacao();
		this.unidadeMedida          = medicamento.getUnidadeMedida();		
	}

	public void adicionaDiluicaoConfiguracao(DiluicaoConfiguracaoDto diluicao) 
	{
		this.diluicoes.add(diluicao);
	}

	//mover conversao para a service?
	public static List<MedicamentoDto> converter(List<MedicamentoEntity> medicamentos) 
	{
		return medicamentos.stream().map(MedicamentoDto::new).collect(Collectors.toList());
	}


	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getConcentracaoInicial() {
		return concentracaoInicial;
	}

	public String getEmbalagemApresentada() {
		return embalagemApresentada;
	}

	public Long getIdGrupoMedicamento() {
		return idGrupoMedicamento;
	}

	public Long getIdLaboratorio() {
		return idLaboratorio;
	}

	public String getInfoObservacao() {
		return infoObservacao;
	}

	public String getInfoSobra() {
		return infoSobra;
	}

	public String getInfoTempoAdministracao() {
		return infoTempoAdministracao;
	}

	public BigDecimal getQuantidadeApresentacao() {
		return quantidadeApresentacao;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public List<DiluicaoConfiguracaoDto> getDiluicoes() {
		return diluicoes;
	}
	

}

