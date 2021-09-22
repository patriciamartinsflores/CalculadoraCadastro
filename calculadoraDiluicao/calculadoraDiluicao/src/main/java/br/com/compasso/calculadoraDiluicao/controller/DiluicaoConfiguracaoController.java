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

import br.com.compasso.calculadoraDiluicao.dto.DiluicaoConfiguracaoDto;
import br.com.compasso.calculadoraDiluicao.dto.ViaAdministracaoDto;
import br.com.compasso.calculadoraDiluicao.form.ViaAdministracaoForm;
import br.com.compasso.calculadoraDiluicao.service.DiluicaoConfiguracaoService;
import br.com.compasso.calculadoraDiluicao.service.MedicamentoService;
import br.com.compasso.calculadoraDiluicao.service.ViaAdministracaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="API Rest cadastro de vias de administracao de medicamentos")
@RestController
@RequestMapping
public class DiluicaoConfiguracaoController 
{
	@Autowired
	private DiluicaoConfiguracaoService diluicaoConfiguracaoService;
	
	@ApiOperation(value="retorna lista de configuracao diluicao")
	@GetMapping("/diluicaoConfiguracao/listar")
	public List<DiluicaoConfiguracaoDto> listaGeral() 
	{
		return diluicaoConfiguracaoService.listaGeral();
	}
	
	@ApiOperation(value="retorna configuracao diluicao por id")
	@GetMapping("/diluicaoConfiguracao/listar/{id}")
	public ResponseEntity<DiluicaoConfiguracaoDto> listaId(@PathVariable Long id) 
	{
		return null;
	}


	@ApiOperation(value="exclui uma configuracao diluicao do banco de dados")
	@DeleteMapping("/diluicaoConfiguracao/remover/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id)
	{
		return null;
	}

}