package br.com.compasso.calculadoraDiluicao.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.calculadoraDiluicao.dto.ViaAdministracaoDto;
import br.com.compasso.calculadoraDiluicao.exceptions.RegistroDuplicadoException;
import br.com.compasso.calculadoraDiluicao.exceptions.RegistroNaoEncontradoException;
import br.com.compasso.calculadoraDiluicao.form.ViaAdministracaoForm;
import br.com.compasso.calculadoraDiluicao.modelo.ViaAdministracaoEntity;
import br.com.compasso.calculadoraDiluicao.repository.ViaAdministracaoRepository;

@Service
public class ViaAdministracaoService 
{
	
	@Autowired
	private ViaAdministracaoRepository viaAdministracaoRepository;
			
	@Transactional
	public ResponseEntity<ViaAdministracaoDto> cadastrar(ViaAdministracaoForm form, UriComponentsBuilder uriBuilder) throws RuntimeException
	{	
			Optional<ViaAdministracaoEntity> optional = viaAdministracaoRepository.findByNome(form.getNome());
			
			if (optional.isPresent())
				throw new RegistroDuplicadoException("Impossível cadastrar duas ViasAdministracao com o mesmo nome!");		

			ViaAdministracaoEntity viaAdministracao = new ViaAdministracaoEntity(form.getNome());
			viaAdministracaoRepository.save(viaAdministracao);
			URI uri = uriBuilder.path("/viaAdministracao/{id}").buildAndExpand(viaAdministracao.getId()).toUri();										
			return ResponseEntity.created(uri).body(new ViaAdministracaoDto(viaAdministracao));
	}
	
	@Transactional //comita no final do metodo
	public ResponseEntity<ViaAdministracaoDto> atualizar(Long id, ViaAdministracaoForm form)
	{
		ViaAdministracaoEntity viaAdministracao   = viaAdministracaoRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("ViaAdministracao não encontrado"));
		Optional<ViaAdministracaoEntity> optional = viaAdministracaoRepository.findByNome(form.getNome());
		
		if(optional.isPresent() && optional.get().getId() != id) 	
				throw new RuntimeException("ViaAdministracao com nome duplicado!");
			
		viaAdministracao.setNome(form.getNome());
			return ResponseEntity.ok(new ViaAdministracaoDto(viaAdministracao));									
	}
	
	
	public List<ViaAdministracaoDto> listaGeral() 
	{
		List<ViaAdministracaoEntity> vias = viaAdministracaoRepository.findAll();
		return ViaAdministracaoDto.converter(vias);
	}
	
	public ResponseEntity<ViaAdministracaoDto> listaId(Long id) 
	{
		ViaAdministracaoEntity viaAdministracao = viaAdministracaoRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("ViaAdministracao não encontrado"));		
		return ResponseEntity.ok(new ViaAdministracaoDto(viaAdministracao));					
	}
	
	public ResponseEntity<ViaAdministracaoDto> listaNome(String nome) 
	{
		ViaAdministracaoEntity viaAdministracao = viaAdministracaoRepository.findByNome(nome).orElseThrow(() -> new RegistroNaoEncontradoException("ViaAdministracao não encontrado"));		
		return ResponseEntity.ok(new ViaAdministracaoDto(viaAdministracao));									
	}
	
	@Transactional //comita no final do metodo
	public ResponseEntity<?> remover(Long id)
	{
		ViaAdministracaoEntity viaAdministracao = viaAdministracaoRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("ViaAdministracao não encontrado"));		
		ViaAdministracaoDto viaAdministracaoDto = new ViaAdministracaoDto(viaAdministracao);
		viaAdministracaoRepository.deleteById(id);
		return ResponseEntity.ok(viaAdministracaoDto);
	}
	
}
