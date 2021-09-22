package br.com.compasso.calculadoraDiluicao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.calculadoraDiluicao.modelo.GrupoMedicamentoEntity;
import br.com.compasso.calculadoraDiluicao.modelo.LaboratorioEntity;

public interface GrupoMedicamentoRepository extends JpaRepository<GrupoMedicamentoEntity, Long>{ 

	Optional<GrupoMedicamentoEntity> findByNome(String nomeLaboratorio);
}
