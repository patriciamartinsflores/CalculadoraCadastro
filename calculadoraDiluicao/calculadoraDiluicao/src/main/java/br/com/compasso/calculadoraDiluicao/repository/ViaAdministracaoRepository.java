package br.com.compasso.calculadoraDiluicao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.calculadoraDiluicao.modelo.ViaAdministracaoEntity;

public interface ViaAdministracaoRepository extends JpaRepository<ViaAdministracaoEntity, Long>{ 
	Optional<ViaAdministracaoEntity> findByNome(String nomeviaAdministracao);

}
