package br.com.unknown.cadmed.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import br.com.unknown.cadmed.modelo.Medicamento;


public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> 
{
	List<Medicamento> findByGrupoMedicamentoNome(String nomeGrupoMedicamento);
	
	List<Medicamento> findByLaboratorioNome(String nomeLaboratorio);
	
	Medicamento findByNome(String nome);
	
	//Optional<Medicamento> findByNome(String nome);
}
