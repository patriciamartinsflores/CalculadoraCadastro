package br.com.unknown.cadmed.controller.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.unknown.cadmed.modelo.GrupoMedicamento;
import br.com.unknown.cadmed.modelo.Laboratorio;
import br.com.unknown.cadmed.modelo.Medicamento;
import br.com.unknown.cadmed.repository.GrupoMedicamentoRepository;
import br.com.unknown.cadmed.repository.LaboratorioRepository;
import br.com.unknown.cadmed.repository.MedicamentoRepository;


public class MedicamentoForm 
{
	@NotBlank @Length(min=3)
    private String nome;
	
	@NotBlank @Length(min=3)
    private String nomeLaboratorio;
	
	@NotBlank @Length(min=3)
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
	
	public Medicamento atualizar(Long id, MedicamentoRepository medicamentoRepository, LaboratorioRepository laboratorioRepository, GrupoMedicamentoRepository grupoMedicamentoRepository) 
	{
		Medicamento medicamento = medicamentoRepository.getOne(id);
		medicamento.setNome(this.nome);
		medicamento.setLaboratorio(laboratorioRepository.findByNome(nomeLaboratorio));
		medicamento.setGrupoMedicamento(grupoMedicamentoRepository.findByNome(nomeGrupoMedicamento));
		
		return medicamento;
	}
}
