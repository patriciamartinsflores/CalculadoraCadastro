package br.com.unknown.cadmed.modelo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Medicamento 
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	@ManyToOne
	private GrupoMedicamento grupoMedicamento;

	
	@ManyToOne
	private Laboratorio laboratorio;
	
	public Medicamento(){}
	
	public Medicamento(String nome, Laboratorio laboratorio, GrupoMedicamento grupoMedicamento) 
	{
		this.nome             = nome;
		this.laboratorio      = laboratorio;
		this.grupoMedicamento = grupoMedicamento;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resposta other = (Resposta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Laboratorio getLaboratorio() 
	{
		return this.laboratorio;
	}

	public void setLaboratorio(Laboratorio laboratorio) {
		this.laboratorio = laboratorio;
	}


	public GrupoMedicamento getGrupoMedicamento() {
		return grupoMedicamento;
	}

	public void setGrupoMedicamento(GrupoMedicamento grupoMedicamento) {
		this.grupoMedicamento = grupoMedicamento;
	}


}
