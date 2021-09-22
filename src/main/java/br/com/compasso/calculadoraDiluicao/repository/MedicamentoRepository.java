package br.com.compasso.calculadoraDiluicao.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.calculadoraDiluicao.modelo.MedicamentoEntity;


public interface MedicamentoRepository extends JpaRepository<MedicamentoEntity, Long> 
{
	List<MedicamentoEntity> findByGrupoMedicamentoNome(String nomeGrupoMedicamento);
	
	List<MedicamentoEntity> findByLaboratorioNome(String nomeLaboratorio);
	
	List<MedicamentoEntity> findByNome(String nome);
	
	Optional<MedicamentoEntity> findById(Long id);
	
	void deleteByGrupoMedicamentoId(Long grupoMedicamentoId);
	
	void deleteByLaboratorioId(Long LaboratorioId);
	
	Optional<MedicamentoEntity> findByGrupoMedicamentoIdAndLaboratorioIdAndNomeAndEmbalagemApresentadaAndQuantidadeApresentacao(Long grupoMedicamentoId, Long laboratorioId, String nome, String embalagemApresentada, BigDecimal quantidadeApresentacao );

}
