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

import br.com.unknown.cadmed.controller.dto.GrupoMedicamentoDto;
import br.com.unknown.cadmed.controller.form.GrupoMedicamentoForm;
import br.com.unknown.cadmed.modelo.GrupoMedicamento;
import br.com.unknown.cadmed.repository.GrupoMedicamentoRepository;


@RestController
@RequestMapping("/grupos")
public class GruposMedicamentoController 
{
	@Autowired
	private GrupoMedicamentoRepository grupoMedicamentoRepository;
	
	@GetMapping
	public List<GrupoMedicamentoDto> lista() 
	{
		List<GrupoMedicamento> grupos = grupoMedicamentoRepository.findAll();
		return GrupoMedicamentoDto.converter(grupos);

	}



	@PostMapping
	@Transactional
	public ResponseEntity<GrupoMedicamentoDto> cadastrar(@RequestBody @Valid GrupoMedicamentoForm form, UriComponentsBuilder uriBuilder) 
	{		
		GrupoMedicamento grupoMedicamento = form.converter();	
		
		List<GrupoMedicamento> listaGrupos = grupoMedicamentoRepository.findAll();

		try 
		{//metodo q joga excecao se for igual
			validaUnico(listaGrupos, grupoMedicamento);
			grupoMedicamentoRepository.save(grupoMedicamento);
			URI uri = uriBuilder.path("/grupos/{id}").buildAndExpand(grupoMedicamento.getId()).toUri();
			return ResponseEntity.created(uri).body(new GrupoMedicamentoDto(grupoMedicamento));		
		}
		catch(RuntimeException ex)
		{//deixei o badRequest provisoriamente
			return ResponseEntity.badRequest().build();
		}		
	
	}

	
	private void validaUnico (List<GrupoMedicamento> listaGrupos, GrupoMedicamento grupoMedicamento) 
	throws RuntimeException{
		RuntimeException ex = new RuntimeException();
		for(GrupoMedicamento item : listaGrupos)
		{
			if (item.getNome().equals(grupoMedicamento.getNome()))
				throw ex;
		}			
	}


	@PutMapping("/{id}")
	@Transactional //comita no final do metodo
	public ResponseEntity<GrupoMedicamentoDto> atualizar(@PathVariable Long id, @RequestBody @Valid GrupoMedicamentoForm form)
	{
		Optional<GrupoMedicamento> optional = grupoMedicamentoRepository.findById(id);
		if (optional.isPresent())
		{
			GrupoMedicamento grupoMedicamento = form.atualizar(id, grupoMedicamentoRepository);
			return ResponseEntity.ok(new GrupoMedicamentoDto(grupoMedicamento));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional //comita no final do metodo
	public ResponseEntity<?> remover(@PathVariable Long id)
	{
		Optional<GrupoMedicamento> optional = grupoMedicamentoRepository.findById(id);
		if (optional.isPresent())
		{
			grupoMedicamentoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
