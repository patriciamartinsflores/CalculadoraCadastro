package br.com.compasso.calculadoraDiluicao.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.calculadoraDiluicao.dto.GrupoMedicamentoDto;
import br.com.compasso.calculadoraDiluicao.exceptions.RegistroDuplicadoException;
import br.com.compasso.calculadoraDiluicao.exceptions.RegistroNaoEncontradoException;
import br.com.compasso.calculadoraDiluicao.form.GrupoMedicamentoForm;
import br.com.compasso.calculadoraDiluicao.modelo.GrupoMedicamentoEntity;
import br.com.compasso.calculadoraDiluicao.repository.GrupoMedicamentoRepository;

@Service
public class GrupoMedicamentoService {
	
	@Autowired
	private GrupoMedicamentoRepository grupoMedicamentoRepository;
			
	@Transactional
	public ResponseEntity<GrupoMedicamentoDto> cadastrar(GrupoMedicamentoForm form, UriComponentsBuilder uriBuilder) throws RuntimeException
	{	
			Optional<GrupoMedicamentoEntity> optional = grupoMedicamentoRepository.findByNome(form.getNome());
			
			if (optional.isPresent())
				throw new RegistroDuplicadoException("Impossível cadastrar dois grupos com o mesmo nome!");		

			GrupoMedicamentoEntity grupoMedicamento = new GrupoMedicamentoEntity(form.getNome());
			grupoMedicamentoRepository.save(grupoMedicamento);
			URI uri = uriBuilder.path("/grupoMedicamento/{id}").buildAndExpand(grupoMedicamento.getId()).toUri();										
			return ResponseEntity.created(uri).body(new GrupoMedicamentoDto(grupoMedicamento));
	}
	
	@Transactional //comita no final do metodo
	public ResponseEntity<GrupoMedicamentoDto> atualizar(Long id, GrupoMedicamentoForm form)
	{
		GrupoMedicamentoEntity grupoMedicamento   = grupoMedicamentoRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("GrupoMedicamento não encontrado"));
		Optional<GrupoMedicamentoEntity> optional = grupoMedicamentoRepository.findByNome(form.getNome());
		
		if(optional.isPresent() && optional.get().getId() != id) 	
				throw new RuntimeException("Grupo com nome duplicado!");
			
		grupoMedicamento.setNome(form.getNome());
			return ResponseEntity.ok(new GrupoMedicamentoDto(grupoMedicamento));									
	}
	
	
	public List<GrupoMedicamentoDto> listaGeral() 
	{
		List<GrupoMedicamentoEntity> grupos = grupoMedicamentoRepository.findAll();
		return GrupoMedicamentoDto.converter(grupos);
	}
	
	public ResponseEntity<GrupoMedicamentoDto> listaId(Long id) 
	{
		GrupoMedicamentoEntity grupoMedicamento = grupoMedicamentoRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("GrupoMedicamento não encontrado"));		
		return ResponseEntity.ok(new GrupoMedicamentoDto(grupoMedicamento));					
	}
	
	public ResponseEntity<GrupoMedicamentoDto> listaNome(String nome) 
	{
		GrupoMedicamentoEntity grupoMedicamento = grupoMedicamentoRepository.findByNome(nome).orElseThrow(() -> new RegistroNaoEncontradoException("GrupoMedicamento não encontrado"));		
		return ResponseEntity.ok(new GrupoMedicamentoDto(grupoMedicamento));									
	}
	
	@Transactional //comita no final do metodo
	public ResponseEntity<?> remover(Long id)
	{
		GrupoMedicamentoEntity grupoMedicamento = grupoMedicamentoRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("GrupoMedicamento não encontrado"));		
		GrupoMedicamentoDto grupoMedicamentodto = new GrupoMedicamentoDto(grupoMedicamento);
		grupoMedicamentoRepository.deleteById(id);
		return ResponseEntity.ok(grupoMedicamentodto);
	}
	
}
