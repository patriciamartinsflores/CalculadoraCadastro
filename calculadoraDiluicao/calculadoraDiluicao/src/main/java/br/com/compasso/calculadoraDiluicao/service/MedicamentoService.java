package br.com.compasso.calculadoraDiluicao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.calculadoraDiluicao.dto.DiluicaoConfiguracaoDto;
import br.com.compasso.calculadoraDiluicao.dto.MedicamentoDto;
import br.com.compasso.calculadoraDiluicao.exceptions.ChaveEstrangeiraNaoExisteException;
import br.com.compasso.calculadoraDiluicao.exceptions.RegistroDuplicadoException;
import br.com.compasso.calculadoraDiluicao.exceptions.RegistroNaoEncontradoException;
import br.com.compasso.calculadoraDiluicao.form.DiluicaoConfiguracaoForm;
import br.com.compasso.calculadoraDiluicao.form.MedicamentoForm;
import br.com.compasso.calculadoraDiluicao.modelo.DiluicaoConfiguracaoEntity;
import br.com.compasso.calculadoraDiluicao.modelo.DiluicaoConfiguracaoId;
import br.com.compasso.calculadoraDiluicao.modelo.GrupoMedicamentoEntity;
import br.com.compasso.calculadoraDiluicao.modelo.LaboratorioEntity;
import br.com.compasso.calculadoraDiluicao.modelo.MedicamentoEntity;
import br.com.compasso.calculadoraDiluicao.modelo.ViaAdministracaoEntity;
import br.com.compasso.calculadoraDiluicao.repository.DiluicaoConfiguracaoRepository;
import br.com.compasso.calculadoraDiluicao.repository.GrupoMedicamentoRepository;
import br.com.compasso.calculadoraDiluicao.repository.LaboratorioRepository;
import br.com.compasso.calculadoraDiluicao.repository.MedicamentoRepository;
import br.com.compasso.calculadoraDiluicao.repository.ViaAdministracaoRepository;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;


@Service
public class MedicamentoService {
	
	@Autowired 
	private DiluicaoConfiguracaoRepository diluicaoConfiguracaoRepository;

	@Autowired
	private MedicamentoRepository medicamentoRepository;
	
	@Autowired
	private ViaAdministracaoRepository viaAdministracaoRepository;
		
	@Autowired
	private LaboratorioRepository laboratorioRepository;
	
	@Autowired
	private GrupoMedicamentoRepository grupoMedicamentoRepository;
	
	
	public List<MedicamentoDto> listaGeral() 
	{
		List<MedicamentoEntity> medicamentos = medicamentoRepository.findAll();
		return MedicamentoDto.converter(medicamentos);
	}
	
	public ResponseEntity<MedicamentoDto> listaId(Long id) 
	{
		Optional<MedicamentoEntity> optional = medicamentoRepository.findById(id);
		
		if (optional.isPresent())
		{
			MedicamentoEntity medicamento = optional.get();
			return ResponseEntity.ok(new MedicamentoDto(medicamento));		
		}
		return ResponseEntity.notFound().build();				
	}
	
	public  List<MedicamentoDto> listaNome(String nome) 
	throws RuntimeException{
		List<MedicamentoEntity> medicamentos = medicamentoRepository.findByNome(nome);
		if(medicamentos.isEmpty())
			throw new RuntimeException("Nome não encontrado!");	
		return MedicamentoDto.converter(medicamentos);		
	}
	
	@Transactional
	public ResponseEntity<?> remover(Long id)
	{
		MedicamentoEntity medicamento = medicamentoRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Medicamento não encontrado"));
		diluicaoConfiguracaoRepository.deleteByIdMedicamentoId(id);
		MedicamentoDto dto = new MedicamentoDto(medicamento);
		medicamentoRepository.deleteById(id);
		return ResponseEntity.ok(dto);
	}
	
