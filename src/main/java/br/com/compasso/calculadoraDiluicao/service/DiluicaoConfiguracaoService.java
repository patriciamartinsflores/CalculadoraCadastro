package br.com.compasso.calculadoraDiluicao.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.calculadoraDiluicao.dto.DiluicaoConfiguracaoDto;
import br.com.compasso.calculadoraDiluicao.dto.MedicamentoDto;
import br.com.compasso.calculadoraDiluicao.exceptions.ChaveEstrangeiraNaoExisteException;
import br.com.compasso.calculadoraDiluicao.exceptions.RegistroDuplicadoException;
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

@Service
public class DiluicaoConfiguracaoService {
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
	
	
	public List<DiluicaoConfiguracaoDto> listaGeral() 
	{
		List<DiluicaoConfiguracaoEntity> diluicoes = diluicaoConfiguracaoRepository.findAll();
		return DiluicaoConfiguracaoDto.converter(diluicoes);
	}
		
	@Transactional
	public ResponseEntity<?> remover(Long id)
	{
		Optional<MedicamentoEntity> optional = medicamentoRepository.findById(id);
		if (optional.isPresent())
		{
			diluicaoConfiguracaoRepository.deleteByIdMedicamentoId(id);
			medicamentoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
		
}
