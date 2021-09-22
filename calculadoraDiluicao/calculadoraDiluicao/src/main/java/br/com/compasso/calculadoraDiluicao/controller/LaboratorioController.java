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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.calculadoraDiluicao.dto.LaboratorioDto;
import br.com.compasso.calculadoraDiluicao.form.LaboratorioForm;
import br.com.compasso.calculadoraDiluicao.service.LaboratorioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="API Rest cadastro de laboratorios")
@RestController
public class LaboratorioController 
{		
	@Autowired
	private LaboratorioService laboratorioService;
	
	
	@ApiOperation(value="retorna toda a lista de laboratorios")
	@GetMapping("/laboratorio/listar")
	public List<LaboratorioDto> listaGeral() 
	{
		return laboratorioService.listaGeral();
	}
	
	@ApiOperation(value="retorna laboratorio por nome")
	@GetMapping(value="/laboratorio/listar/{nome}", params="nome")
	public ResponseEntity<LaboratorioDto> listaNome(@RequestParam(value="nome") String nome) 
	{
		return laboratorioService.listaNome(nome);
	}
	
	@ApiOperation(value="retorna laboratorio por id")
	@GetMapping(value = "/laboratorio/listar/{id}")
	public ResponseEntity<LaboratorioDto> listaId(@PathVariable Long id) 
	{
		return laboratorioService.listaId(id);
	}

	@ApiOperation(value="cadastra laboratorio no banco de dados")
	@PostMapping("/laboratorio/criar")
	public ResponseEntity<LaboratorioDto> cadastrar(@RequestBody @Valid LaboratorioForm form, UriComponentsBuilder uriBuilder) 
	{		
		return laboratorioService.cadastrar(form, uriBuilder);			
	}


	@ApiOperation(value="edita laboratorio no banco de dados")
	@PutMapping("/laboratorio/atualizar/{id}")
	public ResponseEntity<LaboratorioDto> atualizar(@PathVariable Long id, @RequestBody @Valid LaboratorioForm form)
	{		
		return laboratorioService.atualizar(id, form);
	}

	@ApiOperation(value="exclui laboratorio do banco de dados")
	@DeleteMapping("/laboratorio/remover/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id)
	{	
		return laboratorioService.remover(id);
	}

}