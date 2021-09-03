package br.com.unknown.cadmed.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.unknown.cadmed.modelo.Medicamento;
import br.com.unknown.cadmed.repository.MedicamentoRepository;

public class AtualizacaoMedicamentoForm 
{//assumindo que só posso atualizar o nome...
	@NotNull @NotEmpty @Length(min=5)
    private String nome;
	


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	

	public Medicamento atualizar(Long id, MedicamentoRepository medicamentoRepository) {
		Medicamento medicamento = medicamentoRepository.getOne(id);
		medicamento.setNome(this.nome);
		return medicamento;
	}
}
