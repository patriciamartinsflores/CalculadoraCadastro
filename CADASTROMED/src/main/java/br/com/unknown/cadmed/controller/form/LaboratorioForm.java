package br.com.unknown.cadmed.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import br.com.unknown.cadmed.config.validacao.NomeLabUnico;
import br.com.unknown.cadmed.modelo.Laboratorio;

import br.com.unknown.cadmed.repository.LaboratorioRepository;
//mesmo form usado para cadastro e edicao

@NomeLabUnico
public class LaboratorioForm 
{
	 @NotNull @NotEmpty @Length(min=2)
    private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Laboratorio atualizar(Long id, LaboratorioRepository laboratorioRepository) {
		Laboratorio laboratorio = laboratorioRepository.getOne(id);
		laboratorio.setNome(this.nome);
		return laboratorio;
	}
	
	public Laboratorio converter() 
	{
	    return new Laboratorio(nome);
	}
}
