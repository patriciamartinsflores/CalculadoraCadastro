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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.calculadoraDiluicao.dto.GrupoMedicamentoDto;
import br.com.compasso.calculadoraDiluicao.form.GrupoMedicamentoForm;
import br.com.compasso.calculadoraDiluicao.service.GrupoMedicamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="API Rest cadastro de grupos de medicamentos")
@RestController
public class GrupoMedicamentoController 
{
	@Autowired
	private GrupoMedicamentoService grupoMedicamentoService;
	
	@ApiOperation(value="retorna lista de grupos")
	@GetMapping("/grupoMedicamento/listar")
	public List<GrupoMedicamentoDto> listaGeral() 
	{
		return grupoMedicamentoService.listaGeral();
	}
	
	@ApiOperation(value="retorna grupo por id")
	@GetMapping("/grupoMedicamento/listar/{id}")
	public ResponseEntity<GrupoMedicamentoDto> listaId(@PathVariable Long id) 
	{
		return grupoMedicamentoService.listaId(id);
	}

	@ApiOperation(value="retorna grupo por nome")
	@GetMapping("/grupoMedicamento/listar/{nome}")
	public ResponseEntity<GrupoMedicamentoDto> listaNome(@PathVariable String nome) 
	{
		return grupoMedicamentoService.listaNome(nome);
	}



	@ApiOperation(value="cadastra um grupo no banco de dados")
	@PostMapping("/grupoMedicamento/criar")
	public ResponseEntity<GrupoMedicamentoDto> cadastrar(@RequestBody @Valid GrupoMedicamentoForm form, UriComponentsBuilder uriBuilder) 
	{		
		return grupoMedicamentoService.cadastrar(form, uriBuilder);		
	}

	@ApiOperation(value="edita um grupo no banco de dados")
	@PutMapping("/grupoMedicamento/atualizar/{id}")
	public ResponseEntity<GrupoMedicamentoDto> atualizar(@PathVariable Long id, @RequestBody @Valid GrupoMedicamentoForm form)
	{
		return grupoMedicamentoService.atualizar(id, form);
	}

	@ApiOperation(value="exclui um grupo do banco de dados")
	@DeleteMapping("/grupoMedicamento/remover/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id)
	{
		return grupoMedicamentoService.remover(id);
	}

}