	@Transactional
	public ResponseEntity<MedicamentoDto> cadastrar(MedicamentoForm form, UriComponentsBuilder uriBuilder)
	{				
		if(medicamentoRepository.findByGrupoMedicamentoIdAndLaboratorioIdAndNomeAndEmbalagemApresentadaAndQuantidadeApresentacao(form.getIdLaboratorio(),form.getIdGrupoMedicamento(), form.getNome(),form.getEmbalagemApresentada(),form.getQuantidadeApresentacao()).isPresent())
			throw new RegistroDuplicadoException("Medicamento com esses dados ja foi cadastrado!");					

		MedicamentoEntity medicamento = new MedicamentoEntity(validaGrupoMedicamento(form.getIdGrupoMedicamento()), validaLaboratorio(form.getIdLaboratorio()),
				form.getNome(),form.getQuantidadeApresentacao(), form.getConcentracaoInicial(), form.getInfoSobra(),
				form.getInfoObservacao(), form.getInfoTempoAdministracao(), form.getUnidadeMedida(), form.getEmbalagemApresentada());						
		medicamentoRepository.save(medicamento);
		
		//aqui fica a rotina de salvar os dados das diluicaoConfiguracao's
		List<DiluicaoConfiguracaoForm> diluicaoForms = form.getDiluicoes();
		MedicamentoDto dto;
		if(!(diluicaoForms.isEmpty()))
		{	
			for(DiluicaoConfiguracaoForm diluicaoConfiguracaoForm : diluicaoForms)
			{				
				DiluicaoConfiguracaoEntity diluicaoConfiguracao = new DiluicaoConfiguracaoEntity(diluicaoConfiguracaoForm.getQuantidadeAspirada(), 
						diluicaoConfiguracaoForm.getQuantidadeAdicionada() ,
						diluicaoConfiguracaoForm.getConcentracao(), diluicaoConfiguracaoForm.getDiluente(), diluicaoConfiguracaoForm.getModoPreparo(), 
						validaId(medicamento, validaViaAdministracao(diluicaoConfiguracaoForm.getIdViaAdministracao()),diluicaoConfiguracaoForm.getSequencia() ));
				diluicaoConfiguracaoRepository.save(diluicaoConfiguracao);
				
			}
			dto = new MedicamentoDto(medicamento, getDiluicoes(medicamento.getId())); 
		}else
			dto = new MedicamentoDto(medicamento); 
		
		URI uri = uriBuilder.path("/medicamento/{id}").buildAndExpand(medicamento.getId()).toUri();										
		return ResponseEntity.created(uri).body(dto);
	}	

	
	@Transactional //comita no final do metodo
	public ResponseEntity<MedicamentoDto> atualizar(Long id, MedicamentoForm form)
	{
		MedicamentoEntity medicamento = medicamentoRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Medicamento não encontrado"));
		
		Optional<MedicamentoEntity> medicamentoOptional = medicamentoRepository.findByGrupoMedicamentoIdAndLaboratorioIdAndNomeAndEmbalagemApresentadaAndQuantidadeApresentacao(form.getIdLaboratorio(),form.getIdGrupoMedicamento(), form.getNome(),form.getEmbalagemApresentada(),form.getQuantidadeApresentacao());		
		/* lança excecao se tem algum medicamento com 
		 * mesmo nome, grupo, laboratorio
		 * e apresentacao ja cadastrado		
		 */
		if (medicamentoOptional.isPresent() && medicamentoOptional.get().getId() != id)
			throw new RegistroDuplicadoException("Medicamento com esses dados ja foi cadastrado!");

			medicamento.setNome(form.getNome());
			medicamento.setLaboratorio(validaLaboratorio(form.getIdLaboratorio()));
			medicamento.setGrupoMedicamento(validaGrupoMedicamento(form.getIdGrupoMedicamento()));
			medicamento.setConcentracaoInicial(form.getConcentracaoInicial());
			medicamento.setQuantidadeApresentacao(form.getQuantidadeApresentacao());
			medicamento.setInfoSobra(form.getInfoSobra());
			medicamento.setInfoObservacao(form.getInfoObservacao());
			medicamento.setInfoTempoAdministracao(form.getInfoTempoAdministracao());
			medicamento.setUnidadeMedida(form.getUnidadeMedida());
			medicamento.setEmbalagemApresentada(form.getEmbalagemApresentada());			
			
			//rotina para edicao de diluicoes
			List<DiluicaoConfiguracaoForm> diluicaoForms = form.getDiluicoes();
			if(!(diluicaoForms.isEmpty()))				
			{	
				/* Assumo aqui que se a criatura preencheu a parte de 
				 * diluicoes do form medicamento, entao 
				 * ela quer editar as diluicoes. Obs: verificar se pode enviar lista de 
				 * diluicoes vazias pelos metodos PUT/POST
				 * Por isso eu excluo e recadastro novamente.
				 * Não pensei em nada melhor ainda :(				
				*/						
				diluicaoConfiguracaoRepository.deleteByIdMedicamentoId(id);
				for(DiluicaoConfiguracaoForm diluicaoConfiguracaoForm : diluicaoForms)
				{				
					DiluicaoConfiguracaoEntity diluicaoConfiguracao = new DiluicaoConfiguracaoEntity(diluicaoConfiguracaoForm.getQuantidadeAspirada(), 
							diluicaoConfiguracaoForm.getQuantidadeAdicionada() ,
							diluicaoConfiguracaoForm.getConcentracao(), diluicaoConfiguracaoForm.getDiluente(), diluicaoConfiguracaoForm.getModoPreparo(), 
							validaId(medicamento, validaViaAdministracao(diluicaoConfiguracaoForm.getIdViaAdministracao()),diluicaoConfiguracaoForm.getSequencia() ));
					diluicaoConfiguracaoRepository.save(diluicaoConfiguracao);
				
				}
				return ResponseEntity.ok(new MedicamentoDto(medicamento,getDiluicoes(medicamento.getId())));
			}
			return ResponseEntity.ok(new MedicamentoDto(medicamento));		
	}
	
