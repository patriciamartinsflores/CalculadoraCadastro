package br.com.unknown.cadmed.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.unknown.cadmed.modelo.Laboratorio;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long>{ 

	Laboratorio findByNome(String nomeLaboratorio);
}
