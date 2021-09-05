package br.com.unknown.cadmed.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.unknown.cadmed.controller.dto.MedicamentoDto;
import br.com.unknown.cadmed.controller.form.AtualizacaoMedicamentoForm;
import br.com.unknown.cadmed.controller.form.MedicamentoForm;
import br.com.unknown.cadmed.modelo.GrupoMedicamento;
import br.com.unknown.cadmed.modelo.Laboratorio;
import br.com.unknown.cadmed.modelo.Medicamento;
import br.com.unknown.cadmed.repository.GrupoMedicamentoRepository;
import br.com.unknown.cadmed.repository.LaboratorioRepository;
import br.com.unknown.cadmed.repository.MedicamentoRepository;

@Service
public class MedicamentoService 
{
	@Autowired
	private MedicamentoRepository medicamentoRepository;
	
	@Autowired
	private LaboratorioRepository laboratorioRepository;
	
	@Autowired
	private GrupoMedicamentoRepository grupoMedicamentoRepository;
	
	
	public List<MedicamentoDto> lista() 
	{
		List<Medicamento> medicamentos = medicamentoRepository.findAll();
		return MedicamentoDto.converter(medicamentos);
	}
	
	@Transactional
	public ResponseEntity<?> remover(Long id)
	{
		Optional<Medicamento> optional = medicamentoRepository.findById(id);
		if (optional.isPresent())
		{
			medicamentoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@Transactional
	public ResponseEntity<MedicamentoDto> cadastrar(MedicamentoForm form, UriComponentsBuilder uriBuilder) 
	{			
		Laboratorio lab = laboratorioRepository.findByNome(form.getNomeLaboratorio());
		GrupoMedicamento grupo = grupoMedicamentoRepository.findByNome(form.getNomeGrupoMedicamento());
		if(lab == null || grupo == null)
			throw new RuntimeException("Laboratorio e/ou Grupo nao existe!");

		Medicamento medicamento = medicamentoRepository.findByNome(form.getNome());
		
		if(medicamento == null)
			medicamento = form.converter(grupoMedicamentoRepository, laboratorioRepository);						
		else if(medicamento.getLaboratorio().getNome().equals(form.getNomeLaboratorio())
			&& medicamento.getGrupoMedicamento().getNome().equals(form.getNomeGrupoMedicamento()))
				throw new RuntimeException("Medicamento repetido!");					

		medicamentoRepository.save(medicamento);
		URI uri = uriBuilder.path("/medicamentos/{id}").buildAndExpand(medicamento.getId()).toUri();										
		return ResponseEntity.created(uri).body(new MedicamentoDto(medicamento));
	}	
	
	@Transactional //comita no final do metodo
	public ResponseEntity<MedicamentoDto> atualizar(Long id, AtualizacaoMedicamentoForm form)
	throws RuntimeException
	{
		Optional<Medicamento> optional = medicamentoRepository.findById(id);
		
		if (optional.isPresent())
		{
			List <Medicamento> listaMedicamentos = medicamentoRepository.findAll();
			for(Medicamento item : listaMedicamentos) 
				if(form.getNome().equals(item.getNome()) && id != item.getId()) 
					throw new RuntimeException();

			Medicamento medicamento = form.atualizar(id, medicamentoRepository);
			return ResponseEntity.ok(new MedicamentoDto(medicamento));
		}
		return ResponseEntity.notFound().build();
	}

}
