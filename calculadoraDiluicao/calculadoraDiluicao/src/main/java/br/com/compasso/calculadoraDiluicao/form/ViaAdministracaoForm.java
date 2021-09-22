package br.com.compasso.calculadoraDiluicao.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class ViaAdministracaoForm 
{
	@NotBlank //@Length(max=255)
    private String nome;
	
		
	/*public ViaAdministracaoForm(@NotBlank @Length(max = 255) String nome) {
		this.nome = nome;
	}*/

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
