package br.com.unknown.cadmed.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.unknown.cadmed.config.validacao.LaboratorioEGrupoMedExistem;
import br.com.unknown.cadmed.modelo.GrupoMedicamento;
import br.com.unknown.cadmed.modelo.Laboratorio;
import br.com.unknown.cadmed.modelo.Medicamento;
import br.com.unknown.cadmed.repository.GrupoMedicamentoRepository;
import br.com.unknown.cadmed.repository.LaboratorioRepository;

@LaboratorioEGrupoMedExistem
public class MedicamentoForm 
{
	@NotNull @NotEmpty @Length(min=5)
    private String nome;
	
	@NotNull @NotEmpty 
    private String nomeLaboratorio;
	
	@NotNull @NotEmpty 
    private String nomeGrupoMedicamento;
    
    

	
	public String getNome() {
		return nome;
	}




	public void setNome(String nome) {
		this.nome = nome;
	}




	public String getNomeLaboratorio() {
		return nomeLaboratorio;
	}




	public void setNomeLaboratorio(String nomeLaboratorio) {
		this.nomeLaboratorio = nomeLaboratorio;
	}




	public String getNomeGrupoMedicamento() {
		return nomeGrupoMedicamento;
	}




	public void setNomeGrupoMedicamento(String nomeGrupoMedicamento) {
		this.nomeGrupoMedicamento = nomeGrupoMedicamento;
	}


	public Medicamento converter(GrupoMedicamentoRepository grupoMedicamentoRepository, LaboratorioRepository laboratorioRepository) 
	{
		Laboratorio laboratorio = laboratorioRepository.findByNome(nomeLaboratorio);
		GrupoMedicamento grupoMedicamento = grupoMedicamentoRepository.findByNome(nomeGrupoMedicamento);
	    return new Medicamento(nome, laboratorio, grupoMedicamento);
	}
}
