package br.com.compasso.calculadoraDiluicao.service;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.compasso.calculadoraDiluicao.dto.HistoricoDto;
import br.com.compasso.calculadoraDiluicao.exceptions.ChaveEstrangeiraNaoExisteException;
import br.com.compasso.calculadoraDiluicao.exceptions.RegistroNaoEncontradoException;
import br.com.compasso.calculadoraDiluicao.form.CalculoForm;
import br.com.compasso.calculadoraDiluicao.modelo.DiluicaoConfiguracaoEntity;
import br.com.compasso.calculadoraDiluicao.modelo.DiluicaoConfiguracaoId;
import br.com.compasso.calculadoraDiluicao.modelo.HistoricoEntity;
import br.com.compasso.calculadoraDiluicao.modelo.MedicamentoEntity;
import br.com.compasso.calculadoraDiluicao.modelo.ViaAdministracaoEntity;
import br.com.compasso.calculadoraDiluicao.repository.DiluicaoConfiguracaoRepository;
import br.com.compasso.calculadoraDiluicao.repository.HistoricoRepository;
import br.com.compasso.calculadoraDiluicao.repository.MedicamentoRepository;
import br.com.compasso.calculadoraDiluicao.repository.ViaAdministracaoRepository;

@Service
public class CalculoService 
{
	@Autowired
	private DiluicaoConfiguracaoRepository diluicaoConfiguracaoRepository;
	@Autowired
	private HistoricoRepository historicoRepository;
	@Autowired
	private MedicamentoRepository medicamentoRepository;
	@Autowired
	private ViaAdministracaoRepository viaAdministracaoRepository;
	
	String resultadosJson;

	public ResponseEntity<HistoricoDto> calcular(@Valid CalculoForm form, UriComponentsBuilder uriBuilder) 
	{
		MedicamentoEntity medicamento           = validaMedicamento(form.getIdMedicamento());
		ViaAdministracaoEntity viaAdministracao = validaViaAdministracao(form.getIdViaAdministracao());
		
		DiluicaoConfiguracaoId idReconstituicao   = new DiluicaoConfiguracaoId(medicamento,viaAdministracao,(long)1);
		DiluicaoConfiguracaoEntity reconstituicao = validaDiluicaoConfiguracao(idReconstituicao);
		resultadosJson = "Modo de preparo 1:"+"\""+ reconstituicao.getModoPreparo()+"\"";
		
		
		
		DiluicaoConfiguracaoId idRediluicao = new DiluicaoConfiguracaoId(medicamento,viaAdministracao,(long)2);
		DiluicaoConfiguracaoEntity rediluicao = validaDiluicaoConfiguracao(idRediluicao);
		resultadosJson.concat("Modo de preparo 2:"+"\""+ rediluicao.getModoPreparo()+"\"");
		 /*
		 * aqui vai ficar a logica do calculo
		 * onde vou calcular para cada etapa e adicionar no resultadosJson
		 */
		BigDecimal calculoFinal = form.getQuantidadePrescrita().divide((BigDecimal) medicamento.getQuantidadeApresentacao());
		
		resultadosJson.concat("Resultado Final:"+"\""+calculoFinal+"\"");
		
		HistoricoEntity historico = new HistoricoEntity();
		
		historico.setNomeUsuario(form.getNomeUsuario());
		historico.setNomeMedicamento(medicamento.getNome());
		historico.setQuantidadeApresentacao(String.valueOf(medicamento.getQuantidadeApresentacao()));
		historico.setQuantidadePrescrita(String.valueOf(form.getQuantidadePrescrita()));    
		historico.setViaAdministracao(viaAdministracao.getNome());     
		historico.setDataCalculo(LocalDateTime.now());          
		historico.setResultadosJson(resultadosJson);       
		
		historicoRepository.save(historico);
		URI uri = uriBuilder.path("/calculo/{id}").buildAndExpand(historico.getId()).toUri();										
		return ResponseEntity.created(uri).body(new HistoricoDto(historico));
	  }
	 


	public List<HistoricoDto> listaGeral() 
	{
		List<HistoricoEntity>  historicos = historicoRepository.findAll();
		return HistoricoDto.converter(historicos);
	}

	public ResponseEntity<HistoricoDto> listaId(Long id) 
	{
		HistoricoEntity historico = historicoRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Registro calculoHistorico não encontrado"));		
		return ResponseEntity.ok(new HistoricoDto(historico));					
	}
	
	private MedicamentoEntity validaMedicamento(Long medicamentoId)
	{
		return medicamentoRepository.findById(medicamentoId).orElseThrow(() -> new ChaveEstrangeiraNaoExisteException("Medicamento nao existe!"));	
	}
		
	private ViaAdministracaoEntity validaViaAdministracao(Long viaAdministracaoId) throws RuntimeException
	{
		return viaAdministracaoRepository.findById(viaAdministracaoId).orElseThrow(() -> new ChaveEstrangeiraNaoExisteException("Via de administração informada nao existe!"));	
	}
	
	private DiluicaoConfiguracaoEntity validaDiluicaoConfiguracao(DiluicaoConfiguracaoId diluicaoConfiguracaoId) 
	{
		return diluicaoConfiguracaoRepository.findById(diluicaoConfiguracaoId).orElseThrow(() -> new ChaveEstrangeiraNaoExisteException("Diluicao configuracao nao existe!"));	
	}
}
