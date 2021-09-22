package br.com.compasso.calculadoraDiluicao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import br.com.compasso.calculadoraDiluicao.modelo.LaboratorioEntity;


public interface LaboratorioRepository extends JpaRepository<LaboratorioEntity, Long>{ 

	//LaboratorioEntity findByNome(String nomeLaboratorio);
	Optional<LaboratorioEntity> findByNome(String nomeLaboratorio);
}
