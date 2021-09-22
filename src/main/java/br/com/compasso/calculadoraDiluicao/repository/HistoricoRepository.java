package br.com.compasso.calculadoraDiluicao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.calculadoraDiluicao.modelo.HistoricoEntity;


public interface HistoricoRepository extends JpaRepository<HistoricoEntity, Long>{ 

	
	
}