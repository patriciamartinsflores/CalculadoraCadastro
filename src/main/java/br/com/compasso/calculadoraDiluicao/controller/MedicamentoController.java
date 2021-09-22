package br.com.compasso.calculadoraDiluicao.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.calculadoraDiluicao.dto.HistoricoDto;
import br.com.compasso.calculadoraDiluicao.dto.LaboratorioDto;
import br.com.compasso.calculadoraDiluicao.dto.MedicamentoDto;
import br.com.compasso.calculadoraDiluicao.form.CalculoForm;
import br.com.compasso.calculadoraDiluicao.form.MedicamentoForm;
import br.com.compasso.calculadoraDiluicao.service.CalculoService;
import br.com.compasso.calculadoraDiluicao.service.MedicamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="API Rest de calculo de diluicao de medicamentos")
@RestController
public class MedicamentoController 
{
	@Autowired
	private MedicamentoService medicamentoService;
	
	
	@ApiOperation(value="retorna toda a lista de calculos")
	@GetMapping("/medicamento/listar")
	public List<MedicamentoDto> listaGeral() 
	{
		return medicamentoService.listaGeral();
	}	
	
	@ApiOperation(value="retorna medicamento por id")
	@GetMapping("/medicamento/listar/{id}")
	public ResponseEntity<MedicamentoDto> listaId(@PathVariable Long id) 
	{
		return medicamentoService.listaId(id);
	}
	
	@ApiOperation(value="retorna medicamento por nome")
	@GetMapping(value="/medicamento/listar/{nome}", params="nome")
	public List<MedicamentoDto> listaNome(@RequestParam(value="nome") String nome) 
	{
		return medicamentoService.listaNome(nome);
	}
	

	@ApiOperation(value="cria medicamento e diluiicoes")
	@PostMapping("/medicamento/criar")
	public ResponseEntity<MedicamentoDto> cadastrar(@RequestBody @Valid MedicamentoForm form, UriComponentsBuilder uriBuilder) 
	{		
		return medicamentoService.cadastrar(form, uriBuilder);			
	}
	
	@ApiOperation(value="calcula diluicao e grava calculo feito na tabela de historico")
	@PutMapping("/medicamento/atualizar/{id}")
	public ResponseEntity<MedicamentoDto> atualizar(@PathVariable Long id, @RequestBody  @Valid MedicamentoForm form) 
	{		
		return medicamentoService.atualizar(id, form);			
	}
	
	@ApiOperation(value="calcula diluicao e grava calculo feito na tabela de historico")
	@DeleteMapping("/medicamento/remover/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) 
	{		
		return medicamentoService.remover(id);			
	}



	
}
