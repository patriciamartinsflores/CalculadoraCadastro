package br.com.compasso.calculadoraDiluicao.modelo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="historico")
public class HistoricoEntity 
{
	@Column(name="Id")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="NomeUsuario")
	private String nomeUsuario;

	@Column(name="NomeMedicamento")
	private String nomeMedicamento;

	@Column(name="QuantidadeApresentacao")
	private String quantidadeApresentacao;

	@Column(name="QuantidadePrescrita")
	private String quantidadePrescrita;

	@Column(name="ViaAdministracao")
	private String viaAdministracao;

	@Column(name="DataCalculo")
	private LocalDateTime dataCalculo;

	@Column(name="ResultadosJson")
	private String resultadosJson;
	
	
	public HistoricoEntity() {}
	public HistoricoEntity(String nomeUsuario, String nomeMedicamento, String quantidadeApresentacao,
			String quantidadePrescrita, String viaAdministracao, LocalDateTime dataCalculo, String resultadosJson)
	{
		this.nomeUsuario            = nomeUsuario;
		this.nomeMedicamento        = nomeMedicamento;
		this.quantidadeApresentacao = quantidadeApresentacao;
		this.quantidadePrescrita    = quantidadePrescrita;
		this.viaAdministracao       = viaAdministracao;
		this.dataCalculo            = dataCalculo;
		this.resultadosJson         = resultadosJson;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNomeUsuario() {
		return nomeUsuario;
	}



	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}



	public String getNomeMedicamento() {
		return nomeMedicamento;
	}



	public void setNomeMedicamento(String nomeMedicamento) {
		this.nomeMedicamento = nomeMedicamento;
	}



	public String getQuantidadeApresentacao() {
		return quantidadeApresentacao;
	}



	public void setQuantidadeApresentacao(String quantidadeApresentacao) {
		this.quantidadeApresentacao = quantidadeApresentacao;
	}



	public String getQuantidadePrescrita() {
		return quantidadePrescrita;
	}



	public void setQuantidadePrescrita(String quantidadePrescrita) {
		this.quantidadePrescrita = quantidadePrescrita;
	}



	public String getViaAdministracao() {
		return viaAdministracao;
	}



	public void setViaAdministracao(String viaAdministracao) {
		this.viaAdministracao = viaAdministracao;
	}



	public LocalDateTime getDataCalculo() {
		return dataCalculo;
	}



	public void setDataCalculo(LocalDateTime dataCalculo) {
		this.dataCalculo = dataCalculo;
	}



	public String getResultadosJson() {
		return resultadosJson;
	}



	public void setResultadosJson(String resultadosJson) {
		this.resultadosJson = resultadosJson;
	}

}
