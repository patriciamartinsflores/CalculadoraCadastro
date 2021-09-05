package br.com.unknown.cadmed.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


import br.com.unknown.cadmed.controller.dto.LaboratorioDto;
import br.com.unknown.cadmed.controller.form.LaboratorioForm;
import br.com.unknown.cadmed.service.LaboratorioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="API Rest cadastro de laboratorios")
@RestController
@RequestMapping("/laboratorios")
public class LaboratoriosController 
{		
	@Autowired
	private LaboratorioService laboratorioService;
	
	
	@ApiOperation(value="retorna lista de laboratorios")
	@GetMapping
	public List<LaboratorioDto> lista() 
	{
		return laboratorioService.lista();
	}

	@ApiOperation(value="cadastra laboratorio no banco de dados")
	@PostMapping
	public ResponseEntity<LaboratorioDto> cadastrar(@RequestBody @Valid LaboratorioForm form, UriComponentsBuilder uriBuilder) 
	{		
		return laboratorioService.cadastrar(form, uriBuilder);			
	}


	@ApiOperation(value="edita laboratorio no banco de dados")
	@PutMapping("/{id}")
	public ResponseEntity<LaboratorioDto> atualizar(@PathVariable Long id, @RequestBody @Valid LaboratorioForm form)
	{		
		return laboratorioService.atualizar(id, form);
	}

	@ApiOperation(value="exclui laboratorio do banco de dados")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id)
	{	
		return laboratorioService.remover(id);
	}

}