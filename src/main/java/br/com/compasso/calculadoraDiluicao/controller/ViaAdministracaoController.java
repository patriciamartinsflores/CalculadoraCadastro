package br.com.compasso.calculadoraDiluicao.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import br.com.compasso.calculadoraDiluicao.dto.LaboratorioDto;
import br.com.compasso.calculadoraDiluicao.dto.ViaAdministracaoDto;
import br.com.compasso.calculadoraDiluicao.form.ViaAdministracaoForm;
import br.com.compasso.calculadoraDiluicao.service.ViaAdministracaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="API Rest cadastro de vias de administracao de medicamentos")
@RestController
@RequestMapping
public class ViaAdministracaoController 
{
	@Autowired
	private ViaAdministracaoService viaAdministracaoService;
	
	@ApiOperation(value="retorna lista de vias")
	@GetMapping("/viaAdministracao/listar")
	public List<ViaAdministracaoDto> listaGeral() 
	{
		return viaAdministracaoService.listaGeral();
	}
	
	@ApiOperation(value="retorna via por id")
	@GetMapping("/viaAdministracao/listar/{id}")
	public ResponseEntity<ViaAdministracaoDto> listaId(@PathVariable Long id) 
	{
		return viaAdministracaoService.listaId(id);
	}


	@ApiOperation(value="retorna via por nome")
	@GetMapping(value="/viaAdministracao/listar/{nome}", params="nome")
	public ResponseEntity<ViaAdministracaoDto> listaNome(@RequestParam(value="nome") String nome) 
	{
		return viaAdministracaoService.listaNome(nome);
	}


	@ApiOperation(value="cadastra uma via no banco de dados")
	@PostMapping(value="/viaAdministracao/criar")
	public ResponseEntity<ViaAdministracaoDto> cadastrar(@RequestBody @Valid ViaAdministracaoForm form, UriComponentsBuilder uriBuilder) 
	{		
		return viaAdministracaoService.cadastrar(form, uriBuilder);		
	}

	@ApiOperation(value="edita uma via no banco de dados")
	@PutMapping("/viaAdministracao/atualizar/{id}")
	public ResponseEntity<ViaAdministracaoDto> atualizar(@PathVariable Long id, @RequestBody @Valid ViaAdministracaoForm form)
	{
		return viaAdministracaoService.atualizar(id, form);
	}

	@ApiOperation(value="exclui uma via do banco de dados")
	@DeleteMapping("/viaAdministracao/remover/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id)
	{
		return viaAdministracaoService.remover(id);
	}

}