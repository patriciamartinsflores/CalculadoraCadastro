package br.com.unknown.cadmed.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.unknown.cadmed.controller.dto.GrupoMedicamentoDto;
import br.com.unknown.cadmed.controller.form.GrupoMedicamentoForm;
import br.com.unknown.cadmed.modelo.GrupoMedicamento;
import br.com.unknown.cadmed.modelo.Medicamento;
import br.com.unknown.cadmed.repository.GrupoMedicamentoRepository;
import br.com.unknown.cadmed.repository.MedicamentoRepository;


@Service
public class GrupoMedicamentoService 
{
	
	@Autowired
	private GrupoMedicamentoRepository grupoMedicamentoRepository;
	
	@Autowired
	private MedicamentoRepository medicamentoRepository;
	
	
	
	@Transactional
	public ResponseEntity<GrupoMedicamentoDto> cadastrar(GrupoMedicamentoForm form, UriComponentsBuilder uriBuilder) throws RuntimeException
	{	
		GrupoMedicamento grupoMedicamento = grupoMedicamentoRepository.findByNome(form.getNome());
			
			if (grupoMedicamento != null)
				throw new RuntimeException("Impossível cadastrar dois laboratórios com o mesmo nome!");		

			GrupoMedicamento grupo = form.converter();
			grupoMedicamentoRepository.save(grupo);
			URI uri = uriBuilder.path("/grupos/{id}").buildAndExpand(grupo.getId()).toUri();										
			return ResponseEntity.created(uri).body(new GrupoMedicamentoDto(grupo));
	}
	
	@Transactional //comita no final do metodo
	public ResponseEntity<GrupoMedicamentoDto> atualizar(Long id, GrupoMedicamentoForm form)
	throws RuntimeException
	{
		Optional<GrupoMedicamento> optional = grupoMedicamentoRepository.findById(id);
		
		if (optional.isPresent())
		{
			List <GrupoMedicamento> listaGrupos = grupoMedicamentoRepository.findAll();
			for(GrupoMedicamento item : listaGrupos) 
				if(form.getNome().equals(item.getNome()) && id != item.getId()) 
					throw new RuntimeException("Impossível cadastrar dois laboratórios com o mesmo nome!");

			GrupoMedicamento grupoMedicamento = form.atualizar(id, grupoMedicamentoRepository);
			return ResponseEntity.ok(new GrupoMedicamentoDto(grupoMedicamento));
		}
		return ResponseEntity.notFound().build();
	}
	
	
	public List<GrupoMedicamentoDto> lista() 
	{
		List<GrupoMedicamento> listaGrupos = grupoMedicamentoRepository.findAll();
		return GrupoMedicamentoDto.converter(listaGrupos);
	}
	
	@Transactional //comita no final do metodo
	public ResponseEntity<?> remover(Long id)
	{
		Optional<GrupoMedicamento> optional = grupoMedicamentoRepository.findById(id);
		if (optional.isPresent())
		{
			List <Medicamento> listaMedicamentos = medicamentoRepository.findAll();
			for(Medicamento item : listaMedicamentos) 
			{//deleta todos os medicamentos ligados ao item deletado
				if(item.getGrupoMedicamento().getId() == id)
					medicamentoRepository.delete(item);
			}
			grupoMedicamentoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
