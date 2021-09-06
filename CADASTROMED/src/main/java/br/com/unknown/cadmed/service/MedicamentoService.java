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
	public ResponseEntity<MedicamentoDto> cadastrar(MedicamentoForm form, UriComponentsBuilder uriBuilder) throws RuntimeException
	{			
		validaGrupoELaboratorio(form.getNomeLaboratorio(),form.getNomeGrupoMedicamento());

		Medicamento medicamento = medicamentoRepository.findByNome(form.getNome());
		
		if(medicamento == null)
			medicamento = form.converter(grupoMedicamentoRepository, laboratorioRepository);						
		else if(comparaGrupoELab(form,medicamento))
				throw new RuntimeException("Medicamento com esses dados ja foi cadastrado!");					

		medicamentoRepository.save(medicamento);
		URI uri = uriBuilder.path("/medicamentos/{id}").buildAndExpand(medicamento.getId()).toUri();										
		return ResponseEntity.created(uri).body(new MedicamentoDto(medicamento));
	}	
	
	@Transactional //comita no final do metodo
	public ResponseEntity<MedicamentoDto> atualizar(Long id, MedicamentoForm form)
	throws RuntimeException
	{
		validaGrupoELaboratorio(form.getNomeLaboratorio(),form.getNomeGrupoMedicamento());

		Optional<Medicamento> optional = medicamentoRepository.findById(id);
		
		if (optional.isPresent())
		{
			List <Medicamento> listaMedicamentos = medicamentoRepository.findAll();
			for(Medicamento item : listaMedicamentos) 
				if(comparaGrupoELab(form,item) && id != item.getId()) 
					throw new RuntimeException("Medicamento com esses dados ja foi cadastrado!");

			Medicamento medicamento = form.atualizar(id, medicamentoRepository, laboratorioRepository, grupoMedicamentoRepository);
			return ResponseEntity.ok(new MedicamentoDto(medicamento));
		}
		return ResponseEntity.notFound().build();
	}
	
	public void validaGrupoELaboratorio(String nomeLab, String nomeGrupo) throws RuntimeException
	{
		Laboratorio lab = laboratorioRepository.findByNome(nomeLab);
		GrupoMedicamento grupo = grupoMedicamentoRepository.findByNome(nomeGrupo);
		if(lab == null || grupo == null)
			throw new RuntimeException("Laboratorio e/ou Grupo nao existe!");
	}
	
	public boolean comparaGrupoELab(MedicamentoForm form, Medicamento medicamento)
	{
		if(form.getNomeGrupoMedicamento().equals(medicamento.getGrupoMedicamento().getNome())
		&& form.getNomeLaboratorio().equals(medicamento.getLaboratorio().getNome()))
			return true;
		return false;
	}

}
