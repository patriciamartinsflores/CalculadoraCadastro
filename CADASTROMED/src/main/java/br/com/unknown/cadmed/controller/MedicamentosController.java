package br.com.unknown.cadmed.controller;



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

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.unknown.cadmed.modelo.Medicamento;
import br.com.unknown.cadmed.repository.GrupoMedicamentoRepository;
import br.com.unknown.cadmed.repository.LaboratorioRepository;
import br.com.unknown.cadmed.repository.MedicamentoRepository;
import br.com.unknown.cadmed.controller.dto.MedicamentoDto;
import br.com.unknown.cadmed.controller.form.AtualizacaoMedicamentoForm;
import br.com.unknown.cadmed.controller.form.MedicamentoForm;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentosController {
	
	@Autowired
	private MedicamentoRepository medicamentoRepository;
	@Autowired
	private GrupoMedicamentoRepository grupoMedicamentoRepository;
	@Autowired
	private LaboratorioRepository laboratorioRepository;
	

	@GetMapping
	public List<MedicamentoDto> lista() 
	{
		List<Medicamento> medicamentos = medicamentoRepository.findAll();
		return MedicamentoDto.converter(medicamentos);

	}



	@PostMapping
	@Transactional
	public ResponseEntity<MedicamentoDto> cadastrar(@RequestBody @Valid MedicamentoForm form, UriComponentsBuilder uriBuilder) {		
		Medicamento medicamento = form.converter(grupoMedicamentoRepository, laboratorioRepository);	
		
		List<Medicamento> listaMedicamentos = medicamentoRepository.findAll();

		try 
		{//metodo q joga excecao se for igual
			validaUnico(listaMedicamentos, medicamento);
			medicamentoRepository.save(medicamento);
			URI uri = uriBuilder.path("/medicamentos/{id}").buildAndExpand(medicamento.getId()).toUri();
			return ResponseEntity.created(uri).body(new MedicamentoDto(medicamento));		
		}
		catch(RuntimeException ex)
		{//deixei o badRequest provisoriamente
			return ResponseEntity.badRequest().build();
		}		
	
	}

	
	private void validaUnico (List<Medicamento> listaMedicamentos, Medicamento medicamento) 
	throws RuntimeException{
		RuntimeException ex = new RuntimeException();
		for(Medicamento item : listaMedicamentos)
		{
			if (item.getNome().equals(medicamento.getNome())&&
				item.getGrupoMedicamento().getId() == medicamento.getGrupoMedicamento().getId()	&&
				item.getLaboratorio().getId() == medicamento.getLaboratorio().getId()) 
			{
				throw ex;
			}
		}			
	}



	//@PutMapping("/{id}")
	@PutMapping("editar/{id}")
	@Transactional //comita no final do metodo
	public ResponseEntity<MedicamentoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoMedicamentoForm form)
	{
		Optional<Medicamento> optional = medicamentoRepository.findById(id);
		if (optional.isPresent())
		{
			Medicamento medicamento = form.atualizar(id, medicamentoRepository);
			return ResponseEntity.ok(new MedicamentoDto(medicamento));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional //comita no final do metodo
	public ResponseEntity<?> remover(@PathVariable Long id)
	{
		Optional<Medicamento> optional = medicamentoRepository.findById(id);
		if (optional.isPresent())
		{
			medicamentoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
