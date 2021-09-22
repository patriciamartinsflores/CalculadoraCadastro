package br.com.compasso.calculadoraDiluicao.modelo;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;


@Embeddable
public class DiluicaoConfiguracaoId implements Serializable
{

	@ManyToOne
	private MedicamentoEntity medicamento;
	
	@ManyToOne
	private ViaAdministracaoEntity viaAdministracao;
	
	private Long sequencia;	

	public DiluicaoConfiguracaoId() {}
	public DiluicaoConfiguracaoId(MedicamentoEntity medicamento, ViaAdministracaoEntity viaAdministracao,
			Long sequencia) {
		super();
		this.medicamento = medicamento;
		this.viaAdministracao = viaAdministracao;
		this.sequencia = sequencia;
	}

	public Long getSequencia() {
		return sequencia;
	}

	public MedicamentoEntity getMedicamento() {
		return medicamento;
	}


	public void setMedicamento(MedicamentoEntity medicamento) {
		this.medicamento = medicamento;
	}




	public ViaAdministracaoEntity getViaAdministracao() {
		return viaAdministracao;
	}




	public void setViaAdministracao(ViaAdministracaoEntity viaAdministracao) {
		this.viaAdministracao = viaAdministracao;
	}




	public void setSequencia(Long sequencia) {
		this.sequencia = sequencia;
	}
	

}