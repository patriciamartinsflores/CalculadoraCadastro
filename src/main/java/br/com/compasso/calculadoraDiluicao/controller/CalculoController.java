package br.com.compasso.calculadoraDiluicao.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.calculadoraDiluicao.dto.CalculoDto;
import br.com.compasso.calculadoraDiluicao.dto.HistoricoDto;
import br.com.compasso.calculadoraDiluicao.form.CalculoForm;
import br.com.compasso.calculadoraDiluicao.modelo.LaboratorioEntity;
import br.com.compasso.calculadoraDiluicao.service.CalculoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="API Rest de calculo de diluicao de medicamentos")
@RestController
@RequestMapping("/calculo")
public class CalculoController 
{
	@Autowired
	private CalculoService calculoService;
	
	
	@ApiOperation(value="retorna toda a lista de calculos")
	@GetMapping("/calculo/listar")
	public List<HistoricoDto> listaGeral() 
	{
		return calculoService.listaGeral();
	}	
	
	@ApiOperation(value="retorna calculo por id")
	@GetMapping("/calculo/listar/{id}")
	public ResponseEntity<HistoricoDto> listaId(@PathVariable Long id) 
	{
		return calculoService.listaId(id);
	}

	@ApiOperation(value="calcula diluicao e grava calculo feito na tabela de historico")
	@PostMapping("/calculo/criar")
	public ResponseEntity<CalculoDto> cadastrar(@RequestBody @Valid CalculoForm form, UriComponentsBuilder uriBuilder) 
	{		
		return calculoService.calcular(form, uriBuilder);			
	}


	
}
