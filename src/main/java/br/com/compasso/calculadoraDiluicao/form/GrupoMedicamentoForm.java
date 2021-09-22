package br.com.compasso.calculadoraDiluicao.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class GrupoMedicamentoForm 
{
	@NotBlank @Length(min=3)@Length(max=255)
    private String nome;
	
	/*public GrupoMedicamentoForm(@NotBlank @Length(min = 3) @Length(max = 255) String nome) {
		super();
		this.nome = nome;
	}*/

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
