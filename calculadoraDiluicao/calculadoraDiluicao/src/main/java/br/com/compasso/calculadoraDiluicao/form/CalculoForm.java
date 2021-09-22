package br.com.compasso.calculadoraDiluicao.form;

import java.math.BigDecimal;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class CalculoForm 
{	
	@NotNull  @Max(99999999)
	private Long idMedicamento;
	
	@NotNull @Max(99999999)
	private Long idViaAdministracao;
	
	@NotBlank @Length(min=3)@Length(max=255)
	private String nomeUsuario;
	
	@NotNull  @Max(99999999)
	private BigDecimal quantidadePrescrita;
	
	

	/*public CalculoForm(@NotNull @Max(99999999) Long idMedicamento, @NotNull @Max(99999999) Long idViaAdministracao,
			@NotBlank @Length(min = 3) @Length(max = 255) String nomeUsuario,
			@NotNull @Max(99999999) BigDecimal quantidadePrescrita) {
		super();
		this.idMedicamento = idMedicamento;
		this.idViaAdministracao = idViaAdministracao;
		this.nomeUsuario = nomeUsuario;
		this.quantidadePrescrita = quantidadePrescrita;
	}*/

	public Long getIdMedicamento() {
		return idMedicamento;
	}

	public void setIdMedicamento(Long idMedicamento) {
		this.idMedicamento = idMedicamento;
	}

	public Long getIdViaAdministracao() {
		return idViaAdministracao;
	}

	public void setIdViaAdministracao(Long idViaAdministracao) {
		this.idViaAdministracao = idViaAdministracao;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public BigDecimal getQuantidadePrescrita() {
		return quantidadePrescrita;
	}

	public void setQuantidadePrescrita(BigDecimal quantidadePrescrita) {
		this.quantidadePrescrita = quantidadePrescrita;
	}	
	

	
}
