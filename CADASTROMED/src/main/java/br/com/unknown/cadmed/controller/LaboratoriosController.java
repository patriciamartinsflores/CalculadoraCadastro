package br.com.unknown.cadmed.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


import br.com.unknown.cadmed.controller.dto.LaboratorioDto;
import br.com.unknown.cadmed.controller.form.LaboratorioForm;
import br.com.unknown.cadmed.modelo.Laboratorio;
import br.com.unknown.cadmed.repository.LaboratorioRepository;


@RestController
@RequestMapping("/laboratorios")
public class LaboratoriosController 
{	
	@Autowired
	private LaboratorioRepository laboratorioRepository;
	
	@GetMapping
	public List<LaboratorioDto> lista() 
	{
		List<Laboratorio> laboratorios = laboratorioRepository.findAll();
		return LaboratorioDto.converter(laboratorios);

	}



	@PostMapping
	@Transactional
	public ResponseEntity<LaboratorioDto> cadastrar(@RequestBody @Valid LaboratorioForm form, UriComponentsBuilder uriBuilder) 
	{		
		Laboratorio laboratorio = form.converter();	
		
		laboratorioRepository.save(laboratorio);
		URI uri = uriBuilder.path("/laboratorios/{id}").buildAndExpand(laboratorio.getId()).toUri();
		return ResponseEntity.created(uri).body(new LaboratorioDto(laboratorio));						
	}

	
	/*
	 * 	@PostMapping
	@Transactional
	public ResponseEntity<LaboratorioDto> cadastrar(@RequestBody @Valid LaboratorioForm form, UriComponentsBuilder uriBuilder) 
	{		
		Laboratorio laboratorio = form.converter();	
		
		List<Laboratorio> listaLaboratorios = laboratorioRepository.findAll();

		try 
		{//metodo q joga excecao se for igual
			validaUnico(listaLaboratorios, laboratorio);
			laboratorioRepository.save(laboratorio);
			URI uri = uriBuilder.path("/laboratorios/{id}").buildAndExpand(laboratorio.getId()).toUri();
			return ResponseEntity.created(uri).body(new LaboratorioDto(laboratorio));		
		}
		catch(RuntimeException ex)
		{//deixei o badRequest provisoriamente
			return ResponseEntity.badRequest().build();
		}		
	
	}
	 * private void validaUnico (List<Laboratorio> listaLaboratorios, Laboratorio laboratorio) 
	throws RuntimeException{
		RuntimeException ex = new RuntimeException();
		for(Laboratorio item : listaLaboratorios)
		{
			if (item.getNome().equals(laboratorio.getNome()))
				throw ex;
		}			
	}*/


	@PutMapping("/{id}")
	@Transactional //comita no final do metodo
	public ResponseEntity<LaboratorioDto> atualizar(@PathVariable Long id, @RequestBody @Valid LaboratorioForm form)
	{
		Optional<Laboratorio> optional = laboratorioRepository.findById(id);
		if (optional.isPresent())
		{
			Laboratorio laboratorio = form.atualizar(id, laboratorioRepository);
			return ResponseEntity.ok(new LaboratorioDto(laboratorio));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional //comita no final do metodo
	public ResponseEntity<?> remover(@PathVariable Long id)
	{
		Optional<Laboratorio> optional = laboratorioRepository.findById(id);
		if (optional.isPresent())
		{
			laboratorioRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}