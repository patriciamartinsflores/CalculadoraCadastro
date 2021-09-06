package br.com.unknown.cadmed.service;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.unknown.cadmed.controller.dto.LaboratorioDto;
import br.com.unknown.cadmed.controller.form.LaboratorioForm;
import br.com.unknown.cadmed.modelo.Laboratorio;
import br.com.unknown.cadmed.modelo.Medicamento;
import br.com.unknown.cadmed.repository.LaboratorioRepository;
import br.com.unknown.cadmed.repository.MedicamentoRepository;

@Service
public class LaboratorioService 
{	
	@Autowired
	private LaboratorioRepository laboratorioRepository;
	
	@Autowired
	private MedicamentoRepository medicamentoRepository;
	
		
	@Transactional
	public ResponseEntity<LaboratorioDto> cadastrar(LaboratorioForm form, UriComponentsBuilder uriBuilder) throws RuntimeException
	{	
			Laboratorio laboratorio = laboratorioRepository.findByNome(form.getNome());
			
			if (laboratorio != null)
				throw new RuntimeException("Impossível cadastrar dois laboratórios com o mesmo nome!");		

			Laboratorio lab = form.converter();
			laboratorioRepository.save(lab);
			URI uri = uriBuilder.path("/laboratorios/{id}").buildAndExpand(lab.getId()).toUri();										
			return ResponseEntity.created(uri).body(new LaboratorioDto(lab));
	}
	
	@Transactional //comita no final do metodo
	public ResponseEntity<LaboratorioDto> atualizar(Long id, LaboratorioForm form)
	throws RuntimeException
	{
		Optional<Laboratorio> optional = laboratorioRepository.findById(id);
		
		if (optional.isPresent())
		{
			List <Laboratorio> listaLaboratorios = laboratorioRepository.findAll();
			for(Laboratorio item : listaLaboratorios) 
				if(form.getNome().equals(item.getNome()) && id != item.getId()) 
					throw new RuntimeException("Impossível cadastrar dois laboratórios com o mesmo nome!");

			Laboratorio laboratorio = form.atualizar(id, laboratorioRepository);
			return ResponseEntity.ok(new LaboratorioDto(laboratorio));
		}
		return ResponseEntity.notFound().build();
	}
	
	
	public List<LaboratorioDto> lista() 
	{
		List<Laboratorio> laboratorios = laboratorioRepository.findAll();
		return LaboratorioDto.converter(laboratorios);
	}
	
	@Transactional //comita no final do metodo
	public ResponseEntity<?> remover(Long id)
	{
		Optional<Laboratorio> optional = laboratorioRepository.findById(id);
		if (optional.isPresent())
		{
			List <Medicamento> listaMedicamentos = medicamentoRepository.findAll();
			for(Medicamento item : listaMedicamentos) 
			{//deleta todos os medicamentos ligados ao item deletado
				if(item.getLaboratorio().getId() == id)
					medicamentoRepository.delete(item);
			}
			laboratorioRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
