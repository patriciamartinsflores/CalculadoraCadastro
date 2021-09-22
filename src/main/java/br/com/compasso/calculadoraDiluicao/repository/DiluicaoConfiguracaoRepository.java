package br.com.compasso.calculadoraDiluicao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.calculadoraDiluicao.modelo.DiluicaoConfiguracaoEntity;
import br.com.compasso.calculadoraDiluicao.modelo.DiluicaoConfiguracaoId;

public interface DiluicaoConfiguracaoRepository extends JpaRepository<DiluicaoConfiguracaoEntity, Long>{ 

	void deleteByIdMedicamentoId(Long medicamentoId);
	List<DiluicaoConfiguracaoEntity> findByIdMedicamentoId(Long medicamentoId);
	Optional<DiluicaoConfiguracaoEntity> findById(DiluicaoConfiguracaoId diluicaoConfiguracaoId);
	
}