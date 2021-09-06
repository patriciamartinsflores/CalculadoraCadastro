package br.com.unknown.cadmed.controller.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.unknown.cadmed.modelo.GrupoMedicamento;
import br.com.unknown.cadmed.repository.GrupoMedicamentoRepository;



public class GrupoMedicamentoForm 
{
	@NotBlank @Length(min=3)
    private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public GrupoMedicamento atualizar(Long id, GrupoMedicamentoRepository grupoMedicamentoRepository) {
		GrupoMedicamento grupoMedicamento = grupoMedicamentoRepository.getOne(id);
		grupoMedicamento.setNome(this.nome);
		return grupoMedicamento;
	}
	
	public GrupoMedicamento converter() 
	{
	    return new GrupoMedicamento(nome);
	}
}