	private GrupoMedicamentoEntity validaGrupoMedicamento(Long grupoMedicamentoId) throws RuntimeException
	{
		return grupoMedicamentoRepository.findById(grupoMedicamentoId).orElseThrow(() -> new ChaveEstrangeiraNaoExisteException("Grupo medicamento informado nao existe"));		
	}
	
	private ViaAdministracaoEntity validaViaAdministracao(Long viaAdministracaoId) throws RuntimeException
	{
		return viaAdministracaoRepository.findById(viaAdministracaoId).orElseThrow(() -> new ChaveEstrangeiraNaoExisteException("Via de administração informada nao existe!"));	
	}
	
	private LaboratorioEntity validaLaboratorio(Long laboratorioId) throws RuntimeException
	{
		return laboratorioRepository.findById(laboratorioId).orElseThrow(() -> new ChaveEstrangeiraNaoExisteException("LaboratorioId inválido."));		
	}
	private DiluicaoConfiguracaoId validaId(MedicamentoEntity medicamento, ViaAdministracaoEntity viaAdministracao, Long sequencia )
	{
		DiluicaoConfiguracaoId id = new DiluicaoConfiguracaoId(medicamento,viaAdministracao,sequencia);
		Optional<DiluicaoConfiguracaoEntity> optional = diluicaoConfiguracaoRepository.findById(id);
		if(optional.isPresent())
			throw new RegistroDuplicadoException("Impossivel registrar diluicaoConfiguracao com chave composta duplicada!");
		return id;
	}	

		
	public List<DiluicaoConfiguracaoDto> getDiluicoes(Long medicamentoId)
	{
		return DiluicaoConfiguracaoDto.converter(diluicaoConfiguracaoRepository.findByIdMedicamentoId(medicamentoId));
	}
	
}
