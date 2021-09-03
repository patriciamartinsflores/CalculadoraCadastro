package br.com.unknown.cadmed.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.unknown.cadmed.modelo.GrupoMedicamento;

public interface GrupoMedicamentoRepository extends JpaRepository<GrupoMedicamento, Long>{ 

	GrupoMedicamento findByNome(String nomeGrupoMedicamento);
}
