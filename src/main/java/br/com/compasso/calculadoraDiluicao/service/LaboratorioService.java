package br.com.compasso.calculadoraDiluicao.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.calculadoraDiluicao.dto.LaboratorioDto;
import br.com.compasso.calculadoraDiluicao.exceptions.RegistroDuplicadoException;
import br.com.compasso.calculadoraDiluicao.exceptions.RegistroNaoEncontradoException;
import br.com.compasso.calculadoraDiluicao.form.LaboratorioForm;
import br.com.compasso.calculadoraDiluicao.modelo.LaboratorioEntity;
import br.com.compasso.calculadoraDiluicao.repository.LaboratorioRepository;


@Service
public class LaboratorioService 
{	
	@Autowired
	private LaboratorioRepository laboratorioRepository;
			
	@Transactional
	public ResponseEntity<LaboratorioDto> cadastrar(LaboratorioForm form, UriComponentsBuilder uriBuilder) throws RuntimeException
	{	
			Optional<LaboratorioEntity> optional = laboratorioRepository.findByNome(form.getNome());
			
			if (optional.isPresent())
				throw new RegistroDuplicadoException("Impossível cadastrar dois laboratorios com o mesmo nome!");		

			LaboratorioEntity laboratorio = new LaboratorioEntity(form.getNome());
			laboratorioRepository.save(laboratorio);
			URI uri = uriBuilder.path("/laboratorio/{id}").buildAndExpand(laboratorio.getId()).toUri();										
			return ResponseEntity.created(uri).body(new LaboratorioDto(laboratorio));
	}
	
	@Transactional //comita no final do metodo
	public ResponseEntity<LaboratorioDto> atualizar(Long id, LaboratorioForm form)
	{
		LaboratorioEntity laboratorio   = laboratorioRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Laboratorio não encontrado"));
		Optional<LaboratorioEntity> optional = laboratorioRepository.findByNome(form.getNome());
		
		if(optional.isPresent() && optional.get().getId() != id) 	
				throw new RuntimeException("Laboratorio com nome duplicado!");
			
		laboratorio.setNome(form.getNome());
			return ResponseEntity.ok(new LaboratorioDto(laboratorio));									
	}
	
	
	public List<LaboratorioDto> listaGeral() 
	{
		List<LaboratorioEntity> laboratorios = laboratorioRepository.findAll();
		return LaboratorioDto.converter(laboratorios);
	}
	
	public ResponseEntity<LaboratorioDto> listaId(Long id) 
	{
		LaboratorioEntity laboratorio = laboratorioRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Laboratorio não encontrado"));		
		return ResponseEntity.ok(new LaboratorioDto(laboratorio));					
	}
	
	public ResponseEntity<LaboratorioDto> listaNome(String nome) 
	{
		LaboratorioEntity laboratorio = laboratorioRepository.findByNome(nome).orElseThrow(() -> new RegistroNaoEncontradoException("Laboratorio não encontrado"));		
		return ResponseEntity.ok(new LaboratorioDto(laboratorio));									
	}
	
	@Transactional //comita no final do metodo
	public ResponseEntity<?> remover(Long id)
	{
		LaboratorioEntity laboratorio = laboratorioRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Laboratorio não encontrado"));		
		LaboratorioDto laboratorioDto = new LaboratorioDto(laboratorio);
		laboratorioRepository.deleteById(id);
		return ResponseEntity.ok(laboratorioDto);
	}

}
